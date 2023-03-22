package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    globalScope()
    readLine()
}

fun globalScope() {
    newTopic("Global scope")
    GlobalScope.launch {
        startMessage()
        delay(randomSleep())
        println("My coroutine")
        endMessage()
    }
}
