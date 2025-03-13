package net.alephdev.function.trigonometric;

import net.alephdev.function.IterableFunction;

public class TanClass extends IterableFunction {

    private final SinClass sin;
    private final CosClass cos;

    public TanClass() {
        this.sin = new SinClass();
        this.cos = new CosClass();
    }

    public TanClass(final SinClass sin, final CosClass cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double arg, double precision) {
        double normalizedArg = arg % Math.PI;
        // if (Math.abs(normalizedArg) < 0) normalizedArg += Math.PI;

        if (Math.abs(normalizedArg - Math.PI / 2) < EPSILON) {
            throw new ArithmeticException("Тангенс не определён для данного аргумента");
        }

        double cosValue = cos.calculate(normalizedArg, precision);

        if (Math.abs(cosValue) < EPSILON) {
            throw new ArithmeticException("Тангенс не определён для данного аргумента");
        }

        double sinValue = sin.calculate(normalizedArg, precision);

        return sinValue / cosValue;
    }
}
