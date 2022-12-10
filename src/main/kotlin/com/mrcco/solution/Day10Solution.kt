package com.mrcco.solution

import com.mrcco.input.reader.readForDayTen

class Day10Solution:Solution<List<DeviceInstruction>, Long>() {

    override val read = readForDayTen

    override fun solveSecond(input: List<DeviceInstruction>): Long {
        runAllInstructionsOnDevice(input, DrawingDevice())
        return -1
    }

    override fun solveFirst(input: List<DeviceInstruction>): Long {
        val device = CalculatingDevice()
        runAllInstructionsOnDevice(input, device)
        return device.report
    }


    private fun runAllInstructionsOnDevice(
        input: List<DeviceInstruction>,
        device: Device
    ) {
        input.forEach {
            it.device = device
            it.perform()
        }
    }

}

interface Device {
    fun performSingleCycleWithNoChange()
    fun performSingleCycleWithIncrement(increment: Long)
}

class CalculatingDevice(private var clock: Long = 0, private var register: Long = 1, var report: Long = 0): Device {
    override fun performSingleCycleWithNoChange() {
        clock++
        reportIfNeeded()
    }

    override fun performSingleCycleWithIncrement(increment: Long) {
        performSingleCycleWithNoChange()
        register += increment
    }

    private fun reportIfNeeded() {
        if ((clock - 20) % 40 == 0L) {
            report += clock * register
        }
    }


}

class DrawingDevice(private var clock: Long = 0, private var register: Long = 1): Device {

    override fun performSingleCycleWithNoChange() {

        breakLineIfNeeded()
        clock++
        printPixel()

    }

    override fun performSingleCycleWithIncrement(increment: Long) {

        performSingleCycleWithNoChange()
        register += increment

    }

    private fun printPixel() {
        if ((clock - 1) % 40 in register - 1..register + 1) {
            print("#")
        } else {
            print(".")
        }
    }

    private fun breakLineIfNeeded() {
        if (clock % 40 == 0L) {
            println()
        }
    }


}

abstract class DeviceInstruction {

    var device: Device? = null
    abstract fun perform()

}

class AddXOp(private val x: Long): DeviceInstruction() {

    override fun perform() {

        device!!.performSingleCycleWithNoChange()
        device!!.performSingleCycleWithIncrement(x)

    }


}

class NoOp(): DeviceInstruction() {

    override fun perform() {

        device!!.performSingleCycleWithNoChange()

    }


}
