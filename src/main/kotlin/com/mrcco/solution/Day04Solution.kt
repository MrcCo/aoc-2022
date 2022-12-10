package com.mrcco.solution

import com.mrcco.input.reader.readForDayFour
import java.lang.Integer.min

class Day04Solution : Solution<Set<Pair<IntRange, IntRange>>, Int>() {

    override val read = readForDayFour

    override fun solveSecond(input: Set<Pair<IntRange, IntRange>>): Int {
        return input.count { (it.first intersect it.second).isNotEmpty() }
    }

    override fun solveFirst(input: Set<Pair<IntRange, IntRange>>): Int {
        return input.count { (it.first intersect it.second).size == min(it.first.count(), it.second.count()) }
    }
}
