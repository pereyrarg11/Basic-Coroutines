package com.pereyrarg11.basiccoroutines

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