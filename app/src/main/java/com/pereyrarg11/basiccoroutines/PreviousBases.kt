package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() {
    //lambda()
    //threads()
    coroutinesVsThreads()
}

fun coroutinesVsThreads() {
    newTopic("Coroutines vs Threads")
    runBlocking {
        (1..1_000_000).forEach {
            launch {
                delay(randomSleep())
                print("*")
            }
        }
    }
}

fun threads() {
    newTopic("Threads")
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

fun lambda() {
    newTopic("Lambda")
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
