import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import net.alephdev.function.logariphmic.BaseELogarithm;

class BaseELogarithmTest {
    private static final double DELTA = 1e-10;    
    private static final double DELTA_TEST = 1e-6;  

    private final BaseELogarithm baseELogarithm = new BaseELogarithm();

    // Тесты для корректных значений
    @ParameterizedTest(name = "ln({0}) = {1}")
    @CsvSource({
        "1.0, 0.0",         // ln(1) = 0
        "2.7182818285, 1.0", // ln(e) ≈ 1
        "2.0, 0.6931471806", // ln(2) ≈ 0.693
        "10.0, 2.3025850930", // ln(10) ≈ 2.303
        "0.5, -0.6931471806"  // ln(0.5) ≈ -0.693
    })
    void testBaseELogarithm(double input, double expected) {
        double result = baseELogarithm.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input);
    }

    // Тесты для неположительных значений и значений, близких к нулю
    @ParameterizedTest(name = "ln({0}) should throw ArithmeticException")
    @CsvSource({
        "0.0",
        "-1.0",
        "-10.0",
        "1e-11"  
    })
    void testBaseELogarithmUndefined(double input) {
        assertThrows(ArithmeticException.class, () -> {
            baseELogarithm.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);
    }

    // Проверка точности по сравнению с Math.log
    @ParameterizedTest(name = "ln({0}) compared to Math.log")
    @CsvSource({
        "1.0",
        "2.7182818285",
        "2.0",
        "10.0",
        "0.5"
    })
    void testBaseELogarithmAgainstMathLog(double input) {
        double result = baseELogarithm.calculate(input, DELTA);
        double expected = Math.log(input);
        assertEquals(expected, result, DELTA_TEST,
            "Failed to match Math.log for input x=" + input);
    }
}