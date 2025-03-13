
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import net.alephdev.function.FunctionalSystemClass;
import net.alephdev.function.logariphmic.AnyLogarithm;
import net.alephdev.function.logariphmic.BaseELogarithm;
import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.CotClass;
import net.alephdev.function.trigonometric.SecClass;
import net.alephdev.function.trigonometric.TanClass;

public class FunctionalSystemClassTest {

    private FunctionalSystemClass funcSystem;
    private static final double DELTA = 1e-10;
    private static final double DELTA_TEST = 1e-4;

    @Before
    public void setUp() {
        CosClass cos = new CosClass();
        CotClass cot = new CotClass();
        TanClass tan = new TanClass();
        SecClass sec = new SecClass();

        AnyLogarithm log3 = new AnyLogarithm(3);
        AnyLogarithm log5 = new AnyLogarithm(5);
        BaseELogarithm logE = new BaseELogarithm();
        funcSystem = new FunctionalSystemClass(cos, cot, tan, sec, log3, log5, logE);
    }

    // Тесты для x <= 0
    @Test(expected = ArithmeticException.class)
    public void testNegativeDomainCotZero() {
        funcSystem.calculate(-Math.PI / 2, DELTA);
    }

    @Test
    public void testNegativeValue1() {
        double expected = -185.85038;
        double result = funcSystem.calculate(-0.5, DELTA);
        assertEquals(expected, result, DELTA_TEST);
    }

    @Test
    public void testNegativeValue2() {
        double expected = 0.545696;
        double result = funcSystem.calculate(-1.9, DELTA);
        assertEquals(expected, result, DELTA);
    }

    // Тесты для x > 0
    @Test(expected = ArithmeticException.class)
    public void testPositiveDomainLog3Zero() {
        funcSystem.calculate(1, DELTA);
    }

    @Test
    public void testPositiveValue1() {
        double expected = -0.487309;
        double result = funcSystem.calculate(0.5, DELTA);
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testPositiveValue2() {
        double expected = -6.324585;
        double result = funcSystem.calculate(2, DELTA);
        assertEquals(expected, result, DELTA_TEST);
    }

    @Test
    public void testPositiveLargeValue() {
        double expected = -32.45287644712177;
        double result = funcSystem.calculate(100, DELTA);
        assertEquals(expected, result, DELTA_TEST);
    }

    @Test(expected = ArithmeticException.class)
    public void testZeroValue() {
        funcSystem.calculate(0.0f, DELTA);
    }

    @Test()
    public void testCloseToZero() {
        double expected = -2.168387;
        double result = funcSystem.calculate(0.116076, DELTA);
        assertEquals(expected, result, DELTA_TEST);
    }
}
