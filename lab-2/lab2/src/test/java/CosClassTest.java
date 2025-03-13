import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import net.alephdev.function.trigonometric.CosClass;

class CosClassTest{
    private CosClass cosClass;

    private static final double DELTA = Double.MIN_VALUE;  
    private static final double DELTA_TEST = 1e-3;

    @BeforeEach
    void setUp() {
        cosClass = new CosClass();
    }

    // Parameterized test for successful cases
    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @CsvSource({
            "-6, 0.587168",
            
    })
    void testSuccessfulCases(double input, double expected) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(expected, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }
    
}