package com.mrcco.solution

import com.mrcco.input.reader.readForDayThree

class DayThreeSolution : Solution<List<String>, Int>() {

    override val read = readForDayThree

    private val alphabetRange = ('a'..'z') + ('A'..'Z')

    override fun solveSecond(input: List<String>): Int {

        return input.windowed(3, 3).map { findBadge(it) }.sumOf { itemPriority(it) }

    }

    override fun solveFirst(input: List<String>): Int {
        return input.map { Rucksack(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
            .sumOf {
                it.findDuplicateItems().sumOf { dup -> itemPriority(dup) }
            }
    }


    private fun itemPriority(item: Char): Int {
        return alphabetRange.indexOf(item) + 1
    }

    private fun findBadge(rucksacks: List<String>): Char {

        return rucksacks.map{ it.toSet() }
            .reduce{ acc, item -> acc.intersect(item)}
            .single()

    }

}

data class Rucksack(val firstCompartment: String, val secondCompartment: String) {

    private val firstItemSet = firstCompartment.toSet()
    private val secondItemSet = secondCompartment.toSet()

    fun findDuplicateItems(): Set<Char> {
        return firstItemSet.intersect(secondItemSet)
    }

}


