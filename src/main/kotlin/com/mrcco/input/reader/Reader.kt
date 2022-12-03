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
