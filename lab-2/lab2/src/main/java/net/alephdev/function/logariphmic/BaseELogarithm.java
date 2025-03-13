package net.alephdev.function.logariphmic;

import net.alephdev.function.IterableFunction;

public class BaseELogarithm extends IterableFunction {

    @Override
    public double calculate(double arg, double precision) {
        if (Math.abs(arg) <= EPSILON) {
            throw new ArithmeticException("Натуральный логарифм не определён для неположительных чисел: arg <= 0");
        }

        double x = (arg - 1) / (arg + 1);
        double result = 0.0;
        double term = x;
        double xSquared = x * x;
        int n = 1;
        int counter = 0;

        while (Math.abs(term) >= precision) {
            result += term / n;
            term *= xSquared;
            n += 2;
            counter++;
            if(counter > 1000) break;
        }
         
        return 2 * result;
    }

}
