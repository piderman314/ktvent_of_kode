package advent._2015

import advent.Day
import java.time.LocalDate

class Day8 : Day(LocalDate.of(2015, 12, 8)) {

    override fun part1(input: String): String = input.lines().sumOf { it.length - unescape(it).length }.toString()

    override fun part2(input: String): String = input.lines().sumOf { escape(it).length - it.length }.toString()

    private fun escape(input: String): String {
        var escaped = input.replace("\\", "\\\\").replace("\"", "\\\"")
        return "\"$escaped\""
    }

    private fun unescape(input: String): String {
        val builder = StringBuilder()
        var state = State.START
        var hex = 0

        for (c in input.toCharArray()) {
            when (state) {
                State.START -> {
                    if (c != '"') {
                        throw IllegalStateException("In state $state and encountered $c instead of \" in string $input")
                    }

                    state = State.IN_STRING
                }
                State.IN_STRING -> {
                    if (c == '\\') {
                        state = State.ESCAPED
                    } else if (c == '"') {
                        state = State.START
                    } else {
                        builder.append(c)
                    }
                }
                State.ESCAPED -> {
                    if (c == '"' || c == '\\') {
                        builder.append(c)
                        state = State.IN_STRING
                    } else if (c == 'x') {
                        state = State.HEXADECIMAL_1
                    } else {
                        throw IllegalStateException(
                            "In state $state and encountered $c instead of \\, \" or x in string $input")
                    }
                }
                State.HEXADECIMAL_1 -> {
                    if (!c.isDigit() && c !in 'a'..'f') {
                        throw IllegalStateException(
                            "In state $state and encountered $c instead of a hex digit in string $input")
                    }
                    hex = c.digitToInt(16) * 16
                    state = State.HEXADECIMAL_2
                }
                State.HEXADECIMAL_2 -> {
                    if (!c.isDigit() && c !in 'a'..'f') {
                        throw IllegalStateException(
                            "In state $state and encountered $c instead of a hex digit in string $input")
                    }
                    hex += c.digitToInt(16)
                    builder.append(hex.toChar())
                    state = State.IN_STRING
                }
            }
        }

        return builder.toString()
    }

    private enum class State {
        START,
        IN_STRING,
        ESCAPED,
        HEXADECIMAL_1,
        HEXADECIMAL_2
    }
}
