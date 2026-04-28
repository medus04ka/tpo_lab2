package integration

import FunctionWithModule
import MathModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import kotlin.math.PI

class TrigIntTest {

    private val realModule = RealMathModule()
    private val iterations = 100
    private val x = -1.0

    @Test
    fun allMock() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenReturn(2.0)
        whenever(mockModule.cosine(any(), any())).thenReturn(3.0)
        whenever(mockModule.tangent(any(), any())).thenReturn(4.0)
        whenever(mockModule.cotangent(any(), any())).thenReturn(5.0)
        whenever(mockModule.secant(any(), any())).thenReturn(6.0)
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        print(actual)
        assertEquals(1.9653648681247258E13, actual, 1e-9)
    }

    @Test
    fun cos() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenReturn(2.0)
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenReturn(4.0)
        whenever(mockModule.cotangent(any(), any())).thenReturn(5.0)
        whenever(mockModule.secant(any(), any())).thenReturn(6.0)
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(49417.72582626251, actual, 1e-9)
    }

    @Test
    fun sinCos() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.sine(argX, argIter)
        }
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenReturn(4.0)
        whenever(mockModule.cotangent(any(), any())).thenReturn(5.0)
        whenever(mockModule.secant(any(), any())).thenReturn(6.0)
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        print(actual)
        assertEquals(14753.36741729073, actual, 1e-9)
    }

    @Test
    fun sinCosTan() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.sine(argX, argIter)
        }
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.tangent(argX, argIter)
        }
        whenever(mockModule.cotangent(any(), any())).thenReturn(5.0)
        whenever(mockModule.secant(any(), any())).thenReturn(6.0)
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-1558.4824300631178, actual, 1e-9)
    }

    @Test
    fun sinCosTanCOt() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.sine(argX, argIter)
        }
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.tangent(argX, argIter)
        }
        whenever(mockModule.cotangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cotangent(argX, argIter)
        }
        whenever(mockModule.secant(any(), any())).thenReturn(6.0)
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-57217.15492770873, actual, 1e-9)
    }

    @Test
    fun sinCosTanCOtSec() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.sine(argX, argIter)
        }
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.tangent(argX, argIter)
        }
        whenever(mockModule.cotangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cotangent(argX, argIter)
        }
        whenever(mockModule.secant(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.secant(argX, argIter)
        }
        whenever(mockModule.cosecant(any(), any())).thenReturn(7.0)

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-7740.817745953163, actual, 1e-9)
    }

    @Test
    fun fuLLTrigUrodbl() {
        val mockModule = mock<MathModule>()

        whenever(mockModule.sine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.sine(argX, argIter)
        }
        whenever(mockModule.cosine(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosine(argX, argIter)
        }
        whenever(mockModule.tangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.tangent(argX, argIter)
        }
        whenever(mockModule.cotangent(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cotangent(argX, argIter)
        }
        whenever(mockModule.secant(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.secant(argX, argIter)
        }
        whenever(mockModule.cosecant(any(), any())).thenAnswer {
            val argX: Double = it.getArgument(0)
            val argIter: Int = it.getArgument(1)
            realModule.cosecant(argX, argIter)
        }

        val function = FunctionWithModule(iterations, mockModule)
        val actual = function.get(x)

        assertEquals(-2.0494155285106195, actual, 1e-9)
    }
}