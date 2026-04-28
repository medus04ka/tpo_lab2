package unit

import FunctionWithModule
import MathModule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.pow
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FunctionWithModuleTest {

    @Test
    fun logCORRECTfoRfwm() {
        val mock = mockk<MathModule>()

        every { mock.log(2.0, 100) } returns 1.0
        every { mock.log2(2.0, 100) } returns 2.0
        every { mock.log5(2.0, 100) } returns 3.0
        every { mock.log10(2.0, 100) } returns 4.0

        val function = FunctionWithModule(100, mock)
        val actual = function.get(2.0)

        val expected = (((1.0 * 2.0) * 2.0).pow(3) / 4.0) - (((2.0 * 3.0).pow(3)) / 2.0)
        assertEquals(expected, actual, 1e-9)
    }

    @Test
    fun fullFWM() {
        val mock = mockk<MathModule>()

        every { mock.sine(0.0, 100) } returns 1.0
        every { mock.cosine(0.0, 100) } returns 1.0
        every { mock.tangent(0.0, 100) } returns 1.0
        every { mock.cotangent(0.0, 100) } returns 1.0
        every { mock.secant(0.0, 100) } returns 1.0
        every { mock.cosecant(0.0, 100) } returns 1.0

        val function = FunctionWithModule(100, mock)
        val actual = function.get(0.0)

        val sin = 1.0
        val cos = 1.0
        val tan = 1.0
        val cot = 1.0
        val sec = 1.0
        val csc = 1.0

        val firstPart =
            (((((((((tan + cos).pow(2) + sin) / tan).pow(3)).pow(3)) * cos) * (cot / cos)) / cot) *
                    ((cos.pow(2)).pow(2)))

        val secondPart =
            ((cos * sec) + sin.pow(2)) +
                    ((cot - ((sin * (cot - (((sec + sin) * csc) * (sec / tan)))) - (sec * csc))) - tan)

        val thirdPart =
            (((((cos + cos) - (cot - (csc - cos))) * ((csc / csc).pow(2))).pow(3)) / tan) - tan

        val expected = (firstPart + secondPart) * thirdPart

        assertEquals(expected, actual, 1e-9)
    }

    @ParameterizedTest
    @CsvSource(
        "0.0, trig",
        "-1.0, trig",
        "2.0, log"
    )
    fun full100TrigFWM(x: Double, branch: String) {
        val mock = mockk<MathModule>()

        if (branch == "trig") {
            every { mock.sine(any(), any()) } returns 1.0
            every { mock.cosine(any(), any()) } returns 1.0
            every { mock.tangent(any(), any()) } returns 1.0
            every { mock.cotangent(any(), any()) } returns 1.0
            every { mock.secant(any(), any()) } returns 1.0
            every { mock.cosecant(any(), any()) } returns 1.0
        } else {
            every { mock.log(any(), any()) } returns 1.0
            every { mock.log2(any(), any()) } returns 2.0
            every { mock.log5(any(), any()) } returns 3.0
            every { mock.log10(any(), any()) } returns 4.0
        }

        val function = FunctionWithModule(100, mock)
        val result = function.get(x)

        assertTrue(result.isFinite())
    }
}