package org.example.android.architecture.mvi.todo

import java.util.Collections
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

data class UserDto(val id: Int, val userName: String, val changedLineCount: Int)

data class UsersResponse(val users: List<UserDto>)

data class User(val id: Int, val userName: String)

fun UserDto.toDomain() = User(id, userName)

sealed class Content<T> {

    data class Data<T>(val data: T) : Content<T>()
    data class Loading<T>(val data: T? = null) : Content<T>()
    data class Error<T>(val exception: Throwable) : Content<T>()
}

interface Api {

    suspend fun getUsers(): UsersResponse

    suspend fun removeUser(id: Int)
}

interface Cache {

    suspend fun put(key: String, value: Any?)

    suspend fun <T> get(key: String): T?

    suspend fun remove(key: String)

    suspend fun <T> observe(key: String): Flow<T?>
}

suspend fun <T : Any> firstCacheLoad(
    cacheKey: String,
    dispatcher: CoroutineDispatcher,
    memoryCache: Cache,
    networkCall: suspend () -> Content<T>
): Flow<Content<T>> =
    withContext(dispatcher) {
        val cached = memoryCache.observe<T>(cacheKey).firstOrNull()
        val initial: Content<T> = Content.Loading(cached)
        merge(
            flow {
                emit(initial)
                val callResult = networkCall()
                if (callResult is Content.Error) {
                    emit(callResult)
                } else if (callResult is Content.Data) {
                    memoryCache.put(cacheKey, callResult.data)
                }
            },
            memoryCache.observe<T>(cacheKey).filterNotNull().map { Content.Data(it) as Content<T> }
        )
    }.flowOn(dispatcher)

sealed class UserState {
    data class Data(val users: List<User>) : UserState()
    data class Loading(val users: List<User>? = null) : UserState()
    data class Error(val message: String) : UserState()
}

class UserRepository(
    private val api: Api,
    private val memoryCache: Cache
) {
    fun fetchUsers(): Flow<Content<List<UserDto>>> {
        return flow {
            try {
                api.getUsers().also { users ->
                    memoryCache.put("USERS", users.users)
                    emit(Content.Data(users.users))
                }
            } catch (t: Throwable) {
                emit(Content.Error(t))
            }
        }
    }
}

class UsersViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: UserRepository,
) {
    private val viewLifecycle = CoroutineScope(dispatcher)

    private val _userState: MutableStateFlow<UserState> = MutableStateFlow(UserState.Loading())
    val userState = _userState.asStateFlow()

    fun loadUsers() {
        viewLifecycle.launch {
            repository.fetchUsers().collect { contentUserDto ->
                _userState.value = when (contentUserDto) {
                    is Content.Loading<List<UserDto>> -> {
                        UserState.Loading(contentUserDto.data?.map { it.toDomain() })
                    }

                    is Content.Data<List<UserDto>> -> {
                        val users = contentUserDto.data.map { it.toDomain() }
                        UserState.Data(users)
                    }

                    is Content.Error<*> -> {
                        UserState.Error(contentUserDto.exception.message ?: "Unknown error")
                    }
                }
            }
        }
    }
}

class DiComponent {

    private val memoryCache = MemoryCache()

    val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    fun provideApi(): Api = RemoteApi()

    fun provideCache(): Cache {
        return memoryCache
    }

    fun provideRepository(): UserRepository = UserRepository(
        provideApi(),
        provideCache()
    )

    fun provideViewModel(): UsersViewModel = UsersViewModel(
        dispatcher,
        provideRepository()
    )
}

class Fragment {

    val diComponent = DiComponent()

    val viewLifeCycleScope = CoroutineScope(diComponent.dispatcher)

    val memoryCache: Cache = diComponent.provideCache()

    val viewModel: UsersViewModel = diComponent.provideViewModel()

    val view = ListView()

    fun onCreate(): Job {
        val job = viewLifeCycleScope.launch {
            viewModel.userState.collect { state ->
                when (state) {
                    is UserState.Loading -> {
                        renderLoading(state)
                    }
                    is UserState.Data -> {
                        renderData(state)
                    }
                    is UserState.Error -> renderError(state)
                }
            }
        }
        viewModel.loadUsers()

        return job
    }

    private fun renderLoading(state: UserState.Loading) {
        println()
        println("NEW_STATE-------------------")
        println("LOADING IN PROGRESS")
        val cachedUsers = state.users
        if (cachedUsers != null) {
            println("CONTENT CACHED:")
            val views = cachedUsers.map { RowView(it.userName) }
            view.render(views)
        }
        println()
        println()
    }

