package com.mrcco.solution

import com.mrcco.input.reader.readForDaySeven

class DaySevenSolution : Solution<SmartElfFileSystem, Long>() {
    override val read = readForDaySeven

    private val totalDiskSize = 70_000_000
    private val requiredUnusedSpace = 30_000_000
    private val sizeMemo = mutableMapOf<Directory, Long>()

    override fun solveSecond(input: SmartElfFileSystem): Long {
        val currentlyAvailableSpace = totalDiskSize - input.root.size()
        return input.root.getAllSubDirectories()
            .map { sizeMemo.getOrDefault(it, it.size()) }
            .filter { currentlyAvailableSpace + it > requiredUnusedSpace }
            .min()

    }

    override fun solveFirst(input: SmartElfFileSystem): Long {

        return input.root.getAllSubDirectories()
            .map { sizeMemo.getOrDefault(it, it.size()) }
            .filter { it < 100000 }.sum()

    }

}

interface FileSystem {
    fun handleSingleInput(input: String)
}

class ElfFileSystem(inputs: List<String>): FileSystem {

    val directorySizes = mutableMapOf<String, Long>()
    val currentPath = mutableListOf<String>()
    var rootSize: Long = 0

    init {
        inputs.forEach { handleSingleInput(it) }
        fixRootSize()
    }

    override fun handleSingleInput(input: String) {


        if (input.startsWith("$ ls") || input.startsWith("dir")) {
            return
        }

        if (input.startsWith("$ cd")) {
            val dir = input.substringAfter("$ cd").trim()
            if (dir == "..") {
                val currentDirSize = directorySizes[currentPath.joinToString("/")]!!
                currentPath.removeLast()

                directorySizes.merge(
                    currentPath.joinToString("/"),
                    currentDirSize,
                    Long::plus
                )
            } else {
                currentPath.add(dir)
            }

        } else {
            val (size, _) = input.split(" ")
            directorySizes.merge(
                currentPath.joinToString("/"),
                size.toLong(),
                Long::plus
            )
            rootSize += size.toLong()
        }

    }

    private fun fixRootSize() {
        directorySizes["/"] = rootSize
    }
}

class SmartElfFileSystem(inputs: List<String>) {

    val root = Directory("/")
    var workingDirectory = root

    init {
        inputs.drop(1).forEach { handleSingleInput(it) }
    }

    private fun handleSingleInput(input: String) {

        when {
            input.startsWith("$ ls") -> {
                return
            }

            input.startsWith("$ cd ..") -> {
                workingDirectory = workingDirectory.parent!!
            }

            input.startsWith("$ cd ") -> {
                workingDirectory = workingDirectory.subDirectories.find {
                    it.name == input.substringAfter("$ cd ").trim()
                }!!
            }

            input.startsWith("dir ") -> {
                workingDirectory.subDirectories.add(
                    Directory(
                        input.substringAfter("dir ").trim(),
                        parent = workingDirectory
                    )
                )
            }


            else -> {
                workingDirectory.files.add(File(input.split(" ")[0].toLong()))
            }
        }
    }
}

data class Directory(
    val name: String,
    val parent: Directory? = null,
    val files: MutableList<File> = mutableListOf(),
    val subDirectories: MutableList<Directory> = mutableListOf()
) {

    fun getAllSubDirectories(): List<Directory> {
        return this.subDirectories + this.subDirectories.flatMap { it.getAllSubDirectories() }
    }

    fun size(): Long {
        return files.sumOf { it.size } + subDirectories.sumOf { it.size() }
    }

}

data class File(val size: Long)