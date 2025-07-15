package org.example.dsl

import org.example.dsl.LazyConfiguration.ConfigurationsMapBuilder
import kotlin.reflect.KProperty

data class SomeConfiguration(val name: String)
data class SomeOtherConfiguration(val age: Int)


data class LazyConfiguration private constructor(private val underLaying: MutableMap<Class<out Any>, Lazy<Any>>) :
    MutableMap<Class<out Any>, Lazy<Any>> by underLaying {

    @DslMarker
    annotation class LazyConfiguration

    @LazyConfiguration
    class ConfigurationsMapBuilder {
        val configurations = LazyConfiguration(mutableMapOf())

        fun build(): org.example.dsl.LazyConfiguration {
            return configurations
        }
    }
}

inline operator fun <reified Configuration : Any> LazyConfiguration.getValue(
    thisRef: Any?,
    property: KProperty<*>
): Configuration = (this[Configuration::class.java] as? Lazy<Configuration>)?.value
    ?: throw IllegalArgumentException("No configuration available for key")

inline fun <reified Configuration : Any> ConfigurationsMapBuilder.addLazy(noinline initializer: () -> Configuration) {
    this.configurations[Configuration::class.java] = lazy(initializer)
}

fun buildConfigurationsMap(init: ConfigurationsMapBuilder.() -> Unit): org.example.dsl.LazyConfiguration {
    val builder = ConfigurationsMapBuilder()
    builder.init()
    return builder.build()
}

class ComponentBuilder(private val lazyConfigurationMap: LazyConfiguration) {
    val someConfiguration: SomeConfiguration by lazyConfigurationMap
    val someOtherConfiguration: SomeOtherConfiguration by lazyConfigurationMap

    fun build() {
        println("Uruchomienie programu")
        println(someConfiguration.name)
        println(someOtherConfiguration.age)
        println(someConfiguration.name)
    }
}


object MainDslLazy {

    fun main() {
        val configurationsMap = buildConfigurationsMap {
            addLazy {
                println("Akcja wykonana raz")
                SomeConfiguration("Tomek")
            }
            addLazy { SomeOtherConfiguration(40) }
        }

//        LazyConfiguration(mapOf())

//        val configurationsMap = LazyConfigurationMap(mutableMapOf())
//        configurationsMap.put(lazy { SomeConfiguration("Tomek") })
//        configurationsMap.put(lazy { SomeOtherConfiguration(40) })
        println(configurationsMap)
        val componentBuilder = ComponentBuilder(configurationsMap)
        println("Tworzenie componentu")
        componentBuilder.build()
    }

}