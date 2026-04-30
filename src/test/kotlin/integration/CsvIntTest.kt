package integration

import FunctionWithModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class CsvIntTest {

    private val module = FunctionWithModule(100, RealMathModule())

    @Test
    fun CSVExp() {
        val file = File("build/test-results/system-integration.csv")
        if (file.exists()) file.delete()

        val function = FunctionWithModule(100, RealMathModule())
        val xs = listOf(-0.5, 2.0)
        val values = xs.map {
            try {
                function.get(it)
            } catch (_: Exception) {
                null
            }
        }

        Csv.exportSingle(
            path = file.path,
            x = xs,
            moduleName = "FunctionWithModule",
            values = values
        )

        assertTrue(file.exists())
        assertTrue(file.readText().contains("Результаты модуля (FunctionWithModule)"))
    }

    @Test
    fun pointsFromCsv() {
        val file = File("./results/points.csv")
        assertTrue(file.exists(), "CSV file with points should exist")

        val rows = file.readLines()
            .drop(1)
            .filter { it.isNotBlank() }

        rows.forEach { line ->
            val parts = line.split(",")
            val x = parts[0].trim().toDouble()
            val expectedText = parts[1].trim()

            val actual = try {
                module.get(x)
            } catch (_: Exception) {
                null
            }

            when {expectedText == "null" || expectedText == "NaN" -> {
                    assertTrue(
                        actual == null || actual.isNaN() || actual.isInfinite(),
                        "Expected undefined value at x=$x, but actual=$actual")
                }
                expectedText == "infinite" -> {
                    assertTrue(
                        actual != null && (actual.isInfinite() || kotlin.math.abs(actual) > 1e6),
                        "Expected very large value near discontinuity at x=$x, but actual=$actual")
                }
                else -> {
                    val expected = expectedText.toDouble()
                    assertEquals(expected, actual ?: error("Function threw exception at x=$x"), 1e-2, "Wrong value at x=$x")
                }
            }
        }
    }
}