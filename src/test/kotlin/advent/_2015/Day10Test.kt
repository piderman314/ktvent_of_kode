package advent._2015

import advent.DayTest
import advent.TestInput

class Day10Test : DayTest<Day10>(::Day10) {
    override fun part1TestInput(): TestInput =
        mapOf(
            "1#1" to "2", "11#1" to "2", "21#1" to "4", "1211#1" to "6", "111221#1" to "6", "1#3" to "4", "1#5" to "6")

    override fun part2TestInput(): TestInput = mapOf("3113322113#50" to "1")
}
