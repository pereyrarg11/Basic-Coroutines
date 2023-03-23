package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.*

fun main() {
    //dispatchers()
    nested()
}

fun nested() {
    runBlocking {
        newTopic("Nested")
        val job = launch {
            startMessage()
            launch {
                startMessage()
                delay(randomSleep())
                println("nested coroutine")
                endMessage()
            }

            val nestedJob = launch(Dispatchers.IO) {
                startMessage()

                launch(newSingleThreadContext("pereyrarg11")) {
                    startMessage()
                    println("pereyrarg11 coroutine")
                    endMessage()
                }

                delay(randomSleep())
                println("IO")
                endMessage()
            }
            delay(randomSleep()/4)
            nestedJob.cancel()
            println("nested job was cancelled")

            var sum = 0
            (1..100).forEach {
                sum += it
                delay(randomSleep()/100)
            }
            println("Sum=$sum")
            endMessage()
        }

        delay(randomSleep()/2)
        job.cancel()
        println("Job was cancelled")
    }
}

fun dispatchers() {
    runBlocking {
        newTopic("Dispatchers")
        launch {
            startMessage()
            println("nothing...")
            endMessage()
        }

        launch(Dispatchers.IO) {
            startMessage()
            println("IO...")
            endMessage()
        }

        launch(Dispatchers.Unconfined) {
            startMessage()
            println("Unconfined...")
            endMessage()
        }

        // run this only on Android apps
        /*launch(Dispatchers.Main) {
            startMessage()
            println("Unconfined...")
            endMessage()
        }*/

        launch(Dispatchers.Default) {
            startMessage()
            println("Default...")
            endMessage()
        }

        launch(newSingleThreadContext("pereyrarg11")) {
            startMessage()
            println("My custom coroutine with a Dispatcher...")
            endMessage()
        }

        newSingleThreadContext("CustomDispatcher").use { myContext ->
            launch {
                startMessage()
                println("Other custom coroutine with Dispatcher...")
                endMessage()
            }
        }
    }
}