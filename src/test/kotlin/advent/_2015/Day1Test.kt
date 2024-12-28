package advent._2015

import advent.DayTest

class Day1Test : DayTest<Day1>(::Day1) {
    override fun part1TestInput(): Map<String, String> =
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

}