import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import net.alephdev.function.IterableFunction;
import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.SecClass;

class SecClassTest {
    private static final double DELTA = 1e-10;      
    private static final double DELTA_TEST = 1e-6;  

    private CosClass cosClass;
    private SecClass secClass;
    private CosClass mockCosClass;
    private SecClass mockSecClass;

    @BeforeEach
    void setUp() {
        cosClass = new CosClass();
        secClass = new SecClass(cosClass);

        mockCosClass = Mockito.mock(CosClass.class);
        mockSecClass = new SecClass(mockCosClass);
    }

    // Тесты без мока
    @ParameterizedTest(name = "sec({0}) = {1}")
    @CsvSource({
        "0.0, 1.0",                // sec(0) = 1/cos(0) = 1
        "0.7853981634, 1.4142135624",  // sec(PI/4) = 1/cos(PI/4) ≈ √2
        "-0.7853981634, 1.4142135624", // sec(-PI/4) = 1/cos(-PI/4) ≈ √2
        "3.1415926536, -1.0",      // sec(PI) = 1/cos(PI) = -1
        "1.0471975512, 2.0"        // sec(PI/3) = 1/cos(PI/3) = 2
    })
    void testSecWithoutMock(double input, double expected) {
        double result = secClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input);
    }

    // Тесты с мокнутым CosClass
    @ParameterizedTest(name = "sec({0}) with mock, expected={1}")
    @CsvSource({
        "0.0, 1.0",                // sec(0) = 1/cos(0) = 1
        "0.7853981634, 1.4142135624",  // sec(PI/4) = 1/cos(PI/4) ≈ √2
        "-0.7853981634, 1.4142135624", // sec(-PI/4) = 1/cos(-PI/4) ≈ √2
        "3.1415926536, -1.0"       // sec(PI) = 1/cos(PI) = -1
    })
    void testSecWithMock(double input, double expected) {
        double cosInput = input % (2 * Math.PI);
        if (Math.abs(cosInput) < IterableFunction.EPSILON) {
            cosInput += 2 * Math.PI;
        }
        double cosValue = 1.0 / expected;
        when(mockCosClass.calculate(cosInput, DELTA)).thenReturn(cosValue);

        double result = mockSecClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input);
    }

    // Тест обработки исключений (где cos(x) = 0)
    @ParameterizedTest(name = "sec({0}) should throw ArithmeticException")
    @CsvSource({
        "1.5707963268",   
        "-1.5707963268",   
        "4.7123889804",   
        "7.8539816340"    
    })
    void testSecUndefined(double input) {
        assertThrows(ArithmeticException.class, () -> {
            secClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);
    }

    // Тест с мокнутым CosClass для проверки исключений
    @Test
    void testSecWithMockUndefined() {
        double input = Math.PI / 2;
        double cosInput = input % (2 * Math.PI);
        when(mockCosClass.calculate(cosInput, DELTA)).thenReturn(0.0);

        assertThrows(ArithmeticException.class, () -> {
            mockSecClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for mocked cos returning 0");
    }

    // Тест для проверки периодичности
    @ParameterizedTest(name = "sec({0}) periodicity test")
    @CsvSource({
        "0.7853981634, 1.4142135624",    
        "7.0685834706, 1.4142135624",   
        "-0.7853981634, 1.4142135624",  
        "-7.0685834706, 1.4142135624"    
    })
    void testPeriodicity(double input, double expected) {
        double result = secClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed periodicity test for x=" + input);
    }

    // Обычный тест без параметризации
    @Test
    void testSecZero() {
        double result = secClass.calculate(0, DELTA);
        assertEquals(1.0, result, DELTA_TEST);
    }
}