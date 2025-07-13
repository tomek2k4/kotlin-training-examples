package org.example.dsl

data class SomeConfiguration(val name: String)
data class SomeOtherConfiguration(val age: Int)

data class LazyConfigurationMap(val underLaying: MutableMap<Class<out Any>, Lazy<Any>>)

inline fun <reified Configuration : Any> LazyConfigurationMap.get(): Lazy<Configuration> =
    this.underLaying[Configuration::class.java] as? Lazy<Configuration>
        ?: throw IllegalArgumentException("No configuration available for key")

inline fun <reified Configuration : Any> ConfigurationsMapBuilder.addLazy(addLazyMethod: ConfigurationsMapBuilder.() -> Lazy<Configuration>) {
    this.configurations.underLaying[Configuration::class.java] = addLazyMethod()
}

class ConfigurationsMapBuilder {
    val configurations = LazyConfigurationMap(mutableMapOf())

    fun build(): LazyConfigurationMap {
        return configurations
    }
}


fun buildConfigurationsMap(init: ConfigurationsMapBuilder.() -> Unit): LazyConfigurationMap {
    val builder = ConfigurationsMapBuilder()
    builder.init()
    return builder.build()
}


class ComponentBuilder(private val lazyConfigurationMap: LazyConfigurationMap) {
    val someConfiguration: SomeConfiguration by lazyConfigurationMap.get()
    val someOtherConfiguration: SomeOtherConfiguration by lazyConfigurationMap.get()

    fun build() {
        println(someConfiguration.name)
        println(someOtherConfiguration.age)
    }
}

object MainDslLazy {

    fun main() {
        val configurationsMap = buildConfigurationsMap {
            addLazy { lazy { SomeConfiguration("Tomek") } }
            addLazy { lazy { SomeOtherConfiguration(40) } }
        }

//        val configurationsMap = LazyConfigurationMap(mutableMapOf())
//        configurationsMap.put(lazy { SomeConfiguration("Tomek") })
//        configurationsMap.put(lazy { SomeOtherConfiguration(40) })
        println(configurationsMap)
        val componentBuilder = ComponentBuilder(configurationsMap)
        println("Tworzenie componentu")
        componentBuilder.build()
    }

}