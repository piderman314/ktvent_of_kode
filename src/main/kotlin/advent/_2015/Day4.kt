package advent._2015

import advent.Day
import java.security.MessageDigest
import java.time.LocalDate

class Day4 : Day(LocalDate.of(2015, 12, 4)) {
    override fun part1(input: String): String {
        var i = 0L
        while (true) {
            if ("$input$i".md5().startsWith("00000")) {
                return i.toString()
            }
            i++
        }
    }

    override fun part2(input: String): String {
        var i = 0L
        while (true) {
            if ("$input$i".md5().startsWith("000000")) {
                return i.toString()
            }
            i++
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.toHexString()
}