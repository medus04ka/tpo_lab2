package unit.log

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class Log5 {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun log5ValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(1.0, 0.0),
            Arguments.of(5.0, 1.0),
            Arguments.of(25.0, 2.0),
            Arguments.of(125.0, 3.0)
        )

        @JvmStatic
        fun log5UndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(-5.0)
        )
    }

    @ParameterizedTest(name = "log5({0}) = {1}")
    @MethodSource("log5ValueCases")
    fun log5CORRECT(x: Double, expected: Double) {
        val actual = module.log5(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "log5 undefined at {0}")
    @MethodSource("log5UndefinedCases")
    fun log5UNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.log5(x, iterations)
        }
    }
}