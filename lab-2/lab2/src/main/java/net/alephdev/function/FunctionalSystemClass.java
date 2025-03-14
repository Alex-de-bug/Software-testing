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

    private double negative(double x, double precision) {

        double secX = sec.calculate(x, precision);
        double cotX = cot.calculate(x, precision);
        double cosX = cos.calculate(x, precision);
        double tanX = tan.calculate(x, precision);

        double leftUpperTerm = Math.pow(secX - cotX, 2);
        double leftFirstTerm = leftUpperTerm / cotX;
        double leftFinalTerm = Math.pow(leftFirstTerm - cosX, 3);

        double rightFirstTerm = Math.pow(secX, 2) - tanX;
        double rightFinalTerm = rightFirstTerm * tanX;

        return leftFinalTerm + rightFinalTerm;
    }
 
    private double positive(double x, double precision) {

        double logEx = logE.calculate(x, precision);
        double log3x = log3.calculate(x, precision);
        double log5x = log5.calculate(x, precision);

        if(Math.abs(log3x) < EPSILON)
            throw new ArithmeticException("Деление на 0.");

        double leftUpperTerm1 = log3x - logEx;
        double leftUpperTermFinal = leftUpperTerm1 * logEx;
        double leftFirstTerm = leftUpperTermFinal / log3x;
        double leftSecondTerm = log3x * log5x;
        double leftFinalTerm = leftFirstTerm - leftSecondTerm;

        double rightSecondTerm = Math.pow(log5x, 3);
        double rightFinalTerm = logEx - rightSecondTerm;

        return leftFinalTerm + rightFinalTerm;
    }

    @Override
    public double calculate(double arg, double precision) {
        return arg <= 0 ?
                negative(arg, precision) :
                positive(arg, precision);
    }
}
