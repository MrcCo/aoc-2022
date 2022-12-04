package com.mrcco.input.reader

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

val readForDayFour: (String) -> (List<Pair<IntRange, IntRange>>) = { filename ->
    File("$resourceFolderPath/$filename")
        .readLines()
        .map {
            val (first, second, third, fourth) = it.split(",", "-")
            Pair(first.toInt()..second.toInt(), third.toInt()..fourth.toInt())
        }
}
