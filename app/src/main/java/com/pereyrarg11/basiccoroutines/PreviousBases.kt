package com.pereyrarg11.basiccoroutines

fun main() {
    lambda()
}

fun lambda() {
    println(times(5, 8))
    timesAsLambda(5, 8) { result ->
        println(result)
    }
}

fun timesAsLambda(a: Int, b: Int, callback: (result: Int) -> Unit) {
    callback(a * b)
}

fun times(a: Int, b: Int): Int {
    return a * b
}
