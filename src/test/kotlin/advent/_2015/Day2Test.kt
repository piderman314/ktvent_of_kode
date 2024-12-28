package advent._2015

import advent.DayTest
import advent.TestInput

class Day2Test : DayTest<Day2>(::Day2) {
    override fun part1TestInput(): TestInput =
        mapOf(
            "2x3x4" to "58",
            "1x1x10" to "43",
        )

    override fun part2TestInput(): TestInput =
        mapOf(
            "2x3x4" to "34",
            "1x1x10" to "14",
        )

}