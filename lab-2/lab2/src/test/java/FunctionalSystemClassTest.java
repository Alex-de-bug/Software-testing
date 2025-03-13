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
    private static final double DELTA_TEST = 1e-4;  


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
        "-0.5, -185.85038",        // Negative domain test
        "0.116076, -2.168387",     // Close to zero test
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
        Math.PI,         // cot(x) = 0 (undefined in negative domain)
        -Math.PI         // cot(x) = 0 (undefined in negative domain)
    })
    void testErrorCases(double input) {
        assertThrows(ArithmeticException.class, 
            () -> funcSystem.calculate(input, DELTA),
            "Expected ArithmeticException for x=" + input);
    }
}