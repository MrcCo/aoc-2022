package com.mrcco.solution

import com.mrcco.input.reader.readForDayTwo

class Day02Solution : Solution<List<Pair<Char, Char>>, Int>() {

    override val read = readForDayTwo

    private val yourScoringMap = mapOf(
        Pair('X', 1),
        Pair('Y', 2),
        Pair('Z', 3),
    )

    private val yourResponseToWin = mapOf(
        Pair('A', 'Y'),
        Pair('B', 'Z'),
        Pair('C', 'X'),
    )

    private val yourResponseToDraw = mapOf(
        Pair('A', 'X'),
        Pair('B', 'Y'),
        Pair('C', 'Z'),
    )

    private val yourResponseToLose = mapOf(
        Pair('A', 'Z'),
        Pair('B', 'X'),
        Pair('C', 'Y'),
    )

    override fun solveSecond(input: List<Pair<Char, Char>>): Int {
        return input.sumOf { gameResultForTaskTwo(it) }
    }

    override fun solveFirst(input: List<Pair<Char, Char>>): Int {
        return input.sumOf { gameResult(it) }
    }

    private fun gameResult(game: Pair<Char, Char>): Int {
        return yourScoringMap[game.second]!!.plus(
            if (game.second == yourResponseToWin[game.first]) 6
            else if (game.second == yourResponseToDraw[game.first]) 3
            else 0
        )
    }

    private fun gameResultForTaskTwo(game: Pair<Char, Char>): Int {
        return when (game.second) {
            'X' -> yourScoringMap[yourResponseToLose[game.first]]!!
            'Y' -> yourScoringMap[yourResponseToDraw[game.first]]!!.plus(3)
            else -> yourScoringMap[yourResponseToWin[game.first]]!!.plus(6)
        }
    }
}