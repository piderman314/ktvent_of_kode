package advent.util.parser

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

private const val PLUS = "PLUS"
private const val A = "a"
private const val NUMBER = "\\d+"
private const val IDENTIFIER = "[a-z]+"

class StringTokenizerTest {

    @Test
    fun testParser() {
        val parser = StringTokenizer("a PLUS 5")

        assertTrue(parser.mayBe(A), "${parser.input} starts with $A")
        assertTrue(parser.mayBe(IDENTIFIER.toRegex()), "${parser.input} starts with $IDENTIFIER")
        assertFalse(parser.mayBe(PLUS), "${parser.input} starts with $PLUS")
        assertNull(parser.mayRead(PLUS), "$PLUS can be read from ${parser.input}")
        assertFalse(parser.mayBe(NUMBER.toRegex()), "${parser.input} starts with $NUMBER")
        assertNull(parser.mayRead(NUMBER.toRegex()), "$NUMBER can be read from ${parser.input}")

        assertEquals(A, parser.mayRead(IDENTIFIER.toRegex()), "$IDENTIFIER can be read from ${parser.input}")

        assertTrue(parser.mayBe(PLUS), "${parser.input} starts with $PLUS")
        assertFalse(parser.mayBe(PLUS.substring(0, 2)), "${parser.input} starts with ${PLUS.substring(0, 2)}")
        assertFalse(parser.mayBe(NUMBER.toRegex()), "${parser.input} starts with $NUMBER")

        assertEquals(PLUS, parser.mayRead(PLUS), "$PLUS can be read from ${parser.input}")

        assertTrue(parser.mayBe(NUMBER.toRegex()), "${parser.input} starts with $NUMBER")
        assertEquals("5", parser.mayRead(NUMBER.toRegex()), "$NUMBER can be read from ${parser.input}")

        assertTrue(parser.isEmpty(), "Parser input is now empty")
    }

    @Test
    fun testNextToken() {
        val parser = StringTokenizer("a PLUS )")

        assertFalse(parser.isEmpty())
        assertEquals(A, parser.nextToken())
        assertFalse(parser.isEmpty())
        assertEquals(PLUS, parser.nextToken())
        assertFalse(parser.isEmpty())
        assertEquals(")", parser.nextToken())
        assertTrue(parser.isEmpty())
    }
}
