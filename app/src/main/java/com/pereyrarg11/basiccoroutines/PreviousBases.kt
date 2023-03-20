package com.pereyrarg11.basiccoroutines

import kotlin.concurrent.thread
import kotlin.random.Random.Default.nextLong

fun main() {
    lambda()
    threads()
}

fun threads() {
    println("timesAsThread: ${timesAsThread(5, 8)}")
    timesWithThreadAndLambda(5, 8) {
        println("timesWithThreadAndLambda: $it")
    }
}

fun timesAsThread(a: Int, b: Int): Int {
    var result = 0

    thread {
        Thread.sleep(randomSleep())
        result = a * b
    }

    Thread.sleep(2_100)
    return result
}

fun timesWithThreadAndLambda(a: Int, b: Int, callback: (result: Int) -> Unit) {
    var result = 0

    thread {
        Thread.sleep(randomSleep())
        result = a * b
        callback(result)
    }
}

fun randomSleep(): Long = nextLong(500, 2_000)

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
