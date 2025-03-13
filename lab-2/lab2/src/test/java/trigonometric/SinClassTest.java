package trigonometric;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.SinClass;

class SinClassTest {
    private static final double DELTA = 1e-10;      
    private static final double DELTA_TEST = 1e-6; 
    
    private CosClass cosClass;
    private SinClass sinClass;
    private CosClass mockCosClass;
    private SinClass mockSinClass;

    @BeforeEach
    void setUp() {
        cosClass = new CosClass();
        sinClass = new SinClass(cosClass);
        
        mockCosClass = Mockito.mock(CosClass.class);
        mockSinClass = new SinClass(mockCosClass);
    }

    // Тесты без мока
    @ParameterizedTest(name = "sin({0}) = {1}")
    @MethodSource("provideSinTestData")
    void testSinWithoutMock(double input, double expected) {
        double result = sinClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for input x=" + input);
    }

    private static Stream<Arguments> provideSinTestData() {
        return Stream.of(
            Arguments.of(0.0, 0.0),              
            Arguments.of(Math.PI / 2, 1.0),         
            Arguments.of(Math.PI, 0.0),            
            Arguments.of(-Math.PI / 2, -1.0),       
            Arguments.of(Math.PI / 4, 0.7071067812), 
            Arguments.of(-Math.PI / 4, -0.7071067812) 
        );
    }

    // Тесты с мокнутым CosClass
    @ParameterizedTest(name = "sin({0}) with mock, expected={1}")
    @MethodSource("provideSinMockTestData")
    void testSinWithMock(double input, double expected) {
        double mockInput = Math.PI / 2 - input;
        when(mockCosClass.calculate(mockInput, DELTA)).thenReturn(expected);
        
        double result = mockSinClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed for mocked input x=" + input);
    }

    private static Stream<Arguments> provideSinMockTestData() {
        return Stream.of(
            Arguments.of(0.0, 1.0),                  
            Arguments.of(Math.PI / 2, 0.0),         
            Arguments.of(-Math.PI / 2, 0.0),       
            Arguments.of(Math.PI / 4, 0.7071067812)  
        );
    }

    // Обычный тест без параметризации
    @Test
    void testSinZero() {
        double result = sinClass.calculate(0, DELTA);
        assertEquals(0.0, result, DELTA_TEST);
    }

    // Тест для проверки периодичности
    @ParameterizedTest(name = "sin({0}) periodicity test")
    @MethodSource("providePeriodicityTestData")
    void testPeriodicity(double input, double expected) {
        double result = sinClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST,
            "Failed periodicity test for x=" + input);
    }

    private static Stream<Arguments> providePeriodicityTestData() {
        return Stream.of(
            Arguments.of(5 * Math.PI / 4, -0.7071067812),  
            Arguments.of(-5 * Math.PI / 4, 0.7071067812), 
            Arguments.of(9 * Math.PI / 4, 0.7071067812),  
            Arguments.of(-9 * Math.PI / 4, -0.7071067812) 
        );
    }
}