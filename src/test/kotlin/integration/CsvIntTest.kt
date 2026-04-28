package integration

import FunctionWithModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class CsvIntTest {

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
}