package org.example.dsl
data class Greeting(val specific: Map<String, String>, val default: String)

fun main() {

    val greeting: Greeting = buildGreeting { // this: GreetingBuilder

        "Hello".to("father")  // using String extension member function
        "Hi" to "mother"      // also making use of infix notation

        default = "What's up"
    }

    println(greeting)
}

class GreetingBuilder {
    var default = ""
    private val specific = HashMap<String, String>()

    infix fun String.to (target: String) {
        specific[target] = this
    }

    fun build(): Greeting { return Greeting(specific, default) }
}

fun buildGreeting(init: GreetingBuilder.() -> Unit): Greeting {
    val builder = GreetingBuilder()
    builder.init()
    return builder.build()
}