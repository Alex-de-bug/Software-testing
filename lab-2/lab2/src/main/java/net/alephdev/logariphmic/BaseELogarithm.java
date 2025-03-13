package net.alephdev.logariphmic;

import net.alephdev.IterableFunction;

public class BaseELogarithm extends IterableFunction {

    public BaseELogarithm() {
        super();
    }

    @Override
    public double calculate(double arg, double precision) {
        if (arg <= 0) {
            throw new ArithmeticException("Натуральный логарифм не определён для неположительных чисел: arg <= 0");
        }

        double x = (arg - 1) / (arg + 1);
        double result = 0.0;
        double term = x;
        double xSquared = x * x;
        int n = 1;

        while (Math.abs(term) >= precision) {
            result += term / n;
            term *= xSquared;
            n += 2;
        }

        return 2 * result;
    }

}

