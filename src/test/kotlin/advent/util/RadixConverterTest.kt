package advent.util

import kotlin.test.assertEquals
import org.junit.jupiter.api.Test

class RadixConverterTest {

    @Test
    fun test() {
        val converter = RadixConverter("0123456789abcdef")
        assertEquals((1234567L).toString(16), converter.toString(1234567L))
        assertEquals("12345abc".toLong(16), converter.toLong("12345abc"))
    }
}
