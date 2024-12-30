package advent.util.parser

class StringParser(internal var input: String) {
    fun mayBe(value: String): Boolean = input.startsWith(value)

    fun mayBe(regex: Regex): Boolean {
        val matchResult = regex.find(input)
        return matchResult != null && matchResult.range.first == 0
    }

    fun mayRead(value: String): String? {
        if (!mayBe(value)) {
            return null
        }

        input = input.substring(value.length)
        skipWhitespace()
        return value
    }

    fun mayRead(regex: Regex): String? {
        val matchResult = regex.find(input) ?: return null
        if (matchResult.range.first != 0) {
            return null
        }

        val result = matchResult.value

        input = input.substring(result.length)
        skipWhitespace()
        return result
    }

    private fun skipWhitespace() {
        mayRead("\\s+".toRegex())
    }
}
