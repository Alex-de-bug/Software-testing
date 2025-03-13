package net.alephdev;

import net.alephdev.logariphmic.AnyLogarithm;
import net.alephdev.logariphmic.BaseELogarithm;
import net.alephdev.trigonometric.CosClass;
import net.alephdev.trigonometric.CotClass;
import net.alephdev.trigonometric.SecClass;
import net.alephdev.trigonometric.SinClass;
import net.alephdev.trigonometric.TanClass;

public class FunctionalSystemClass {

    private CosClass cos = new CosClass();
    private SinClass sin = new SinClass();
    private CotClass cot = new CotClass();
    private TanClass tan = new TanClass();
    private SecClass sec = new SecClass();

    private AnyLogarithm log3 = new AnyLogarithm(3);
    private AnyLogarithm log5 = new AnyLogarithm(5);
    private BaseELogarithm logE = new BaseELogarithm();

    public FunctionalSystemClass(){
        this.cos = new CosClass();
        this.sin = new SinClass();
        this.cot = new CotClass();
        this.tan = new TanClass();
        this.sec = new SecClass();

        this.log3 = new AnyLogarithm(3);
        this.log5 = new AnyLogarithm(5);
        this.logE = new BaseELogarithm();
    }

    private double negative(double x, double precision) {

        double leftUpperTerm = Math.pow(sec.calculate(x, precision) - cot.calculate(x, precision), 2);
        double leftFirstTerm = leftUpperTerm / cot.calculate(x, precision);
        double leftFinalTerm = Math.pow(leftFirstTerm - cos.calculate(x, precision), 3);

        double rightFirstTerm = Math.pow(sec.calculate(x, precision), 2) - tan.calculate(x, precision);
        double rightFinalTerm = rightFirstTerm * tan.calculate(x, precision);

        return leftFinalTerm + rightFinalTerm;
    }
 
    private double positive(double x, double precision) {
        double leftUpperTerm1 = log3.calculate(x, precision) - logE.calculate(x, precision);
        double leftUpperTermFinal = leftUpperTerm1 * logE.calculate(x, precision);
        double leftFirstTerm = leftUpperTermFinal / log3.calculate(x, precision);
        double leftSecondTerm = logE.calculate(x, precision) * log5.calculate(x, precision);
        double leftFinalTerm = leftFirstTerm - leftSecondTerm;

        double rightSecondTerm = Math.pow(log5.calculate(x, precision), 3);
        double rightFinalTerm = logE.calculate(x, precision) - rightSecondTerm;

        return leftFinalTerm + rightFinalTerm;
    }

    public double system(double x, int precision) {
        return x <= 0 ?
                negative(x, precision) :
                positive(x, precision);
    }
}
