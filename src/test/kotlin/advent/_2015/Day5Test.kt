package advent._2015

import advent.DayTest
import advent.TestInput

class Day5Test : DayTest<Day5>(::Day5) {
    override fun part1TestInput(): TestInput =
        mapOf(
            "ugknbfddgicrmopn" to "1",
            "aaa" to "1",
            "jchzalrnumimnmhp" to "0",
            "haegwjzuvuyypxyu" to "0",
            "dvszwmarrgswjxmb" to "0"
        )

    override fun part2TestInput(): TestInput =
        mapOf(
            "qjhvhtzxzqqjkmpb" to "1",
            "xxyxx" to "1",
            "uurcxstgmygtbstg" to "0",
            "ieodomkazucvgmuy" to "0",
            "xyxy" to "1",
            "aaaxyx" to "0",
            "ueihvxviirnooomi" to "0"
        )

}