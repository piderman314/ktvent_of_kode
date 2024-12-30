package advent._2015

import advent.Day
import advent._2015.day7.BNFDefinitions
import advent._2015.day7.TokenDefinitions
import advent._2015.day7.bnfDefinitions
import advent.util.parser.Lexer
import advent.util.parser.Parser
import java.time.LocalDate

class Day7 : Day(LocalDate.of(2015, 12, 7)) {
    override fun part1(input: String): String {
        val memory = mutableMapOf<String, Long>()

        runProgram(input, memory)

        return memory["a"]?.toString() ?: ""
    }

    override fun part2(input: String): String {
        val memory = mutableMapOf<String, Long>()

        runProgram(input.replace("44430 -> b", "3176 -> b"), memory)

        return memory["a"]?.toString() ?: ""
    }

    private fun runProgram(input: String, memory: MutableMap<String, Long>) {
        val assignments =
            input
                .lines()
                .map {
                    val lexer = Lexer(TokenDefinitions.entries)
                    val tokens = lexer.tokenize(it) // .also(::println)
                    val parser = Parser(bnfDefinitions, tokens)

                    parser.parse(BNFDefinitions.ASSIGNMENT)
                }
                .toMutableList()

        while (assignments.isNotEmpty()) {
            val assignment = assignments.removeFirst()!!
            try {
                assignment.evaluate(memory)
            } catch (e: IllegalStateException) {
                if (!(e.message ?: "").startsWith("Unknown identifier")) {
                    throw e
                }
                assignments.add(assignment)
            }
        }

        println(memory)
    }
}
