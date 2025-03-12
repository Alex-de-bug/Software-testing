package net.alephdev;

import static net.alephdev.logariphmic.AnyLogarithm.logN;
import static net.alephdev.logariphmic.BaseELogarithm.ln;
import static net.alephdev.trigonometric.CosClass.cos;
import static net.alephdev.trigonometric.CotClass.cot;
import static net.alephdev.trigonometric.SecClass.sec;
import static net.alephdev.trigonometric.TanClass.tan;

public class FunctionalSystemClass {
    public static double negative(double x, int iterations) {
        double leftUpperTerm = Math.pow(sec(x, iterations) - cot(x, iterations), 2);
        double leftFirstTerm = leftUpperTerm / cot(x, iterations);
        double leftFinalTerm = Math.pow(leftFirstTerm - cos(x, iterations), 3);

        double rightFirstTerm = Math.pow(sec(x, iterations), 2) - tan(x, iterations);
        double rightFinalTerm = rightFirstTerm * tan(x, iterations);

        return leftFinalTerm + rightFinalTerm;
    }

    public static double positive(double x, int iterations) {
        double leftUpperTerm1 = logN(x, 3, iterations) - ln(x, iterations);
        double leftUpperTermFinal = leftUpperTerm1 * ln(x, iterations);
        double leftFirstTerm = leftUpperTermFinal / logN(x, 3, iterations);
        double leftSecondTerm = logN(x, 3, iterations) * logN(x, 5, iterations);
        double leftFinalTerm = leftFirstTerm - leftSecondTerm;

        double rightSecondTerm = Math.pow(logN(x, 5, iterations), 3);
        double rightFinalTerm = ln(x, iterations) - rightSecondTerm;

        return leftFinalTerm + rightFinalTerm;
    }

    public static double system(double x, int iterations) {
        return x <= 0 ?
                negative(x, iterations) :
                positive(x, iterations);
    }
}
