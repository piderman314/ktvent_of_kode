package advent._2015

import advent.DayTest
import advent.TestInput

class Day6Test : DayTest<Day6>(::Day6) {
    override fun part1TestInput(): TestInput =
        mapOf(
            "turn on 0,0 through 2,2" to "9",
            "turn on 0,0 through 999,999" to "1000000",
            "turn on 0,0 through 999,999\ntoggle 0,0 through 999,0" to "999000",
            "turn on 0,0 through 999,999\ntoggle 0,0 through 999,0\nturn off 499,499 through 500,500" to "998996")

    override fun part2TestInput(): TestInput =
        mapOf(
            "turn on 0,0 through 2,2" to "9",
            "toggle 0,0 through 999,999" to "2000000",
            "turn on 0,0 through 999,999\ntoggle 0,0 through 999,0" to "1002000",
            "turn on 0,0 through 999,999\ntoggle 0,0 through 999,0\nturn off 499,499 through 500,500" to "1001996")
}
