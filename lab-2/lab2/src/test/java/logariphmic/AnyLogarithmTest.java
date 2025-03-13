package logariphmic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import net.alephdev.function.logariphmic.AnyLogarithm;
import net.alephdev.function.logariphmic.BaseELogarithm;

class AnyLogarithmTest {
    private static final double DELTA = 1e-10;      
    private static final double DELTA_TEST = 1e-6;  

    private BaseELogarithm baseELogarithm;
    private AnyLogarithm anyLogarithmBase2;  // log_2
    private AnyLogarithm anyLogarithmBase10; // log_10
    private BaseELogarithm mockBaseELogarithm;
    private AnyLogarithm mockAnyLogarithmBase2;
    private AnyLogarithm mockAnyLogarithmBase10;

    @BeforeEach
    void setUp() {
        baseELogarithm = new BaseELogarithm();
        anyLogarithmBase2 = new AnyLogarithm(2, baseELogarithm);
        anyLogarithmBase10 = new AnyLogarithm(10, baseELogarithm);

        mockBaseELogarithm = Mockito.mock(BaseELogarithm.class);
        mockAnyLogarithmBase2 = new AnyLogarithm(2, mockBaseELogarithm);
        mockAnyLogarithmBase10 = new AnyLogarithm(10, mockBaseELogarithm);
    }

    // Тесты без мока для log_2
    @ParameterizedTest(name = "log_2({0}) = {1}")
    @CsvSource({
        "1.0, 0.0",         // log_2(1) = 0
        "2.0, 1.0",         // log_2(2) = 1
        "4.0, 2.0",         // log_2(4) = 2
        "8.0, 3.0",         // log_2(8) = 3
        "0.5, -1.0"         // log_2(0.5) = -1
    })
    void testLog2WithoutMock(double input, double expected) {
        double result = anyLogarithmBase2.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input + " with base 2");
    }

    // Тесты без мока для log_10
    @ParameterizedTest(name = "log_10({0}) = {1}")
    @CsvSource({
        "1.0, 0.0",         // log_10(1) = 0
        "10.0, 1.0",        // log_10(10) = 1
        "100.0, 2.0",       // log_10(100) = 2
        "1000.0, 3.0",      // log_10(1000) = 3
        "0.1, -1.0"         // log_10(0.1) = -1
    })
    void testLog10WithoutMock(double input, double expected) {
        double result = anyLogarithmBase10.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input + " with base 10");
    }

    // Тесты с мокнутым BaseELogarithm для log_2
    @ParameterizedTest(name = "log_2({0}) with mock, expected={1}")
    @CsvSource({
        "1.0, 0.0",
        "2.0, 1.0",
        "4.0, 2.0",
        "8.0, 3.0",
        "0.5, -1.0"
    })
    void testLog2WithMock(double input, double expected) {
        double ln2 = Math.log(2); // ln(2) ≈ 0.6931471806
        double lnArg = expected * ln2; // ln(x) = log_2(x) * ln(2)

        when(mockBaseELogarithm.calculate(input, DELTA)).thenReturn(lnArg);
        when(mockBaseELogarithm.calculate(2, DELTA)).thenReturn(ln2);

        double result = mockAnyLogarithmBase2.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input + " with base 2");
    }

    // Тесты с мокнутым BaseELogarithm для log_10
    @ParameterizedTest(name = "log_10({0}) with mock, expected={1}")
    @CsvSource({
        "1.0, 0.0",
        "10.0, 1.0",
        "100.0, 2.0",
        "1000.0, 3.0",
        "0.1, -1.0"
    })
    void testLog10WithMock(double input, double expected) {
        double ln10 = Math.log(10); // ln(10) ≈ 2.3025850930
        double lnArg = expected * ln10; // ln(x) = log_10(x) * ln(10)

        when(mockBaseELogarithm.calculate(input, DELTA)).thenReturn(lnArg);
        when(mockBaseELogarithm.calculate(10, DELTA)).thenReturn(ln10);

        double result = mockAnyLogarithmBase10.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input + " with base 10");
    }

    // Тесты исключений для неположительных аргументов
    @ParameterizedTest(name = "log({0}) should throw ArithmeticException for invalid argument")
    @CsvSource({
        "0.0",
        "-1.0",
        "-10.0",
        "1e-11"  // Значение меньше EPSILON
    })
    void testInvalidArgument(double input) {
        assertThrows(ArithmeticException.class, () -> {
            anyLogarithmBase2.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);

        assertThrows(ArithmeticException.class, () -> {
            anyLogarithmBase10.calculate(input, DELTA);
        }, "Expected ArithmeticException for input x=" + input);
    }

    // Тесты исключений для некорректных оснований
    @Test
    void testInvalidBase() {
        assertThrows(ArithmeticException.class, () -> {
            new AnyLogarithm(0, baseELogarithm).calculate(2.0, DELTA);
        }, "Expected ArithmeticException for base 0");

        assertThrows(ArithmeticException.class, () -> {
            new AnyLogarithm(-1, baseELogarithm).calculate(2.0, DELTA);
        }, "Expected ArithmeticException for base -1");

        assertThrows(ArithmeticException.class, () -> {
            new AnyLogarithm(1, baseELogarithm).calculate(2.0, DELTA);
        }, "Expected ArithmeticException for base 1");
    }

    // Проверка точности по сравнению с Math.log
    @ParameterizedTest(name = "log_2({0}) compared to Math.log")
    @CsvSource({
        "1.0",
        "2.0",
        "4.0",
        "8.0",
        "0.5"
    })
    void testLog2AgainstMathLog(double input) {
        double result = anyLogarithmBase2.calculate(input, DELTA);
        double expected = Math.log(input) / Math.log(2);
        assertEquals(expected, result, DELTA_TEST,
            "Failed to match Math.log for input x=" + input + " with base 2");
    }

    @ParameterizedTest(name = "log_10({0}) compared to Math.log")
    @CsvSource({
        "1.0",
        "10.0",
        "100.0",
        "1000.0",
        "0.1"
    })
    void testLog10AgainstMathLog(double input) {
        double result = anyLogarithmBase10.calculate(input, DELTA);
        double expected = Math.log(input) / Math.log(10);
        assertEquals(expected, result, DELTA_TEST,
            "Failed to match Math.log for input x=" + input + " with base 10");
    }
}