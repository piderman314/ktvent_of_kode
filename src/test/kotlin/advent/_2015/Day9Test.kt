package advent._2015

import advent.DayTest
import advent.TestInput

class Day9Test : DayTest<Day9>(::Day9) {
    override fun part1TestInput(): TestInput =
        mapOf(
            """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
            """
                .trimIndent() to "605")

    override fun part2TestInput(): TestInput =
        mapOf(
            """
                London to Dublin = 464
                London to Belfast = 518
                Dublin to Belfast = 141
            """
                .trimIndent() to "982")
}
