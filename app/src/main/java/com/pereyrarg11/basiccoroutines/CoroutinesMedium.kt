package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    }
}
