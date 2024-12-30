package advent._2015.day7

import advent.util.parser.ASTNode
import advent.util.parser.Token

const val MAX_VALUE = 65535L

class Assignment(private val nodes: List<ASTNodeAlias>) : ASTNodeAlias {
    override val astType = BNFDefinitions.ASSIGNMENT

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        val (expression, _, identifier) = nodes

        if (identifier !is Identifier) {
            throw IllegalStateException("Expected identifier but got node of type ${identifier.astType}")
        }

        val value = expression.evaluate(memory)
        memory[identifier.token.value] = value

        return value
    }
}

class BinaryExpression(private val nodes: List<ASTNodeAlias>) : ASTNodeAlias {
    override val astType = BNFDefinitions.EXPRESSION

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        val (term1, operator, term2) = nodes

        if (operator !is Operator || operator.astType != BNFDefinitions.BINARY_OPERATOR) {
            throw IllegalStateException("Expected operator but got node of type ${operator.astType}")
        }

        val value1 = term1.evaluate(memory)
        val value2 = term2.evaluate(memory)

        return when (operator.token.tokenType) {
            TokenDefinitions.OPERATOR_AND -> (value1 and value2) and MAX_VALUE
            TokenDefinitions.OPERATOR_OR -> (value1 or value2) and MAX_VALUE
            TokenDefinitions.OPERATOR_LSHIFT -> (value1 shl value2.toInt()) and MAX_VALUE
            TokenDefinitions.OPERATOR_RSHIFT -> (value1 shr value2.toInt()) and MAX_VALUE
            else -> throw IllegalStateException("Expected a binary operator but got ${operator.token.tokenType}")
        }
    }
}

class UnaryExpression(private val nodes: List<ASTNodeAlias>) : ASTNodeAlias {
    override val astType = BNFDefinitions.EXPRESSION

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        val (operator, term) = nodes

        if (operator !is Operator || operator.astType != BNFDefinitions.UNARY_OPERATOR) {
            throw IllegalStateException("Expected operator but got node of type ${operator.astType}")
        }

        val value1 = term.evaluate(memory)

        return when (operator.token.tokenType) {
            TokenDefinitions.OPERATOR_NOT -> value1.inv() and MAX_VALUE
            else -> throw IllegalStateException("Expected a unary operator but got ${operator.token.tokenType}")
        }
    }
}

class TermExpression(private val nodes: List<ASTNodeAlias>) : ASTNodeAlias {
    override val astType = BNFDefinitions.EXPRESSION

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        val (term) = nodes

        return term.evaluate(memory)
    }
}

class Term(private val nodes: List<ASTNodeAlias>) : ASTNodeAlias {
    override val astType = BNFDefinitions.TERM

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        val (node) = nodes

        return node.evaluate(memory)
    }
}

class Operator(override val astType: BNFDefinitions, val token: Token<TokenDefinitions>) : ASTNodeAlias {
    override fun evaluate(memory: MutableMap<String, Long>): Long {
        throw UnsupportedOperationException("Cannot evaluate an Operator")
    }
}

class Identifier(val token: Token<TokenDefinitions>) : ASTNodeAlias {
    override val astType = BNFDefinitions.IDENTIFIER

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        return memory[token.value] ?: throw IllegalStateException("Unknown identifier ${token.value}")
    }
}

class Number(val token: Token<TokenDefinitions>) : ASTNodeAlias {
    override val astType = BNFDefinitions.NUMBER

    override fun evaluate(memory: MutableMap<String, Long>): Long {
        return token.value.toLong()
    }
}

typealias ASTNodeAlias = ASTNode<BNFDefinitions, MutableMap<String, Long>, Long>
