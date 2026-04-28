package unit.trig

import RealMathModule
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals

class Cos {
    private val module = RealMathModule()
    private val iterations = 100

    companion object {
        @JvmStatic
        fun cosValueCases(): Stream<Arguments> = Stream.of(
            Arguments.of(0.0, 1.0),
            Arguments.of(PI / 3, 0.5),
            Arguments.of(PI / 2, 0.0),
            Arguments.of(PI, -1.0),
            Arguments.of(-PI / 3, 0.5)
        )
    }

    @ParameterizedTest(name = "cos({0}) = {1}")
    @MethodSource("cosValueCases")
    fun cosCORRECT(x: Double, expected: Double) {
        val actual = module.cosine(x, iterations)
        assertEquals(expected, actual, 1e-6)
    }
}