package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //coldFlow()
    cancelFlow()
}

fun cancelFlow() {
    runBlocking {
        newTopic("Cancel flow")
        val job = launch {
            getDataByFlow().collect {
                println(it)
            }
        }
        delay(randomSleep() * 2)
        job.cancel()
    }
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
