package unit.log

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class Log2 {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun log2ValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(1.0, 0.0),
            Arguments.of(2.0, 1.0),
            Arguments.of(8.0, 3.0),
            Arguments.of(32.0, 5.0)
        )

        @JvmStatic
        fun log2UndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(-2.0)
        )
    }

    @ParameterizedTest(name = "log2({0}) = {1}")
    @MethodSource("log2ValueCases")
    fun log2CORRECT(x: Double, expected: Double) {
        val actual = module.log2(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "log2 undefined at {0}")
    @MethodSource("log2UndefinedCases")
    fun log2UNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.log2(x, iterations)
        }
    }
}