package com.mrcco.solution

typealias FileName = String

abstract class Solution<INPUT, OUTPUT> {

    abstract val read: (FileName) -> INPUT

    fun solve(filename: String) {

        val input = read(filename)

        println(solveFirst(input))
        println(solveSecond(input))

    }


    abstract fun solveFirst(input: INPUT): OUTPUT
    abstract fun solveSecond(input: INPUT): OUTPUT
}