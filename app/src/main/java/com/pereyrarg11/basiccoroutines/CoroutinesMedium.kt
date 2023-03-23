package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() {
    dispatchers()
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
