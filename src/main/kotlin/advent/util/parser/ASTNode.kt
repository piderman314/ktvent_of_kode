package advent.util.parser

interface ASTNode<T : Enum<T>, M, R> {

    val astType: T

    fun evaluate(memory: M): R
}
