package advent.util.parser

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class ParserTest {
    private val bnfDefinitions =
        mapOf(
            TestBNFDefinitions.IDENTIFIER to listOf(BNFLeafOption(TestTokenDefinitions.IDENTIFIER, ::Identifier)),
            TestBNFDefinitions.NUMBER to listOf(BNFLeafOption(TestTokenDefinitions.NUMBER, ::Number)),
            TestBNFDefinitions.TERM to
                listOf(
                    BNFTreeOption(listOf(TestBNFDefinitions.IDENTIFIER), ::Term),
                    BNFTreeOption(listOf(TestBNFDefinitions.NUMBER), ::Term)),
            TestBNFDefinitions.OPERATOR to
                listOf(
                    BNFLeafOption(TestTokenDefinitions.OPERATOR_AND, ::Operator),
                    BNFLeafOption(TestTokenDefinitions.OPERATOR_OR, ::Operator)),
            TestBNFDefinitions.EXPRESSION to
                listOf(
                    BNFTreeOption(
                        listOf(TestBNFDefinitions.TERM, TestBNFDefinitions.OPERATOR, TestBNFDefinitions.EXPRESSION),
                        ::Expression),
                    BNFTreeOption(listOf(TestBNFDefinitions.TERM), ::Expression)))

    private val lexer = Lexer(TestTokenDefinitions.entries)

    private val parser = Parser(bnfDefinitions, lexer.tokenize("a AND 15"))

    @Test
    fun testParser() {
        val node = parser.parse(TestBNFDefinitions.EXPRESSION)
        assertNotNull(node)
        assertEquals(TestBNFDefinitions.EXPRESSION, node.astType)
        assertTrue(node is Expression)
        assertEquals(
            listOf(TestBNFDefinitions.TERM, TestBNFDefinitions.OPERATOR, TestBNFDefinitions.EXPRESSION),
            node.nodes.map { it.astType })
        val (identifierTerm, operator, expression) = node.nodes

        assertTrue(identifierTerm is Term)
        val identifier = identifierTerm.nodes.last()
        assertTrue(identifier is Identifier)
        val identifierToken = identifier.token
        assertEquals(TestTokenDefinitions.IDENTIFIER, identifierToken.tokenType)
        assertEquals("a", identifierToken.value)

        assertTrue(operator is Operator)
        val operatorToken = operator.token
        assertEquals(TestTokenDefinitions.OPERATOR_AND, operatorToken.tokenType)
        assertEquals("AND", operatorToken.value)

        assertTrue(expression is Expression)
        assertEquals(listOf(TestBNFDefinitions.TERM), expression.nodes.map { it.astType })
        val numberTerm = expression.nodes.last()
        assertTrue(numberTerm is Term)
        val number = numberTerm.nodes.last()
        assertTrue(number is Number)
        val numberToken = number.token
        assertEquals(TestTokenDefinitions.NUMBER, numberToken.tokenType)
        assertEquals("15", numberToken.value)
    }
}

class Expression(val nodes: List<TestASTNode>) : TestASTNode {
    override val astType = TestBNFDefinitions.EXPRESSION

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        TODO()
    }
}

class Operator(val token: Token<TestTokenDefinitions>) : TestASTNode {
    override val astType = TestBNFDefinitions.OPERATOR

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        TODO()
    }
}

class Term(val nodes: List<TestASTNode>) : TestASTNode {
    override val astType = TestBNFDefinitions.TERM

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        TODO()
    }
}

class Identifier(val token: Token<TestTokenDefinitions>) : TestASTNode {
    override val astType = TestBNFDefinitions.IDENTIFIER

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        TODO()
    }
}

class Number(val token: Token<TestTokenDefinitions>) : TestASTNode {
    override val astType = TestBNFDefinitions.NUMBER

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        TODO()
    }
}

enum class TestBNFDefinitions {
    IDENTIFIER,
    NUMBER,
    TERM,
    OPERATOR,
    EXPRESSION
}

typealias TestASTNode = ASTNode<TestBNFDefinitions, MutableMap<String, Long>, Long>
