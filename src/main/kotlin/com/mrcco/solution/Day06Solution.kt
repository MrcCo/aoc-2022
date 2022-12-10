package com.mrcco.solution

import com.mrcco.input.reader.readForDaySix

class DaySixSolution : Solution<String, Int>() {

    override val read = readForDaySix

    override fun solveSecond(input: String): Int {
        return findMarker(input, 14)
    }

    override fun solveFirst(input: String): Int {
        return findMarker(input, 4)
    }

    private fun findMarker(input: String, markerSize: Int) = (input.windowedSequence(markerSize, 1).withIndex()
        .dropWhile { !it.value.allUnique() }
        .first()
        .index + markerSize)
}

fun String.allUnique(): Boolean = toSet().size == length

