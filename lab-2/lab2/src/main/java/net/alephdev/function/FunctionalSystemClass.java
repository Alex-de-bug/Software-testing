package net.alephdev.function;

import net.alephdev.function.logariphmic.AnyLogarithm;
import net.alephdev.function.logariphmic.BaseELogarithm;
import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.CotClass;
import net.alephdev.function.trigonometric.SecClass;
import net.alephdev.function.trigonometric.TanClass;

public class FunctionalSystemClass extends IterableFunction{

    private final CosClass cos;
    private final CotClass cot;
    private final TanClass tan;
    private final SecClass sec;

    private final AnyLogarithm log3;
    private final AnyLogarithm log5;
    private final BaseELogarithm logE;

    public FunctionalSystemClass(){
        this.cos = new CosClass();
        this.cot = new CotClass();
        this.tan = new TanClass();
        this.sec = new SecClass();

        this.log3 = new AnyLogarithm(3);
        this.log5 = new AnyLogarithm(5);
        this.logE = new BaseELogarithm();
    }

    public FunctionalSystemClass(
            final CosClass cos,
            final CotClass cot,
            final TanClass tan,
            final SecClass sec,
            final AnyLogarithm log3,
            final AnyLogarithm log5,
            final BaseELogarithm logE
    ){
        this.cos = cos;
        this.cot = cot;
        this.tan = tan;
        this.sec = sec;
        this.log3 = log3;
        this.log5 = log5;
        this.logE = logE;
    }

    /**
     * Проверка ОДЗ для x > 0.
     * Функция не существует, если:
     * 1. log3(x) = 0 (деление на ноль).
     */
    private void checkPositiveDomain(double x, double precision) {
        if (Math.abs(log3.calculate(x, precision)) < EPSILON) {
            throw new ArithmeticException("log3(x) = 0 при x = " + x);
        }
    }

        /**
     * Проверка ОДЗ для x <= 0.
     * Функция не существует, если:
     * cot(x) = 0 (деление на ноль).
     */
    private void checkNegativeDomain(double x, double precision) {
        if (Math.abs(cot.calculate(x, precision)) < EPSILON) {
            throw new ArithmeticException("cot(x) = 0 при x = " + x);
        }
    }

    private double negative(double x, double precision) {

        checkNegativeDomain(x, precision);

        double leftUpperTerm = Math.pow(sec.calculate(x, precision) - cot.calculate(x, precision), 2);
        double leftFirstTerm = leftUpperTerm / cot.calculate(x, precision);
        double leftFinalTerm = Math.pow(leftFirstTerm - cos.calculate(x, precision), 3);

        double rightFirstTerm = Math.pow(sec.calculate(x, precision), 2) - tan.calculate(x, precision);
        double rightFinalTerm = rightFirstTerm * tan.calculate(x, precision);

        return leftFinalTerm + rightFinalTerm;
    }
 
    private double positive(double x, double precision) {

        checkPositiveDomain(x, precision);

        double leftUpperTerm1 = log3.calculate(x, precision) - logE.calculate(x, precision);
        double leftUpperTermFinal = leftUpperTerm1 * logE.calculate(x, precision);
        double leftFirstTerm = leftUpperTermFinal / log3.calculate(x, precision);
        double leftSecondTerm = logE.calculate(x, precision) * log5.calculate(x, precision);
        double leftFinalTerm = leftFirstTerm - leftSecondTerm;

        double rightSecondTerm = Math.pow(log5.calculate(x, precision), 3);
        double rightFinalTerm = logE.calculate(x, precision) - rightSecondTerm;

        return leftFinalTerm + rightFinalTerm;
    }

    @Override
    public double calculate(double arg, double precision) {
        return arg <= 0 ?
                negative(arg, precision) :
                positive(arg, precision);
    }
}
