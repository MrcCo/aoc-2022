package com.mrcco.solution

import com.mrcco.input.reader.readForDayNine
import kotlin.math.*

class Day09Solution : Solution<List<Pair<String, Int>>, Int>() {
    override val read = readForDayNine

    private var ropeCoordinates:MutableList<Pair<Int, Int>> = mutableListOf()
    private var tailVisits:MutableSet<Pair<Int, Int>> = mutableSetOf()
    override fun solveSecond(input: List<Pair<String, Int>>): Int {

        init(10)

        input.forEach {

            handleMovement(it)

        }
        return tailVisits.size
    }

    override fun solveFirst(input: List<Pair<String, Int>>): Int {

        init(2)

        input.forEach {

            handleMovement(it)

        }
        return tailVisits.size

    }

    private fun init(numberOfKnots: Int) {
        ropeCoordinates = mutableListOf()
        tailVisits = mutableSetOf()
        repeat(numberOfKnots) {
            ropeCoordinates.add(Pair(0, 0))
        }
    }

    private fun handleMovement(movement: Pair<String, Int>) {

        for (count in 0 until movement.second) {

            val newRope = mutableListOf<Pair<Int, Int>>()
            newRope.add(
                when (movement.first) {
                    "U" -> ropeCoordinates[0].moveUp()
                    "D" -> ropeCoordinates[0].moveDown()
                    "L" -> ropeCoordinates[0].moveLeft()
                    "R" -> ropeCoordinates[0].moveRight()
                    else -> ropeCoordinates[0]
                }
            )

            for (knot in 1 until ropeCoordinates.size) {
                if (newRope[knot - 1].distanceFrom(ropeCoordinates[knot]) > 1) {
                    newRope.add(follow(ropeCoordinates[knot], newRope[knot - 1]))
                } else {
                    newRope.add(ropeCoordinates[knot])
                }
            }
            tailVisits.add(newRope.last())
            ropeCoordinates = newRope

        }


    }

    fun Pair<Int, Int>.moveUp(): Pair<Int, Int> = Pair(this.first + 1, this.second)
    fun Pair<Int, Int>.moveDown(): Pair<Int, Int> = Pair(this.first - 1, this.second)
    fun Pair<Int, Int>.moveRight(): Pair<Int, Int> = Pair(this.first, this.second + 1)
    fun Pair<Int, Int>.moveLeft(): Pair<Int, Int> = Pair(this.first, this.second - 1)

    fun Pair<Int, Int>.realDistanceFrom(other: Pair<Int, Int>): Float = sqrt(
        ((this.first - other.first) * (this.first - other.first) +
                (this.second - other.second) * (this.second - other.second)).toFloat()
    )

    fun Pair<Int, Int>.distanceFrom(other: Pair<Int, Int>): Int = floor(
        sqrt(
            ((this.first - other.first) * (this.first - other.first) +
                    (this.second - other.second) * (this.second - other.second)).toFloat()
        )
    ).toInt()

    fun follow(follower: Pair<Int, Int>, other: Pair<Int, Int>): Pair<Int, Int> {
        val distance = follower.realDistanceFrom(other)

        return if (distance > 2) {
            // diagonal
            Pair(
                if (abs(other.first - follower.first) == 2) (other.first + follower.first) / 2 else other.first,
                if (abs(other.second - follower.second) == 2) (other.second + follower.second) / 2 else other.second
            )
        } else if (distance == 2.toFloat()) {
            // horizontal; vertical
            Pair((other.first + follower.first) / 2, (other.second + follower.second) / 2)
        } else {
            Pair(0, 0)
        }

    }


}