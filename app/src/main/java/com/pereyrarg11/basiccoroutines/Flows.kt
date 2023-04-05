package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    coldFlow()
}

fun coldFlow() {
    newTopic("Flows are cold")
    runBlocking {
        val dataFlow = getDataByFlow()
        println("waiting...")
        delay(randomSleep())
        dataFlow.collect {
            println(it)
        }
    }
}
