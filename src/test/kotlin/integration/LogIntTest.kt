package integration

import FunctionWithModule
import MathModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LogIntTest {

    private val realModule = RealMathModule()
    private val iterations = 100
    private val x = 2.0

    @Test
    fun allLogMock() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.log(any(), any())).thenReturn(2.0)
        whenever(mockModule.log2(any(), any())).thenReturn(3.0)
        whenever(mockModule.log5(any(), any())).thenReturn(4.0)
        whenever(mockModule.log10(any(), any())).thenReturn(5.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(590.4000000000001, actual, 1e-9)
    }

    @Test
    fun lnMockBAZA() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.log(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log(argX, argIter)
        }
        whenever(mockModule.log2(any(), any())).thenReturn(3.0)
        whenever(mockModule.log5(any(), any())).thenReturn(4.0)
        whenever(mockModule.log10(any(), any())).thenReturn(5.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-527.4450057400362, actual, 1e-9)
    }

    @Test
    fun lnPLUSlog2() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.log(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log(argX, argIter)
        }
        whenever(mockModule.log2(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log2(argX, argIter)
        }
        whenever(mockModule.log5(any(), any())).thenReturn(4.0)
        whenever(mockModule.log10(any(), any())).thenReturn(5.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-63.93339506960224, actual, 1e-9)
    }

    @Test
    fun lnPLUSlog2Plus5() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.log(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log(argX, argIter)
        }
        whenever(mockModule.log2(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log2(argX, argIter)
        }
        whenever(mockModule.log5(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log5(argX, argIter)
        }
        whenever(mockModule.log10(any(), any())).thenReturn(5.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-0.01327794714799857, actual, 1e-9)
    }

    @Test
    fun lnPLUSlog2Plus5Plus10() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.log(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log(argX, argIter)
        }
        whenever(mockModule.log2(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log2(argX, argIter)
        }
        whenever(mockModule.log5(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log5(argX, argIter)
        }
        whenever(mockModule.log10(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.log10(argX, argIter)
        }

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(1.0264010701858688, actual, 1e-9)
    }

}