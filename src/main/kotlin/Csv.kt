import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

object Csv {

    fun exportSingle(
        path: String,
        x: List<Double>,
        moduleName: String,
        values: List<Double?>
    ) {
        val file = File(path)
        val parent = file.parentFile
        if (parent != null) {
            parent.mkdirs()
        }

        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(FileWriter(file))
            writer.write("X,Результаты модуля ($moduleName)")
            writer.newLine()

            var i = 0
            while (i < x.size) {
                writer.write("${x[i]},${values[i]}")
                writer.newLine()
                i++
            }
        } finally {
            writer?.close()
        }
    }

    fun exportComparison(
        path: String,
        x: List<Double>,
        firstModuleName: String,
        firstValues: List<Double?>,
        secondModuleName: String,
        secondValues: List<Double?>
    ) {
        val file = File(path)
        val parent = file.parentFile
        if (parent != null) {
            parent.mkdirs()
        }

        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(FileWriter(file))
            writer.write("X,Результаты модуля ($firstModuleName),Результаты модуля ($secondModuleName)")
            writer.newLine()

            var i = 0
            while (i < x.size) {
                writer.write("${x[i]},${firstValues[i]},${secondValues[i]}")
                writer.newLine()
                i++
            }
        } finally {
            writer?.close()
        }
    }
}