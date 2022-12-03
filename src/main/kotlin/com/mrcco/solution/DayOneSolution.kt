package com.mrcco.solution

import com.mrcco.input.reader.readForDayOne

class DayOneSolution: Solution<List<Int>, Int>() {

    override val read = readForDayOne

    override fun solveFirst(input: List<Int>): Int {
        return input.max()
    }

    override fun solveSecond(input: List<Int>): Int {
        return input.sortedDescending().take(3).sum()
    }

}