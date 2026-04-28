import kotlin.math.pow

class FunctionWithModule(
    private val iterations: Int,
    private val solver: MathModule
) {

    fun get(x: Double): Double {
        return if (x <= 0.0) {
            calculateTrigBranch(x)
        } else {
            calculateLogBranch(x)
        }
    }

    private fun calculateTrigBranch(x: Double): Double {
        val sin = solver.sine(x, iterations)
        val cos = solver.cosine(x, iterations)
        val tan = solver.tangent(x, iterations)
        val cot = solver.cotangent(x, iterations)
        val sec = solver.secant(x, iterations)
        val csc = solver.cosecant(x, iterations)

        val firstPart =
            (((((((((tan + cos).pow(2) + sin) / tan).pow(3)).pow(3)) * cos) * (cot / cos)) / cot) *
                    ((cos.pow(2)).pow(2)))

        val secondPart =
            ((cos * sec) + sin.pow(2)) +
                    ((cot - ((sin * (cot - (((sec + sin) * csc) * (sec / tan)))) - (sec * csc))) - tan)

        val thirdPart =
            (((((cos + cos) - (cot - (csc - cos))) * ((csc / csc).pow(2))).pow(3)) / tan) - tan

        return (firstPart + secondPart) * thirdPart
    }

    private fun calculateLogBranch(x: Double): Double {
        val ln = solver.log(x, iterations)
        val log2 = solver.log2(x, iterations)
        val log5 = solver.log5(x, iterations)
        val log10 = solver.log10(x, iterations)

        val firstPart = (((ln * log2) * log2).pow(3)) / log10
        val secondPart = ((log2 * log5).pow(3)) / log2

        return firstPart - secondPart
    }
}