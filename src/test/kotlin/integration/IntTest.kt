package integration

import FunctionWithModule
import RealMathModule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class IntTest {

    private val module = FunctionWithModule(100, RealMathModule())

    companion object {
        @JvmStatic
        fun trig() = Stream.of(Arguments.of(-0.47761, 0.0),
            Arguments.of(-0.3981, 2.95554), //триганома первая наверху
            Arguments.of(-0.547, -1.21938), //до пересечения с микроштукой
            Arguments.of(-0.47761, 0), // точка пересечения наверху и до
            Arguments.of(-0.876, -2.092642), // между микроштуками
            Arguments.of(-0.77341, -2.14949), //точка пересечения до и между
            Arguments.of(-0.9728, -2.04274), //точка пересечения микроштуки и низа
            Arguments.of(-1.1818, -3.73301), //низ триганомной линии бесконечно падает
            Arguments.of(-1.9642, -37.00205), //точка между бесконечно низом и бесконечно верхом (парабола в минусе???)
//            Arguments.of(-6.7608, 0.0), //
//            Arguments.of(-7.05659, -2.14949), //
//            Arguments.of(-8.24738, -37.00205), //
            Arguments.of(-1.26019, -41.75848), // точка идущая бесконечно вниз никогда не дойдет до параболы((
            Arguments.of(-1.92681, -41.7724), // точка параболы первая, ближе к той, к которой она не дойдет)(
            Arguments.of(-1.33318, -863.90483), //глубокий низ первой линии бесконечновнизкой
            Arguments.of(-1.81337, -872.5477), // глубокий низ параболы бесконечно низкой
            Arguments.of(-1.94222, -38.33195),//начало парабола первая линий
            Arguments.of(-1.99846, -38.83654),// начало параболы вторая линия
            Arguments.of(-1.84383, -283.76068),//глубокий низ первой параболы
            Arguments.of(-2.39212, -283.76636),//глубокий низ второй линии параболы
            Arguments.of(-5.41945, -3.84033), //вторая линия внизу
            Arguments.of(-5.41951, 0.0), //точка пересечения второй линии с осью
            Arguments.of(-5.41959, 5.91607), //вторая линия наверху
            Arguments.of(-4.12512, 18.49831), //первая линия наверху
            Arguments.of(-4.12543, 0.0), //первая линия пересечение с осью
            Arguments.of(-4.12557, -8.43138), //первая линия внизу
            Arguments.of(-6.679, 3.07968), //вторая триганомня - верх
            Arguments.of(-6.76, 0.0), //вторая триганома пересечение с осью
            Arguments.of(-6.8295, -1.211167), //низ оси вторая трига
            Arguments.of(-7.05659, -2.14949),//точка пересечения до микроштук и после
            Arguments.of(-7.165, -2.088069),//между двумя микроштуками
            Arguments.of(-7.25598, -2.04274),//между микроштукой и бесконечно внизумирающей
            Arguments.of(-7.4066, -2.409154),//вниз после точки пересечения
            Arguments.of(-7.54204, -39.56352),//низ для асимптоты
            Arguments.of(-8.2174, -39.75991),//парабола2 первая вниз
            Arguments.of(-8.24738, -37.00205),//вершина параболы
            Arguments.of(-8.2892, -39,59383),//парабола вниз вторая
            Arguments.of(-7.58716, -254.91004),// глубокий низ триганомы до параболы
            Arguments.of(-8.12991, -256.54341),//глубокий низ параболы первой
            Arguments.of(-8.6588, -255.50465),//глубокий низ параболы второй
            Arguments.of(-10.40866, -2.74533),//вторые линии один низ
            Arguments.of(-10.40861, 0.0), //точка пересечения с осью первой
            Arguments.of(-11.70269, 0.0),//второй
            Arguments.of(-10.4084, 12.81409),// вверх
            Arguments.of(-11.70272, 2.05902)//вверх второй
        )

        @JvmStatic
        fun log() = Stream.of(
            Arguments.of(0.5084, 0.981353),
            Arguments.of(1.279901, -0.000171),
            Arguments.of(1.334609, 0.0),
            Arguments.of(1.68, 0.090186),
            Arguments.of(1.887, 0.497226),
            Arguments.of(1.969, 0.850592),
            Arguments.of(2.0472, 1.347396),
            Arguments.of(0.64, 0.04151),
            Arguments.of(2.322,5.05475),
            Arguments.of(0.4385, 4.61954)
        )
    }

    @ParameterizedTest(name = "trig f({0}) = {1}")
    @MethodSource("trig")
    fun trigPointsAreCorrect(x: Double, expected: Double) {
        assertEquals(expected, module.get(x), 1e-0)
    }

    @ParameterizedTest(name = "log f({0}) = {1}")
    @MethodSource("log")
    fun logPointsAreCorrect(x: Double, expected: Double) {
        assertEquals(expected, module.get(x), 1e-0)
    }

}