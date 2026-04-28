package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class RealTrigTest {

    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun trigValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of("sin", 0.0, 0.0),
            Arguments.of("cos", 0.0, 1.0),
            Arguments.of("tan", PI / 4, 1.0),
            Arguments.of("cot", PI / 4, 1.0),
            Arguments.of("sec", 0.0, 1.0),
            Arguments.of("csc", PI / 2, 1.0)
        )

        @JvmStatic
        fun trigUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of("tan", PI / 2),
            Arguments.of("cot", 0.0),
            Arguments.of("sec", PI / 2),
            Arguments.of("csc", 0.0)
        )
    }

    @ParameterizedTest(name = "{0}({1}) = {2}")
    @MethodSource("trigValueCases")
    fun trigCORRECTFulll(fn: String, x: Double, expected: Double) {
        val actual = when (fn) {
            "sin" -> module.sine(x, iterations)
            "cos" -> module.cosine(x, iterations)
            "tan" -> module.tangent(x, iterations)
            "cot" -> module.cotangent(x, iterations)
            "sec" -> module.secant(x, iterations)
            "csc" -> module.cosecant(x, iterations)
            else -> error("Unknown function")
        }

        assertEquals(expected, actual, 1e-9)
    }

    @ParameterizedTest(name = "{0} undefined at {1}")
    @MethodSource("trigUndefinedCases")
    fun UNDFTrigZZZ(fn: String, x: Double) {
        assertThrows(ArithmeticException::class.java) {
            when (fn) {
                "tan" -> module.tangent(x, iterations)
                "cot" -> module.cotangent(x, iterations)
                "sec" -> module.secant(x, iterations)
                "csc" -> module.cosecant(x, iterations)
                else -> error("Unknown function")
            }
        }
    }
}