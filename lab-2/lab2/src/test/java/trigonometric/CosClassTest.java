package trigonometric;
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
    void testSuccessfulCasesMaxY(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(1.0f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }

    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @ValueSource(doubles = {
        Math.PI/2,
        -Math.PI/2,
        3*Math.PI/2,
        -3*Math.PI/2,
        5*Math.PI/2,
        -5*Math.PI/2
    })
    void testSuccessfulCasesAvgY(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(0.0f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }

    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @ValueSource(doubles = {
        Math.PI,
        -Math.PI,
        3*Math.PI,
        -3*Math.PI,
        5*Math.PI,
        -5*Math.PI
    })
    void testSuccessfulCasesMinY(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(-1.0f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }

    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @ValueSource(doubles = {
        Math.PI/4,
        -Math.PI/4,
        7*Math.PI/4,
        -7*Math.PI/4
    })
    void testSuccessfulCasesQuater(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(0.7071067811865475f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }

    @ParameterizedTest(name = "Test x={0}, expected={1}")
    @ValueSource(doubles = {
        3*Math.PI/4,
        -3*Math.PI/4,
        5*Math.PI/4,
        -5*Math.PI/4
    })
    void testSuccessfulCasesMinusQuater(double input) {
        double result = cosClass.calculate(input, DELTA);
        assertEquals(-0.7071067811865475f, result, DELTA_TEST, 
            "Failed for input x=" + input);
    }
}