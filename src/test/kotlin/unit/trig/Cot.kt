package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class Cot {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun cotValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(PI / 4, 1.0),
            Arguments.of(-PI / 4, -1.0),
            Arguments.of(PI / 2, 0.0)
        )

        @JvmStatic
        fun cotUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(PI),
            Arguments.of(-PI)
        )
    }

    @ParameterizedTest(name = "cot({0}) = {1}")
    @MethodSource("cotValueCases")
    fun cotCORRECT(x: Double, expected: Double) {
        val actual = module.cotangent(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "cot undefined at {0}")
    @MethodSource("cotUndefinedCases")
    fun cotUNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.cotangent(x, iterations)
        }
    }
}