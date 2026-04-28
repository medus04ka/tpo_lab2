import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

class RealMathModule : MathModule() {

    private val eps = 1e-12

    private val ln2Const = lnSeriesCore(2.0, 10000)

    private fun normalizeAngle(x: Double): Double {
        var angle = x % (2 * PI)
        if (angle > PI) angle -= 2 * PI
        if (angle < -PI) angle += 2 * PI
        return angle
    }

    override fun sine(radian: Double, iterations: Int): Double {
        if (iterations <= 0) throw IllegalArgumentException("iterations must be ))))))))")
        val x = normalizeAngle(radian)
        val cos = cosine(x, iterations)
        var sinMod = 1 - cos * cos
        val sin = sqrt(sinMod)
        return if (x > 0.0 && x < PI) sin
        else if (x < 0.0 && x > -PI) -sin
        else 0.0
    }

    override fun cosine(radian: Double, iterations: Int): Double {
        if (iterations <= 0) throw IllegalArgumentException("iterations must be ))))))))")

        val x = normalizeAngle(radian)
        var term = 1.0
        var sum = term

        for (n in 1 until iterations) {
            term *= -x * x / ((2.0 * n - 1.0) * (2.0 * n))
            sum += term
            if (abs(term) < eps) break
        }

        return sum
    }

    override fun tangent(radian: Double, iterations: Int): Double {
        val cosValue = cosine(radian, iterations)
        if (abs(cosValue) < eps) {
            throw ArithmeticException("tan УНДЕФАЙН")
        }
        return sine(radian, iterations) / cosValue
    }

    override fun cotangent(radian: Double, iterations: Int): Double {
        val sinValue = sine(radian, iterations)
        if (abs(sinValue) < eps) {
            throw ArithmeticException("cot УНДЕФАЙН")
        }
        return cosine(radian, iterations) / sinValue
    }

    override fun secant(radian: Double, iterations: Int): Double {
        val cosValue = cosine(radian, iterations)
        if (abs(cosValue) < eps) {
            throw ArithmeticException("sec УНДЕФАЙН")
        }
        return 1.0 / cosValue
    }

    override fun cosecant(radian: Double, iterations: Int): Double {
        val sinValue = sine(radian, iterations)
        if (abs(sinValue) < eps) {
            throw ArithmeticException("csc УНДЕФАЙН")
        }
        return 1.0 / sinValue
    }

    private fun lnSeriesCore(x: Double, iterations: Int): Double {
        val t = (x - 1.0) / (x + 1.0)
        val t2 = t * t

        var term = t
        var sum = 0.0

        for (n in 0 until iterations) {
            val add = term / (2 * n + 1)
            sum += add
            if (abs(add) < eps) break
            term *= t2
        }

        return 2.0 * sum
    }

    override fun log(x: Double, iterations: Int): Double {
        if (iterations <= 0) throw IllegalArgumentException("iterations must be ))))))))")
        if (x <= 0.0) throw ArithmeticException("ln УНДЕФАЙН for x <= 0")
        if (x == 1.0) return 0.0

        var m = x
        var k = 0

        while (m > 1.5) {
            m /= 2.0
            k++
        }

        while (m < 0.75) {
            m *= 2.0
            k--
        }

        return lnSeriesCore(m, iterations) + k * ln2Const
    }

    override fun log2(x: Double, iterations: Int): Double {
        if (x <= 0.0) {
            throw ArithmeticException("log2 УНДЕФАЙН for x <= 0")
        }
        return log(x, iterations) / ln2Const
    }

    override fun log5(x: Double, iterations: Int): Double {
        if (x <= 0.0) {
            throw ArithmeticException("log5 УНДЕФАЙН for x <= 0")
        }
        return log(x, iterations) / log(5.0, iterations)
    }

    override fun log10(x: Double, iterations: Int): Double {
        if (x <= 0.0) {
            throw ArithmeticException("log10 УНДЕФАЙН for x <= 0")
        }
        return log(x, iterations) / log(10.0, iterations)
    }
}