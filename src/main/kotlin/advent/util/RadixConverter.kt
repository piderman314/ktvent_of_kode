package advent.util

class RadixConverter(val radixChars: String) {

    fun toLong(input: String): Long {
        var result = 0L
        for (c in input) {
            result *= radixChars.length
            result += radixChars.indexOf(c)
        }

        return result
    }

    fun toString(input: Long): String {
        var value = input
        val result = StringBuilder()
        while (value > 0) {
            result.append(radixChars[(value % radixChars.length).toInt()])
            value /= radixChars.length
        }

        return result.toString().reversed()
    }
}
