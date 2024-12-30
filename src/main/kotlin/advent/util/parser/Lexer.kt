package advent.util.parser

class Lexer<T : Enum<T>>(tokenDefinitions: Collection<TokenDefinition<T>>) {
    private val definitions = tokenDefinitions.groupBy { it.definitionType }

    fun tokenize(input: String): List<Token<T>> {
        val parser = StringTokenizer(input)
        val result = mutableListOf<Token<T>>()

        var token = nextToken(parser)
        while (token != null) {
            result.add(token)
            token = nextToken(parser)
        }

        if (!parser.isEmpty()) throw LexerException("Unknown token ${parser.nextToken()}")

        return result
    }

    private fun nextToken(parser: StringTokenizer): Token<T>? {
        // Do exact definitions first for keywords, boolean literals etc
        for (definition in definitions.getOrDefault(TokenDefinitionType.STRING, listOf())) {
            val value = parser.mayRead(definition.string!!) ?: continue
            return Token(definition.tokenType, value)
        }

        // Next to regex definitions for identifiers, numeric literals etc
        for (definition in definitions.getOrDefault(TokenDefinitionType.REGEX, listOf())) {
            val value = parser.mayRead(definition.regex!!) ?: continue
            return Token(definition.tokenType, value)
        }

        return null
    }
}

class LexerException(message: String) : Exception(message)