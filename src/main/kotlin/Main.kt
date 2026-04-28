fun main() {
    val leftBorder = -6.0
    val rightBorder = 5.0
    val pointsCount = 10000
    val iterations = 100

    val function = FunctionWithModule(iterations, RealMathModule())

    val step = (rightBorder - leftBorder) / pointsCount
    val xValues = (0..pointsCount).map { leftBorder + it * step }

    val values = xValues.map { x ->
        try {
            function.get(x)
        } catch (_: Exception) {
            null
        }
    }

    Csv.exportSingle(
        path = "./results/result.csv",
        x = xValues,
        moduleName = "FunctionWithModule",
        values = values
    )

    println("Готово емае")
}