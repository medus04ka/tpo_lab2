package unit.log

import RealMathModule
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class RealLogTest {

    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun logValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of("ln", 1.0, 0.0),
            Arguments.of("log2", 8.0, 3.0),
            Arguments.of("log5", 25.0, 2.0),
            Arguments.of("log10", 100.0, 2.0)
        )

        @JvmStatic
        fun logUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of("ln", -2.0),
            Arguments.of("log2", 0.0),
            Arguments.of("log5", 0.0),
            Arguments.of("log10", 0.0)
        )
    }

    @ParameterizedTest(name = "{0}({1}) = {2}")
    @MethodSource("logValueCases")
    fun LogCORRECT(fn: String, x: Double, expected: Double) {
        val actual = when (fn) {
            "ln" -> module.log(x, iterations)
            "log2" -> module.log2(x, iterations)
            "log5" -> module.log5(x, iterations)
            "log10" -> module.log10(x, iterations)
            else -> error("УНКНОВН function")
        }

        assertEquals(expected, actual, 1e-9)
    }

    @ParameterizedTest(name = "{0} undefined at {1}")
    @MethodSource("logUndefinedCases")
    fun UNDFLog(fn: String, x: Double) {
        assertThrows(ArithmeticException::class.java) {
            when (fn) {
                "ln" -> module.log(x, iterations)
                "log2" -> module.log2(x, iterations)
                "log5" -> module.log5(x, iterations)
                "log10" -> module.log10(x, iterations)
                else -> error("УНКНОВН function")
            }
        }
    }
}