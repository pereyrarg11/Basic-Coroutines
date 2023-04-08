package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

private const val SEPARATOR = "=========="

fun startMessage() {
    println("Starting coroutine -${Thread.currentThread().name}-")
}

fun endMessage() {
    println("Finishing coroutine -${Thread.currentThread().name}-")
}

fun newTopic(topic: String) {
    println("$SEPARATOR $topic $SEPARATOR")
}

fun randomSleep(): Long = Random.nextLong(500, 2_000)

fun getDataByFlow(): Flow<Float> {
    return flow {
        (1..5).forEach {
            println("processing data...")
            delay(randomSleep())
            emit(20 + it + Random.nextFloat())
        }
    }
}

fun getDataByFlowWithStaticDelay(): Flow<Float> {
    return flow {
        (1..5).forEach {
            println("processing data...")
            delay(300)
            emit(20 + it + Random.nextFloat())
        }
    }
}