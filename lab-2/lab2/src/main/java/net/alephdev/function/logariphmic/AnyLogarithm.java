package net.alephdev.function.logariphmic;

import net.alephdev.function.IterableFunction;

public class AnyLogarithm extends IterableFunction{

    private final BaseELogarithm baseELogarithm;
    private final int base;

    public AnyLogarithm(final int base){
        this.baseELogarithm = new BaseELogarithm();
        this.base = base;
    }

    public AnyLogarithm(final int base, final BaseELogarithm baseELogarithm){
        super();
        this.baseELogarithm = baseELogarithm;
        this.base = base;
    }

    @Override
    public double calculate(double arg, double precision) {
        if (Math.abs(arg) <= EPSILON) {
            throw new ArithmeticException("Логарифм не определён для неположительных чисел: arg <= 0");
        }

        if (base <= 0 || base == 1) {
            throw new ArithmeticException("Основание логарифма должно быть положительным и не равным 1: base <= 0 или base == 1");
        }

        double lnArg = baseELogarithm.calculate(arg, precision);
        double lnBase = baseELogarithm.calculate(base, precision);

        return lnArg / lnBase;
    }
}
