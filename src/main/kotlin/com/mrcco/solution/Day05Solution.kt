package com.mrcco.solution

import com.mrcco.input.reader.readForDayFive

class DayFiveSolution : Solution<Pair<Cargo, Sequence<Instruction>>, String>() {
    override val read = readForDayFive

    override fun solve(filename: String) {

        println(solveFirst(read(filename)))
        println(solveSecond(read(filename)))

    }

    override fun solveSecond(input: Pair<Cargo, Sequence<Instruction>>): String {

        val (cargo, instructions) = input

        instructions.forEach { cargo.performInstruction9001(it) }

        return cargo.readTopOfStacks()
    }

    override fun solveFirst(input: Pair<Cargo, Sequence<Instruction>>): String {
        val (cargo, instructions) = input

        instructions.forEach { cargo.performInstruction9000(it) }

        return cargo.readTopOfStacks()
    }


}

data class Cargo(val numberOfStacks: Int) {

    val stacks = mutableListOf<MutableList<Char>>()

    init {
        repeat(numberOfStacks) {
            stacks.add(mutableListOf())
        }
    }

    fun performInstruction9000(instruction: Instruction) {

        repeat(instruction.amount) {
            stacks[instruction.to].add(stacks[instruction.from].removeLast())
        }

    }

    fun performInstruction9001(instruction: Instruction) {

        stacks[instruction.to].addAll(stacks[instruction.from].takeLast(instruction.amount))
        stacks[instruction.from].removeLast(instruction.amount)

    }

    fun readTopOfStacks(): String {
        return stacks.map { it.last() }.fold("") { top, item -> top + item }
    }

}

data class Instruction(val amount: Int, val from: Int, val to: Int)

fun MutableList<Char>.removeLast(n: Int) {
    repeat(n) {
        this.removeLast()
    }
}