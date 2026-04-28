package unit.trig

import RealMathModule
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI

class Sin {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun sinValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0, 0.0),
            Arguments.of(PI / 6, 0.5),
            Arguments.of(PI / 2, 1.0),
            Arguments.of(-PI / 6, -0.5),
            Arguments.of(-PI / 2, -1.0),
            Arguments.of(-3* PI /2, 1.0),
            Arguments.of(PI, 0.0)
        )
    }

    @ParameterizedTest(name = "sin({0}) = {1}")
    @MethodSource("sinValueCases")
    fun sinCORRECT(x: Double, expected: Double) {
        val actual = module.sine(x, iterations)
        Assertions.assertEquals(expected, actual, 1e-6)
    }
}