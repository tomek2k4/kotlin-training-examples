package org.example.dsl

data class SomeConfiguration(val name: String)
data class SomeOtherConfiguration(val age: Int)

data class LazyConfigurationMap(val underLaying: Map<String, Lazy<Any>>)

class ConfigurationsMapBuilder {
    private val configurations = mutableMapOf<String,Lazy<Any>>()

    infix fun String.to(target: Lazy<Any>){
        configurations[this] = target
    }

    fun build(): LazyConfigurationMap {
        return LazyConfigurationMap(configurations)
    }
}

fun buildConfigurationsMap(init: ConfigurationsMapBuilder.() -> Unit): LazyConfigurationMap {
    val builder = ConfigurationsMapBuilder()
    builder.init()
    return builder.build()
}


inline fun <reified Configuration : Any> LazyConfigurationMap.get(key: String) =
    this.underLaying[key] as? Lazy<Configuration> ?: throw IllegalArgumentException("No configuration available for key: $key")

class ComponentBuilder(private val lazyConfigurationMap: LazyConfigurationMap) {
    val someConfiguration: SomeConfiguration by lazyConfigurationMap.get("name")
    fun build() {
        println(someConfiguration.name)
    }
}

object MainDslLazy {

    fun main() {
        val configurationsMap = buildConfigurationsMap {
            "name" to lazy<Any> { SomeConfiguration("Tomek") }
            "age" to lazy<Any> { SomeOtherConfiguration(40) }
        }

        println(configurationsMap)
        val componentBuilder = ComponentBuilder(configurationsMap)
        println("Tworzenie componentu")
        componentBuilder.build()
    }

}