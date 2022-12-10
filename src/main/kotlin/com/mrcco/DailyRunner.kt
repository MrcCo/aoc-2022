package com.mrcco

import com.mrcco.solution.*
import java.lang.System.currentTimeMillis
import java.time.LocalDate


val dailySolution = mapOf(
    Pair(1, Day01Solution()),
    Pair(2, Day02Solution()),
    Pair(3, DayThreeSolution()),
    Pair(4, Day04Solution()),
    Pair(5, DayFiveSolution()),
    Pair(6, DaySixSolution()),
    Pair(7, DaySevenSolution()),
    Pair(8, Day08Solution()),
    Pair(9, Day09Solution()),
    Pair(10, Day10Solution()),
)


fun main(args: Array<String>) {

    val task = if(args.getOrNull(0) != null) args[0].toInt() else LocalDate.now().dayOfMonth

    val startTime = currentTimeMillis()

    dailySolution[task]?.solve("${task}.txt")

    val endTime = currentTimeMillis()
    println("Execution time = ${endTime - startTime}ms")

}



