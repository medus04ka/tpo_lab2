package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class Tan {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun tanValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0, 0.0),
            Arguments.of(PI / 4, 1.0),
            Arguments.of(-PI / 4, -1.0)
        )

        @JvmStatic
        fun tanUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(PI / 2),
            Arguments.of(-PI / 2)
        )
    }

    @ParameterizedTest(name = "tan({0}) = {1}")
    @MethodSource("tanValueCases")
    fun tanCORRECT(x: Double, expected: Double) {
        val actual = module.tangent(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "tan undefined at {0}")
    @MethodSource("tanUndefinedCases")
    fun tanUNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.tangent(x, iterations)
        }
    }
}