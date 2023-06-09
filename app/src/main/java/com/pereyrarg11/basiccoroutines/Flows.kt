package com.pereyrarg11.basiccoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    //coldFlow()
    //cancelFlow()
    //flowOperators()
    terminalFlowOperators()
}

fun terminalFlowOperators() {
    runBlocking {
        newTopic("Terminal operators")
        //toListOperator()
        //singleOperator()
        //firstOperator()
        //lastOperator()
        //reduceOperator()
        //foldOperator()
        //flowBuffer()
        //flowConflation()
        //zipOperator()
        //combineOperator()
        //flatMapConcatOperator()
        flatMapMergeOperator()
    }
}

suspend fun flatMapMergeOperator() {
    newTopic("flatMapMerge")
    getCitiesAsFlow()
        .flatMapMerge { city ->
            println("last 3 days in $city")
            getDataToFlatFlow(city)
        }
        .map { setFormat(it) }
        .collect {
            println(it)
        }
}

suspend fun flatMapConcatOperator() {
    newTopic("flatMapConcat")
    getCitiesAsFlow()
        .flatMapConcat { city ->
            println("last 3 days in $city")
            getDataToFlatFlow(city)
        }
        .map { setFormat(it) }
        .collect {
            println(it)
        }
}

fun getDataToFlatFlow(city: String): Flow<Float> = flow {
    (1..2).forEach {
        println("Morning")
        emit(Random.nextInt(10, 30).toFloat())

        delay(100)
        println("Afternoon")
        emit(20 + it + kotlin.random.Random.nextFloat())
    }
}

suspend fun combineOperator() {
    newTopic("combine")
    getDataByFlowWithStaticDelay()
        .map { setFormat(it) }
        .combine(getMatchResultFlow()) { degrees, matchResult ->
            "$matchResult, $degrees"
        }
        .collect {
            delay(100)
            println(it)
        }
}

suspend fun zipOperator() {
    newTopic("zip")
    getDataByFlowWithStaticDelay()
        .map { setFormat(it) }
        .zip(getMatchResultFlow()) { degrees, matchResult ->
            "$matchResult, $degrees"
        }
        .collect {
            delay(100)
            println(it)
        }
}

suspend fun flowConflation() {
    newTopic("conflate")
    val time = measureTimeMillis {
        getMatchResultFlow()
            .conflate()
            .collect {
                delay(100)
                println(it)
            }
    }
    println("time: $time ms")
}

suspend fun flowBuffer() {
    newTopic("buffer")
    val time = measureTimeMillis {
        getDataByFlowWithStaticDelay()
            .map { setFormat(it) }
            .buffer()
            .collect {
                delay(500)
                println(it)
            }
    }
    println("time: $time ms")
}

suspend fun foldOperator() {
    newTopic("fold {}")
    val initialValue = 20f
    val saving = getDataByFlow()
        .fold(initialValue) { acc, value ->
            acc + value
        }
    println("saving: $saving")
}

suspend fun reduceOperator() {
    newTopic("reduce {}")
    val saving = getDataByFlow()
        .reduce { accumulator, value ->
            println("accumulator: $accumulator, value: $value")
            accumulator + value
        }
    println("saving: $saving")
}

suspend fun firstOperator() {
    newTopic("first()")
    val first = getDataByFlow().first()
    println("first: $first")
}

suspend fun lastOperator() {
    newTopic("last()")
    val last = getDataByFlow().last()
    println("last: $last")
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
