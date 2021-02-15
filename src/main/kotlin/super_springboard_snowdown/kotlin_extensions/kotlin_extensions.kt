package super_springboard_snowdown.kotlin_extensions

import java.util.*

fun main() {
    applyAction("I'm Mary Poppins!", "Y'all!") { message: String ->
        println(message.uuid())
    }
    println("I'm Mary Poppins!".uuid())
}

fun applyAction(vararg s: String, function: (String) -> Unit) {
    s.forEach(function)
}

fun String.uuid(): String =
    UUID.nameUUIDFromBytes(this.encodeToByteArray()).toString()