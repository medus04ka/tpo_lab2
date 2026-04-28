package unit

import Csv
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class CsvTest {

    @Test
    fun expFileOneMod() {
        val file = File("build/test-results/export-single.csv")
        if (file.exists()) file.delete()

        Csv.exportSingle(
            path = file.path,
            x = listOf(1.0, 2.0),
            moduleName = "TestModule",
            values = listOf(10.0, 20.0)
        )

        assertTrue(file.exists())

        val content = file.readText()
        assertTrue(content.contains("X,Результаты модуля (TestModule)"))
        assertTrue(content.contains("1.0,10.0"))
        assertTrue(content.contains("2.0,20.0"))
    }

    @Test
    fun expFile2Mod() {
        val file = File("build/test-results/export-comparison.csv")
        if (file.exists()) file.delete()

        Csv.exportComparison(
            path = file.path,
            x = listOf(1.0, 2.0),
            firstModuleName = "Real",
            firstValues = listOf(10.0, 20.0),
            secondModuleName = "Iter",
            secondValues = listOf(11.0, 21.0)
        )

        assertTrue(file.exists())

        val content = file.readText()
        assertTrue(content.contains("X,Результаты модуля (Real),Результаты модуля (Iter)"))
        assertTrue(content.contains("1.0,10.0,11.0"))
        assertTrue(content.contains("2.0,20.0,21.0"))
    }

    @Test
    fun emptyData() {
        val file = File("build/test-results/empty.csv")
        if (file.exists()) file.delete()

        Csv.exportSingle(
            path = file.path,
            x = emptyList(),
            moduleName = "Empty",
            values = emptyList()
        )

        assertTrue(file.exists())
        val content = file.readText()
        assertTrue(content.contains("X,Результаты модуля (Empty)"))
    }

    @Test
    fun smallerExp() {
        val file = File("build/test-results/export-error.csv")
        if (file.exists()) file.delete()

        assertThrows(IndexOutOfBoundsException::class.java) {
            Csv.exportSingle(
                path = file.path,
                x = listOf(1.0, 2.0),
                moduleName = "Broken",
                values = listOf(10.0)
            )
        }
    }

    @Test
    fun expValuesSizesAreInvalid() {
        val file = File("build/test-results/export-error2.csv")
        if (file.exists()) file.delete()

        assertThrows(IndexOutOfBoundsException::class.java) {
            Csv.exportComparison(
                path = file.path,
                x = listOf(1.0, 2.0),
                firstModuleName = "A",
                firstValues = listOf(10.0),
                secondModuleName = "B",
                secondValues = listOf(20.0)
            )
        }
    }
}