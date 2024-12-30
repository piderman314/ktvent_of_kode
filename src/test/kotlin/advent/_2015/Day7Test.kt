package advent._2015

import advent.DayTest
import advent.TestInput

class Day7Test : DayTest<Day7>(::Day7) {
    override fun part1TestInput(): TestInput =
        mapOf(
            """456 -> y
               x AND y -> d
               x OR y -> e
               x LSHIFT 2 -> f
               y RSHIFT 2 -> g
               123 -> x
               NOT x -> h
               NOT y -> i""" to
                "")

    override fun part2TestInput(): TestInput = mapOf()
}
