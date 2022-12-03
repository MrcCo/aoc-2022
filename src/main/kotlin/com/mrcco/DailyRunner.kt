package com.mrcco

import com.mrcco.solution.DayOneSolution
import com.mrcco.solution.DayThreeSolution
import com.mrcco.solution.DayTwoSolution
import java.lang.System.currentTimeMillis
import java.time.LocalDate


val dailySolution = mapOf(
    Pair(1, DayOneSolution()),
    Pair(2, DayTwoSolution()),
    Pair(3, DayThreeSolution()),
)


fun main(args: Array<String>) {

    val task = if(args.getOrNull(0) != null) args[0].toInt() else LocalDate.now().dayOfMonth

    val startTime = currentTimeMillis()

    dailySolution[task]?.solve("${task}.txt")

    val endTime = currentTimeMillis()
    println("Execution time = ${endTime - startTime}ms")

}