    private fun renderError(state: UserState.Error) {
        println()
        println("NEW_STATE-------------------")
        println()
        view.render(RowView(state.message))
        println()
        println()
    }

    private fun renderData(state: UserState.Data) {
        println()
        println("NEW_STATE-------------------")
        println()
        val views = state.users.map { RowView(it.userName) }
        view.render(views)
        println()
        println()
    }
}

fun main() {
    flow_example_test()

    uiTest()
}

fun uiTest() = runBlocking {
    val job = Fragment().onCreate()
    delay(100)
    job.cancel()
}

fun flow_example_test() = runBlocking {
    val dispatcher = Dispatchers.Unconfined
    val testCollector = flowOf(1, 2, 3).test(this, dispatcher, "flow_example_test")

    testCollector
        .isEqualTo(1, 2, 3)
}


/*
*
* DON'T CHANGE CLASS BELOW THIS LINE  ---------------------------------------
*
* */

class RemoteApi : Api {

    private val users = mutableListOf(
        UserDto(1, "John Doe", 1000),
        UserDto(2, "Marc Test", 200),
        UserDto(3, "Anna Lawson", 450)
    )

    override suspend fun getUsers(): UsersResponse = UsersResponse(users)

    override suspend fun removeUser(id: Int) {
        if (!users.removeIf { id == it.id }) {
            throw IllegalStateException("404 resource not found")
        }
    }
}

class CustomMutableStateFlow<T>(val delegate: kotlinx.coroutines.flow.MutableStateFlow<T>) :
    kotlinx.coroutines.flow.MutableStateFlow<T> by delegate {
    override suspend fun emit(value: T) {
        println("delay")
        delay(10)
        delegate.emit(value)
    }
}

fun <T> MutableStateFlow(initial: T): kotlinx.coroutines.flow.MutableStateFlow<T> =
    CustomMutableStateFlow(kotlinx.coroutines.flow.MutableStateFlow<T>(initial))

class MemoryCache(cacheSize: Int = MAX_CACHE_SIZE) : Cache {

    private val cache: MutableMap<String, kotlinx.coroutines.flow.MutableStateFlow<Any?>> =
        object : LinkedHashMap<String, kotlinx.coroutines.flow.MutableStateFlow<Any?>>() {
            override fun removeEldestEntry(
                eldest: MutableMap.MutableEntry<String, kotlinx.coroutines.flow.MutableStateFlow<Any?>>?
            ): Boolean = size > cacheSize
        }.let { Collections.synchronizedMap(it) }

    override suspend fun put(key: String, value: Any?) {
        if (cache.containsKey(key)) {
            cache[key]?.emit(value)
        } else {
            cache[key] = kotlinx.coroutines.flow.MutableStateFlow(value)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> get(key: String): T? {
        return cache[key]?.value as? T
    }

    override suspend fun remove(key: String) {
        cache.remove(key)
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <T> observe(key: String): Flow<T?> {
        val state = cache[key] ?: put(
            key,
            null
        ).let { cache[key]!! }
        return state.map {
            it as? T
        }
    }

    companion object {
        private const val MAX_CACHE_SIZE = Int.MAX_VALUE
    }
}

interface View {
    fun render()
}

class ListView {
    fun render(vararg views: View) {
        renderInternal(views)
    }

    fun render(views: List<View>) {
        renderInternal(views.toTypedArray())
    }

    private fun renderInternal(views: Array<out View>) {
        views.forEach { it.render() }
    }
}

class RowView(val content: String) : View {
    override fun render() {
        println(content)
    }
}

fun <T> Flow<T>.test(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    testName: String
): TestCollector<T> {
    return TestCollector(scope, dispatcher, this, testName).test()
}

class TestCollector<T>(
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
    private val flow: Flow<T>,
    private val testName: String
) {
    private val values = mutableListOf<T>()
    private lateinit var job: Job

    fun test(): TestCollector<T> {
        job = scope.launch(dispatcher) {
            flow.toCollection(values)
        }
        return this
    }

    suspend fun values(): List<T> {
        delay(100)
        cancelIfActive()
        return values
    }

    suspend fun isEqualTo(expected: List<T>) {
        delay(100)
        cancelIfActive()
        assertEquals(testName, expected, values)
    }

    suspend fun isEqualTo(vararg expected: T) {
        delay(100)
        cancelIfActive()
        assertEquals(testName, expected.toList(), values)
    }

    private fun cancelIfActive() {
        if (job.isActive) {
            job.cancel()
        }
    }
}

fun assertEquals(testName: String, expected: Any, values: Any) {
    if (values != expected) {
        println("$testName FAILED:\nexpected: $expected\nActual: $values")
    } else {
        println("$testName PASSED")
    }
}