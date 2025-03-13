package trigonometric;
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
import net.alephdev.function.trigonometric.SinClass;
import net.alephdev.function.trigonometric.TanClass;

class TanClassTest {
    private static final double DELTA = 1e-10;     
    private static final double DELTA_TEST = 1e-6; 

    private SinClass sinClass;
    private CosClass cosClass;
    private TanClass tanClass;
    private SinClass mockSinClass;
    private CosClass mockCosClass;
    private TanClass mockTanClass;

    @BeforeEach
    void setUp() {
        cosClass = new CosClass();
        sinClass = new SinClass(cosClass);
        tanClass = new TanClass(sinClass, cosClass);

        mockSinClass = Mockito.mock(SinClass.class);
        mockCosClass = Mockito.mock(CosClass.class);
        mockTanClass = new TanClass(mockSinClass, mockCosClass);
    }

    // Тесты без мока
    @ParameterizedTest(name = "tan({0}) = {1}")
    @CsvSource({
        "0.0, 0.0",                // tan(0) = 0
        "0.7853981634, 1.0",       // tan(PI/4) = 1
        "-0.7853981634, -1.0",     // tan(-PI/4) = -1
        "0.5235987756, 0.5773502692", // tan(PI/6) ≈ 0.577 
        "1.0471975512, 1.7320508076"  // tan(PI/3) ≈ √3
    })
    void testTanWithoutMock(double input, double expected) {
        double result = tanClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input);
    }

    // Тесты с мокнутым SinClass и CosClass
    @ParameterizedTest(name = "tan({0}) with mock, expected={1}")
    @CsvSource({
        "0.0, 0.0",                // tan(0) = 0
        "0.7853981634, 1.0",       // tan(PI/4) = 1
        "-0.7853981634, -1.0",     // tan(-PI/4) = -1
        "0.5235987756, 0.5773502692"  // tan(PI/6) ≈ 0.577
    })
    void testTanWithMock(double input, double expected) {
        double normalizedArg = input % Math.PI;
        if (Math.abs(normalizedArg) < 0) normalizedArg += Math.PI;

        // Задаём значения для моков
        double sinValue = expected * Math.cos(normalizedArg); 
        double cosValue = Math.cos(normalizedArg);      
        if (Math.abs(cosValue) < IterableFunction.EPSILON) cosValue = 1.0;   

        when(mockSinClass.calculate(normalizedArg, DELTA)).thenReturn(sinValue);
        when(mockCosClass.calculate(normalizedArg, DELTA)).thenReturn(cosValue);

        double result = mockTanClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input);
    }

    // Тест обработки исключений (где cos(x) = 0 или x = π/2 + kπ)
    @ParameterizedTest(name = "tan({0}) should throw ArithmeticException")
    @CsvSource({
        "1.5707963268",    
        "-1.5707963268",  
        "4.7123889804",  
        "-4.7123889804"  
    })
    void testTanUndefined(double input) {
        assertThrows(ArithmeticException.class, () -> {
            tanClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);
    }

    // Тест с мокнутым CosClass для проверки исключений
    @Test
    void testTanWithMockUndefined() {
        double input = Math.PI / 2;
        double normalizedArg = input % Math.PI;
        if (Math.abs(normalizedArg) < 0) normalizedArg += Math.PI;

        when(mockSinClass.calculate(normalizedArg, DELTA)).thenReturn(1.0);
        when(mockCosClass.calculate(normalizedArg, DELTA)).thenReturn(0.0);

        assertThrows(ArithmeticException.class, () -> {
            mockTanClass.calculate(input, DELTA);
        }, "Expected ArithmeticException for mocked cos returning 0");
    }

    // Тест для проверки периодичности (период PI)
    @ParameterizedTest(name = "tan({0}) periodicity test")
    @CsvSource({
        "0.7853981634, 1.0",     
        "3.9269908170, 1.0",  
        "-0.7853981634, -1.0",     
        "-3.9269908170, -1.0"     
    })
    void testPeriodicity(double input, double expected) {
        double result = tanClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed periodicity test for x=" + input);
    }

    // Обычный тест без параметризации
    @Test
    void testTanZero() {
        double result = tanClass.calculate(0, DELTA);
        assertEquals(0.0, result, DELTA_TEST);
    }
}
