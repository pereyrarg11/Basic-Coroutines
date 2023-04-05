package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

fun main() {
    //coldFlow()
    //cancelFlow()
    //flowOperators()
    terminalFlowOperators()
}

fun terminalFlowOperators() {
    runBlocking {
        newTopic("Terminal operators")
        toListOperator()
        singleOperator()
    }
}

suspend fun singleOperator() {
    newTopic("single()")

    val single = getDataByFlow()
        .take(1)
        .single()

    println("single: $single")
}

suspend fun toListOperator() {
    newTopic("toList()")

    val list = getDataByFlow().toList()

    println("list: $list")
}

fun flowOperators() {
    runBlocking {
        newTopic("Operators")

        newTopic("take { }")
        getDataByFlow()
            .take(3)
            .map {
                setFormat(it)
            }
            .collect {
                println(it)
            }

        newTopic("map()")
        getDataByFlow()
            .map {
                setFormat(it)
                setFormat(convertCelsiusToFahrenheit(it), "F")
            }

        newTopic("filter { }")
        getDataByFlow()
            .filter {
                it < 23
            }
            .map {
                setFormat(it)
            }

        newTopic("transform { }")
        getDataByFlow()
            .transform {
                emit(setFormat(it))
                emit(setFormat(convertCelsiusToFahrenheit(it), "F"))
            }
            .collect {
                println(it)
            }
    }
}

fun convertCelsiusToFahrenheit(celsius: Float): Float =
    (celsius * 9 / 5) + 32

fun setFormat(temp: Float, degree: String = "C"): String =
    String.format(Locale.getDefault(), "%.1f'$degree", temp)

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
