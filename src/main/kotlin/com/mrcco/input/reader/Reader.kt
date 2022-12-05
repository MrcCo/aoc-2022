package com.mrcco.input.reader

import com.mrcco.solution.Cargo
import com.mrcco.solution.Instruction
import java.io.File

private const val resourceFolderPath = "src/main/resources"

val readForDayOne: (String) -> (List<Int>) = { filename ->
    File("$resourceFolderPath/$filename")
        .readText()
        .split("\n\n")
        .map { it.split("\n").sumOf { s -> s.toInt() } }
}

val readForDayTwo: (String) -> (List<Pair<Char, Char>>) = { filename ->
    File("$resourceFolderPath/$filename")
        .readLines()
        .map { Pair(it[0], it[2]) }
}

val readForDayThree: (String) -> (List<String>) = { filename ->
    File("$resourceFolderPath/$filename")
        .readLines()
}

val readForDayFour: (String) -> (Set<Pair<IntRange, IntRange>>) = { filename ->
    File("$resourceFolderPath/$filename")
        .readLines()
        .map {
            val (first, second, third, fourth) = it.split(",", "-")
            Pair(first.toInt()..second.toInt(), third.toInt()..fourth.toInt())
        }.toSet()
}

val readForDayFive: (String) -> (Pair<Cargo, Sequence<Instruction>>) = { filename ->

    val text = File("$resourceFolderPath/$filename")
        .readText()

    val cargo = Cargo(9)
    text.substringBefore("\n\n")
        .lines()
        .dropLast(1)
        .reversed()
        .map {
            it.windowed(3, 4)
                .withIndex()
                .forEach { chunk ->
                    if (chunk.value[1].isLetter())
                        cargo.stacks[chunk.index].add(chunk.value[1])
                }

        }

    val instructionRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val instructionString = text.substringAfter("\n\n")
    val instructions = instructionRegex.findAll(instructionString)
        .map {
            val (amount, from, to) = it.destructured
            Instruction(amount.toInt(), from.toInt() - 1, to.toInt() - 1)
        }

    Pair(cargo, instructions)
}
