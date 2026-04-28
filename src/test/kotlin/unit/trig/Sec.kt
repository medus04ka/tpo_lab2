package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class Sec {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun secValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0, 1.0),
            Arguments.of(PI / 3, 2.0),
            Arguments.of(PI, -1.0)
        )

        @JvmStatic
        fun secUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(PI / 2),
            Arguments.of(-PI / 2)
        )
    }

    @ParameterizedTest(name = "sec({0}) = {1}")
    @MethodSource("secValueCases")
    fun secCORRECT(x: Double, expected: Double) {
        val actual = module.secant(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "sec undefined at {0}")
    @MethodSource("secUndefinedCases")
    fun secUNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.secant(x, iterations)
        }
    }
}