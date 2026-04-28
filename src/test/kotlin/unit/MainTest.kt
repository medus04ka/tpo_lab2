package unit

import main
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

class MainTest {

    @Test
    fun full100ForMain() {
        val file = File("./results/result.csv")
        if (file.exists()) file.delete()

        main()

        assertTrue(file.exists())
        assertTrue(file.readText().contains("X,Результаты модуля"))
    }
}