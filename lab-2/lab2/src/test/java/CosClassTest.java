import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import net.alephdev.trigonometric.CosClass;

class CosClassTest {

    private static final double DELTA = 1e-10;

    @ParameterizedTest(name = "cos({0}) = {1}")
    @CsvSource({
        "0.0, 1.0", // cos(0) = 1
        "1.5707963267948966, 0.0", // cos(π/2) = 0
        "3.141592653589793, -1.0", // cos(π) = -1
        "4.71238898038469, 0.0", // cos(3π/2) = 0
        "6.283185307179586, 1.0", // cos(2π) = 1
        "-3.141592653589793, -1.0", // cos(-π) = -1
        "0.5235987755982989, 0.866025403784439", // cos(π/6) ≈ 0.866
        "1.0471975511965976, 0.5" // cos(π/3) = 0.5
    })
    void shouldCalculateForVariousAngles(double input, double expected) {
        final CosClass cos = new CosClass(input);
        assertEquals(expected, cos.calculate(), DELTA);
    }

    @ParameterizedTest(name = "periodic test: cos({0}) = cos({0} + 2π)")
    @CsvSource({
        "0.5",
        "1.0",
        "1.5",
        "2.0",
        "2.5"
    })
    void shouldBePeriodic(double input) {
        final CosClass cos1 = new CosClass(input);
        final CosClass cos2 = new CosClass(input + 2 * Math.PI);
        assertEquals(cos1.calculate(), cos2.calculate(), DELTA);
    }

    @ParameterizedTest(name = "even function test: cos({0}) = cos({1})")
    @CsvSource({
        "1.0, -1.0",
        "0.5, -0.5",
        "1.5, -1.5",
        "2.0, -2.0"
    })
    void shouldBeEvenFunction(double input1, double input2) {
        final CosClass cos1 = new CosClass(input1);
        final CosClass cos2 = new CosClass(input2);
        assertEquals(cos1.calculate(), cos2.calculate(), DELTA);
    }

    @ParameterizedTest(name = "precision test with {1} iterations")
    @CsvSource({
        "1.0, 10",
        "1.0, 100",
        "1.0, 1000",
        "1.0, 10000"
    })
    void shouldConvergeWithMoreIterations(double input, int iterations) {
        final CosClass cos = new CosClass(input, iterations);
        assertEquals(Math.cos(input), cos.calculate(), DELTA);
    }

    @ParameterizedTest(name = "boundary test: cos({0})")
    @CsvSource({
        "3.141592653589793", // π
        "-3.141592653589793", // -π
        "3.141592653589794", // slightly over π
        "-3.141592653589794", // slightly under -π
        "3.141592653589792", // slightly under π
        "-3.141592653589792" // slightly over -π
    })
    void shouldHandleBoundaryValues(double input) {
        CosClass cos = new CosClass(input);
        assertEquals(Math.cos(input), cos.calculate(), DELTA);
    }

    @ParameterizedTest(name = "periodic equivalence: cos({0}) = cos({1})")
    @CsvSource({
        "3.141592653589793, -3.141592653589793", // π, -π
        "4.71238898038469, -1.5707963267948966", // 3π/2, -π/2
        "6.283185307179586, 0.0", // 2π, 0
        "7.853981633974483, 1.5707963267948966" // 5π/2, π/2
    })
    void shouldMaintainPeriodicEquivalence(double input1, double input2) {
        CosClass cos1 = new CosClass(input1);
        CosClass cos2 = new CosClass(input2);
        assertEquals(cos1.calculate(), cos2.calculate(), DELTA);
    }
}
