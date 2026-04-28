package unit.log

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class Log10 {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun log10ValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(1.0, 0.0),
            Arguments.of(10.0, 1.0),
            Arguments.of(100.0, 2.0),
            Arguments.of(1000.0, 3.0)
        )

        @JvmStatic
        fun log10UndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(-10.0)
        )
    }

    @ParameterizedTest(name = "log10({0}) = {1}")
    @MethodSource("log10ValueCases")
    fun log10CORRECT(x: Double, expected: Double) {
        val actual = module.log10(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "log10 undefined at {0}")
    @MethodSource("log10UndefinedCases")
    fun log10UNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.log10(x, iterations)
        }
    }
}