package advent

import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

typealias TestInput = Map<String, String>

abstract class DayTest<D : Day>(constructor: () -> D) {

    private val day: D = constructor.invoke()

    @TestFactory fun part1Tests() = partTests(::part1TestInput, Day::part1, 1)

    @TestFactory fun part2Tests() = partTests(::part2TestInput, Day::part2, 2)

    @Test
    fun part1ForReal() {
        partForReal(day, Day::part1)
    }

    @Test
    fun part2ForReal() {
        partForReal(day, Day::part2)
    }

    private fun partTests(input: () -> TestInput, part: D.(String) -> String, partNumber: Int) =
        input().map { (input, expected) ->
            DynamicTest.dynamicTest("Test for Part $partNumber") { assertEquals(expected, day.part(input)) }
        }

    private fun partForReal(day: D, part: D.(String) -> String) {
        val fileName = this.javaClass.getResource(day.realInputFile)?.file
        assertNotNull(fileName)

        val input = File(fileName).readText(Charsets.UTF_8)

        println(day.part(input))
    }

    abstract fun part1TestInput(): TestInput

    abstract fun part2TestInput(): TestInput
}
