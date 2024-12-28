package advent._2015

import advent.DayTest
import advent.TestInput

class Day1Test : DayTest<Day1>(::Day1) {
    override fun part1TestInput(): TestInput =
        mapOf(
            "(())" to "0",
            "()()" to "0",
            "(((" to "3",
            "(()(()(" to "3",
            "))(((((" to "3",
            "())" to "-1",
            "))(" to "-1",
            ")))" to "-3",
            ")())())" to "-3"
        )

    override fun part2TestInput(): TestInput =
        mapOf(
            ")" to "1",
            "()())" to "5"
        )

}