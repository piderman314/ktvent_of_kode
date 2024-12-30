package advent.util.parser

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class LexerTest {

    @Test
    fun testLexer() {
        val lexer = Lexer(TestTokenDefinitions.entries)

        // Operator definition takes precedence over Identifier
        assertEquals(
            listOf(
                TestTokenDefinitions.OPERATOR_AND.toToken("AND"),
                TestTokenDefinitions.NUMBER.toToken("15"),
                TestTokenDefinitions.IDENTIFIER.toToken("id")),
            lexer.tokenize("AND 15     id"))

        // Without the space, the AND15 becomes an identifier
        assertEquals(
            listOf(TestTokenDefinitions.IDENTIFIER.toToken("AND15"), TestTokenDefinitions.IDENTIFIER.toToken("id")),
            lexer.tokenize("AND15     id"))

        // Unknown token, expect an exception
        val lexerException = assertThrows<LexerException> { lexer.tokenize("AND15     id ((WHOOPS") }
        assertEquals("Unknown token ((WHOOPS", lexerException.message)
    }

    @Test
    fun testLexerWithoutOperators() {
        val lexer = Lexer(listOf(TestTokenDefinitions.NUMBER, TestTokenDefinitions.IDENTIFIER))

        // Without Operator, the AND becomes an Identifier
        assertEquals(
            listOf(
                TestTokenDefinitions.IDENTIFIER.toToken("AND"),
                TestTokenDefinitions.NUMBER.toToken("15"),
                TestTokenDefinitions.IDENTIFIER.toToken("id")),
            lexer.tokenize("AND 15     id"))
    }
}

enum class TestTokenDefinitions(
    override val definitionType: TokenDefinitionType,
    override val string: String?,
    override val regex: Regex?
) : TokenDefinition<TestTokenDefinitions> {

    OPERATOR_AND("AND"),
    OPERATOR_OR("OR"),
    NUMBER("\\d+".toRegex()),
    IDENTIFIER("[A-Za-z][A-Za-z0-9]*".toRegex()),
    ;

    constructor(string: String) : this(TokenDefinitionType.STRING, string, null)

    constructor(regex: Regex) : this(TokenDefinitionType.REGEX, null, regex)

    override val tokenType: TestTokenDefinitions = this

    fun toToken(value: String): Token<TestTokenDefinitions> = Token(this, value)
}
