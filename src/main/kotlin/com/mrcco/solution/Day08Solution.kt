package com.mrcco.solution

import com.mrcco.input.reader.readForDayEight

class Day08Solution : Solution<List<List<Int>>, Int>() {
    override val read = readForDayEight

    override fun solveFirst(input: List<List<Int>>): Int {

        val highestToTheTop = Array(input[0].size) { input[0][it] }
        val highestToTheLeft = Array(input.size) { input[it][0] }
        var visibleTreeCount = (input.size * 2 + input[0].size * 2 - 4)

        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].size - 1) {

                // check up & left
                if (input[i][j] > highestToTheTop[j] || input[i][j] > highestToTheLeft[i]) {
                    visibleTreeCount += 1
                    if (input[i][j] > highestToTheTop[j])
                        highestToTheTop[j] = input[i][j]

                    if (input[i][j] > highestToTheLeft[i])
                        highestToTheLeft[i] = input[i][j]

                    continue
                }


                // check right
                var isTallestInRow = true
                for (right in j + 1 until input[i].size) {
                    if (input[i][j] <= input[i][right]) {
                        isTallestInRow = false
                        break
                    }
                }
                if (isTallestInRow) {
                    visibleTreeCount += 1
                    continue
                }

                // check down
                var isTallestInCol = true
                for (bot in i + 1 until input.size) {
                    if (input[i][j] <= input[bot][j]) {
                        isTallestInCol = false
                        break
                    }
                }
                if (isTallestInCol) {
                    visibleTreeCount += 1
                }

            }

        }
        return visibleTreeCount

    }

    override fun solveSecond(input: List<List<Int>>): Int {

        var bestScenicScore = 0
        for (i in 1 until input.size - 1) {
            for (j in 1 until input[i].size - 1) {

                val score = calculateScenicScore(input, i, j)
                if (score > bestScenicScore) {
                    bestScenicScore = score
                }
            }

        }
        return bestScenicScore
    }

    private fun calculateScenicScore(input: List<List<Int>>, row: Int, col: Int): Int {
        var rightScore = 0
        for (i in col + 1 until input[row].size) {
            if (input[row][col] > input[row][i])
                rightScore += 1

            if (input[row][col] <= input[row][i]) {
                rightScore += 1
                break
            }
        }

        var leftScore = 0
        for (i in col - 1 downTo 0) {
            if (input[row][col] > input[row][i])
                leftScore += 1

            if (input[row][col] <= input[row][i]) {
                leftScore += 1
                break
            }
        }

        var downScore = 0
        for (i in row + 1 until input.size) {
            if (input[row][col] > input[i][col])
                downScore += 1

            if (input[row][col] <= input[i][col]) {
                downScore += 1
                break
            }
        }

        var upScore = 0
        for (i in row - 1 downTo 0) {
            if (input[row][col] > input[i][col])
                upScore += 1

            if (input[row][col] <= input[i][col]) {
                upScore += 1
                break
            }
        }

        return leftScore * upScore * rightScore * downScore
    }

}