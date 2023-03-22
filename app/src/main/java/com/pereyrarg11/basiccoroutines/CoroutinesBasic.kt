package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    //globalScope()
    suspendFun()
    readLine()
}

fun suspendFun() {
    newTopic("Suspend fun")
    Thread.sleep(randomSleep())
    GlobalScope.launch {
        startMessage()
        delay(randomSleep())
        endMessage()
    }
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
