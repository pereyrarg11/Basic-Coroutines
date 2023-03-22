package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    //globalScope()
    //suspendFun()
    newTopic("Coroutine constructors")
    //cRunBlocking()
    cLaunch()
    readLine()
}

fun cLaunch() {
    runBlocking {
    newTopic("launch")
        launch {
            startMessage()
            delay(randomSleep())
            println("launch...")
            endMessage()
        }
    }
}

fun cRunBlocking() {
    newTopic("Run blocking")
    runBlocking {
        startMessage()
        delay(randomSleep())
        println("run blocking...")
        endMessage()
    }
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
