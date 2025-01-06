package advent._2015

import advent.DayTest
import advent.TestInput

class Day8Test : DayTest<Day8>(::Day8) {
    override fun part1TestInput(): TestInput =
        mapOf("\"\"" to "2", "\"abc\"" to "2", "\"aaa\\\"aaa\"" to "3", "\"aa\\x27\"" to "5")

    override fun part2TestInput(): TestInput =
        mapOf("\"\"" to "4", "\"abc\"" to "4", "\"aaa\\\"aaa\"" to "6", "\"aa\\x27\"" to "5")
}
