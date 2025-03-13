import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import net.alephdev.function.FunctionalSystemClass;
import net.alephdev.function.logariphmic.AnyLogarithm;
import net.alephdev.function.logariphmic.BaseELogarithm;
import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.CotClass;
import net.alephdev.function.trigonometric.SecClass;
import net.alephdev.function.trigonometric.TanClass;

class FunctionalSystemClassTest {

    private FunctionalSystemClass funcSystem;
    private static final double DELTA = Double.MIN_VALUE;  
    private static final double DELTA_TEST = 1e-3;


    @BeforeEach
    void setUp() {
        CosClass cos = new CosClass();
        CotClass cot = new CotClass();
        TanClass tan = new TanClass();
        SecClass sec = new SecClass();
        AnyLogarithm log3 = new AnyLogarithm(3);
        AnyLogarithm log5 = new AnyLogarithm(5);
        BaseELogarithm logE = new BaseELogarithm();
        funcSystem = new FunctionalSystemClass(cos, cot, tan, sec, log3, log5, logE);
    }

    // Parameterized test for successful cases
    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @CsvSource({
            "-6, 0.587168",
            "-5.94525, 0.276875",
            "-5.915, 0.294296",
            "-5.88383, 0.307632",
            "-5.785, 0.179921",
            "-5.68928, 0.056363",
            "-5.584, 0.300098",
            "-5.5, 0.830192",

            "-3.775, -1.166686",
            "-3.675, -0.81104",
            "-3.624, -0.759569",
            "-3.57, -0.711194",
            "-3.515, -0.605007",
            "-3.46579, -0.511809",
            "-3.4, -1.447405",

            "0.03182, 0.0",
            "0.04367, -1.003788",
            "0.116076, -2.168387",

            "0.25, -1.69743",
            "0.5, -0.81664",
            "0.75, -0.30041",

            "0.998, -0.00181",
            "1.003, 0.0027",

            "1.32, 0.20153",
            "1.789, 0.28575",

            "2.5, 0.16656",
            "2.97409, 0.0",

            "5, -1.01425"
    })
    void testSuccessfulCases(double input, double expected) {
        double result = funcSystem.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }

    // Parameterized test for error cases (ArithmeticException)
    @ParameterizedTest(name = "Test error case x={0}")
    @ValueSource(doubles = {
        -Math.PI / 2,    // cot(x) = 0 (undefined in negative domain)
        0.0,             // Boundary case
        1.0,             // log3(x) = 0 (undefined in positive domain)
        -Math.PI         // cot(x) = 0 (undefined in negative domain)
    })
    void testErrorCases(double input) {
        assertThrows(ArithmeticException.class, 
            () -> funcSystem.calculate(input, DELTA),
            "Expected ArithmeticException for x=" + input);
    }
}