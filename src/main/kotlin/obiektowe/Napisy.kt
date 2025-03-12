package org.example.obiektowe

object Napisy {

    fun main() {
        val a = "Kot"
        var b = "K"


        b = b + "ot"
        if (a === b) {
            println("[ZLE] Rowne")
        } else {
            println("[ZLE] Rozne!!!!")
        }






        if (a == b) {
            println("[DOBRZE] Rowne")
        } else {
            println("[DOBRZE] Rozne!!!!")
        }








        if ("kot".equals(a, ignoreCase = true)) {
            println("Tak to Kot.")
        } else {
            println("To cos innego.")
        }
    }

}