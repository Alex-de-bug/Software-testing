package net.alephdev.trigonometric;

import net.alephdev.IterableFunction;

public class SecClass extends IterableFunction {

    private final CosClass cos;

    public SecClass() {
        this.cos = new CosClass();
    }

    public SecClass(final CosClass cos) {
        this.cos = cos;
    }

    @Override
    public double calculate(double arg, double precision) {
        double normalizedArg = arg % Math.PI;

        if (Math.abs(normalizedArg - Math.PI / 2) < EPSILON) {
            throw new ArithmeticException("Секанс не определён для данного аргумента");
        }

        double cosValue = cos.calculate(arg, precision);

        if (Math.abs(cosValue) < EPSILON) {
            throw new ArithmeticException("Секанс не определён для данного аргумента");
        }

        return 1.0 / cosValue;
    }
}
