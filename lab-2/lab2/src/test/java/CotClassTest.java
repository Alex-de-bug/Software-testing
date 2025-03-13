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
import net.alephdev.function.trigonometric.CotClass;
import net.alephdev.function.trigonometric.SinClass;

class CotClassTest {
    private static final double DELTA = 1e-10;      
    private static final double DELTA_TEST = 1e-6;  

    private SinClass sinClass;
    private CosClass cosClass;
    private CotClass cotClass;
    private SinClass mockSinClass;
    private CosClass mockCosClass;
    private CotClass mockCotClass;

    @BeforeEach
    void setUp() {
        cosClass = new CosClass();
        sinClass = new SinClass(cosClass);
        cotClass = new CotClass(sinClass, cosClass);

        mockSinClass = Mockito.mock(SinClass.class);
        mockCosClass = Mockito.mock(CosClass.class);
        mockCotClass = new CotClass(mockSinClass, mockCosClass);
    }

    // Тесты без мока
    @ParameterizedTest(name = "cot({0}) = {1}")
    @CsvSource({
        "0.7853981634, 1.0",       // cot(PI/4) = 1
        "-0.7853981634, -1.0",     // cot(-PI/4) = -1
        "0.5235987756, 1.7320508076", // cot(PI/6) = √3 ≈ 1.732
        "1.0471975512, 0.5773502692", // cot(PI/3) = √3/3 ≈ 0.577
        "1.5707963268, 0.0"        // cot(PI/2) = 0
    })
    void testCotWithoutMock(double input, double expected) {
        double result = cotClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input);
    }

    // Тесты с мокнутым SinClass и CosClass
    @ParameterizedTest(name = "cot({0}) with mock, expected={1}")
    @CsvSource({
        "0.7853981634, 1.0",       // cot(PI/4) = 1
        "-0.7853981634, -1.0",     // cot(-PI/4) = -1
        "0.5235987756, 1.7320508076", // cot(PI/6) = √3 ≈ 1.732
        "1.0471975512, 0.5773502692"  // cot(PI/3) = √3/3 ≈ 0.577
    })
    void testCotWithMock(double input, double expected) {
        double x = input % (2 * Math.PI);
        if (Math.abs(x) < IterableFunction.EPSILON) x += 2 * Math.PI;

        // Задаём значения для моков
        double sinValue = Math.sin(x);           // Используем реальное значение синуса
        double cosValue = expected * sinValue;   // cos = cot * sin

        when(mockSinClass.calculate(x, DELTA)).thenReturn(sinValue);
        when(mockCosClass.calculate(x, DELTA)).thenReturn(cosValue);

        double result = mockCotClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input);
    }

    // Тест обработки исключений (где sin(x) = 0)
    @ParameterizedTest(name = "cot({0}) should throw ArithmeticException")
    @CsvSource({
        "0.0",              // cot(0)
        "3.1415926536",     // cot(PI)
        "-3.1415926536",    // cot(-PI)
        "6.2831853072"      // cot(2PI)
    })
    void testCotUndefined(double input) {
        assertThrows(ArithmeticException.class, () -> {
            cotClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);
    }

    // Тест с мокнутым SinClass для проверки исключений
    @Test
    void testCotWithMockUndefined() {
        double input = 0.0;
        double x = input % (2 * Math.PI);
        if (Math.abs(x) < IterableFunction.EPSILON) x += 2 * Math.PI;

        when(mockSinClass.calculate(x, DELTA)).thenReturn(0.0);
        when(mockCosClass.calculate(x, DELTA)).thenReturn(1.0);

        assertThrows(ArithmeticException.class, () -> {
            mockCotClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for mocked sin returning 0");
    }

    // Тест для проверки периодичности (период PI)
    @ParameterizedTest(name = "cot({0}) periodicity test")
    @CsvSource({
        "0.7853981634, 1.0",       // cot(PI/4)
        "3.9269908170, 1.0",       // cot(PI/4 + PI)
        "-0.7853981634, -1.0",     // cot(-PI/4)
        "-3.9269908170, -1.0"      // cot(-PI/4 - PI)
    })
    void testPeriodicity(double input, double expected) {
        double result = cotClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed periodicity test for x=" + input);
    }

    // Обычный тест без параметризации
    @Test
    void testCotPiOverTwo() {
        double result = cotClass.calculate(Math.PI / 2, DELTA);
        assertEquals(0.0, result, DELTA_TEST);
    }
}