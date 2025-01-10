package advent._2015

import advent.Day
import java.time.LocalDate

class Day10 : Day(LocalDate.of(2015, 12, 10)) {

    override fun part1(input: String): String {
        val (i, d) = input.split("#")

        var sequence = i
        //        println(sequence)
        for (j in 0..<d.toInt()) {
            val sb = StringBuilder()

            var current = sequence[0]
            var count = 0
            for (c in sequence.toCharArray()) {
                if (c == current) {
                    count++
                    continue
                }

                sb.append("$count$current")
                //                length += getLengthCached("$count$current", depth - 1)
                current = c
                count = 1
            }

            sb.append("$count$current")
            sequence = sb.toString()
            //            println(sequence)
        }

        return sequence.length.toString()
    }

    override fun part2(input: String): String {
        return part1(input)
    }
}
