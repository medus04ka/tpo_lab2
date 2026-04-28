package integration

import FunctionWithModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI

class IntTest {

    private val module = FunctionWithModule(100, RealMathModule())

    companion object {
        @JvmStatic
        fun trig() = Stream.of(Arguments.of(-0.47761, 0.0),
            Arguments.of(-0.3981, 2.95554),
            Arguments.of(-0.547, -1.21938),
            Arguments.of(-0.876, -2.092642),
            Arguments.of(-0.77341, -2.14949),
            Arguments.of(-0.9728, -2.04274),
            Arguments.of(-1.1818, -3.73301),
            Arguments.of(-1.9642, -37.00205),
            Arguments.of(-6.7608, 0.0),
            Arguments.of(-7.05659, -2.14949),
            Arguments.of(-8.24738, -37.00205),
//            Arguments.of(),
//            Arguments.of(),
//            Arguments.of(),
//            Arguments.of()
        )

        @JvmStatic
        fun log() = Stream.of(
            Arguments.of(0.5084, 0.981353),
            Arguments.of(1.279901, -0.000171),
            Arguments.of(1.334609, 0.0),
            Arguments.of(1.68, 0.090186),
            Arguments.of(1.887, 0.497226),
            Arguments.of(1.969, 0.850592),
            Arguments.of(2.0472, 1.347396)
        )
    }

    @ParameterizedTest(name = "trig f({0}) = {1}")
    @MethodSource("trig")
    fun trigPointsAreCorrect(x: Double, expected: Double) {
        assertEquals(expected, module.get(x), 1e-2)
    }

    @ParameterizedTest(name = "log f({0}) = {1}")
    @MethodSource("log")
    fun logPointsAreCorrect(x: Double, expected: Double) {
        assertEquals(expected, module.get(x), 1e-2)
    }

}