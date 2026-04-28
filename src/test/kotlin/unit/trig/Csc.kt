package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class Csc {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun cscValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(PI / 2, 1.0),
            Arguments.of(-PI / 2, -1.0),
            Arguments.of(PI / 6, 2.0)
        )

        @JvmStatic
        fun cscUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(PI),
            Arguments.of(-PI)
        )
    }

    @ParameterizedTest(name = "csc({0}) = {1}")
    @MethodSource("cscValueCases")
    fun cscCORRECT(x: Double, expected: Double) {
        val actual = module.cosecant(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "csc undefined at {0}")
    @MethodSource("cscUndefinedCases")
    fun cscUNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.cosecant(x, iterations)
        }
    }
}