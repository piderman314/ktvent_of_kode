package advent

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

abstract class DayTest<D : Day>(constructor: () -> D) {

    private val day: D = constructor.invoke()

    @TestFactory
    fun part1Tests() = part1TestInput().map { (input, expected) ->
        DynamicTest.dynamicTest("Test") {
            assertEquals(
                expected,
                day.part1(input)
            )
        }
    }

    @Test
    fun part1ForReal() {
        println(day.realInputFile)
        val fileName = this.javaClass.getResource(day.realInputFile)?.file
        assertNotNull(fileName)

        val input = File(fileName).readText(Charsets.UTF_8)

        println(day.part1(input))
    }

    abstract fun part1TestInput(): Map<String, String>

}