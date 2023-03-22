package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.*

fun main() {
    //globalScope()
    //suspendFun()
    newTopic("Coroutine constructors")
    //cRunBlocking()
    //cLaunch()
    //cAsync()
    //job()
    deferred()
    readLine()
}

fun deferred() {
    runBlocking {
        newTopic("Deferred")
        val deferred = async {
            startMessage()
            delay(randomSleep())
            println("deferred")
            endMessage()
            times(5,6)
        }
        println("Deferred $deferred")
        println("value of Deferred.await ${deferred.await()}")

        val result = async {
            times(5,8)
        }.await()
        println("Result: $result")
    }
}

fun job() {
    runBlocking {
        newTopic("Job")
        val job = launch {
            startMessage()
            delay(2_100)
            println("job...")
            endMessage()
        }
        println("Job: $job")
        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isCompleted: ${job.isCompleted}")

        delay(randomSleep())
        println("Job cancelled")
        job.cancel()

        println("isActive: ${job.isActive}")
        println("isCancelled: ${job.isCancelled}")
        println("isCompleted: ${job.isCompleted}")
    }
}

fun cAsync() {
    newTopic("async")
    runBlocking {
        val result = async {
            startMessage()
            delay(randomSleep())
            println("async...")
            endMessage()
            8
        }
        println("Result: ${result.await()}")
    }
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
