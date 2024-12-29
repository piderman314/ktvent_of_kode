package advent._2015

import advent.DayTest
import advent.TestInput

class Day3Test : DayTest<Day3>(::Day3) {
    override fun part1TestInput(): TestInput = mapOf(">" to "2", "^>v<" to "4", "^v^v^v^v^v" to "2")

    override fun part2TestInput(): TestInput = mapOf("^v" to "3", "^>v<" to "3", "^v^v^v^v^v" to "11")
}
