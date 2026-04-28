import unit.CsvTest
import unit.FunctionWithModuleTest
import unit.MainTest
import integration.CsvIntTest
import integration.IntTest
import integration.LogIntTest
//import integration.MockiK
import integration.TrigIntTest
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import unit.trig.Cos
import unit.trig.Cot
import unit.trig.Csc
import unit.log.Ln
import unit.log.Log10
import unit.log.Log2
import unit.log.Log5
import unit.trig.Sec
import unit.trig.Sin
import unit.trig.Tan
import unit.log.RealLogTest
//import unit.log.StubLogTest
import unit.trig.RealTrigTest
//import unit.trig.StubTrigTest

@Suite
@SelectClasses(
    CsvTest::class,
    FunctionWithModuleTest::class,
    MainTest::class,
    RealLogTest::class,
//    StubLogTest::class,
    RealTrigTest::class,
//    StubTrigTest::class,

    CsvIntTest::class,
    IntTest::class,
    LogIntTest::class,
    TrigIntTest::class,
//    MockiK::class
    Ln::class,
    Log2::class,
    Log5::class,
    Log10::class,
    Sin::class,
    Cos::class,
    Cot::class,
    Csc::class,
    Sec::class,
    Tan::class
)
class AllTests