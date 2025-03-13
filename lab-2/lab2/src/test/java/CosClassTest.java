import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    @ValueSource(doubles = {
        0,
        2*Math.PI,
        -2*Math.PI,
        4*Math.PI,
        -4*Math.PI,
        6*Math.PI,
        -6*Math.PI
    })
    void testSuccessfulCases(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(1.0f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }
    
}