package unit.log

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class Ln {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun lnValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(1.0, 0.0),
            Arguments.of(2.0, 0.6931471805599453),
            Arguments.of(5.0, 1.6094379124341003),
            Arguments.of(10.0, 2.302585092994046)
        )

        @JvmStatic
        fun lnUndefinedCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0),
            Arguments.of(-1.0),
            Arguments.of(-10.0)
        )
    }

    @ParameterizedTest(name = "ln({0}) = {1}")
    @MethodSource("lnValueCases")
    fun lnCORRECT(x: Double, expected: Double) {
        val actual = module.log(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }

    @ParameterizedTest(name = "ln undefined at {0}")
    @MethodSource("lnUndefinedCases")
    fun lnUNDEFINED(x: Double) {
        Assertions.assertThrows(ArithmeticException::class.java) {
            module.log(x, iterations)
        }
    }
}