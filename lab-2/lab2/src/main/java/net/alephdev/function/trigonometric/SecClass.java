package net.alephdev.function.trigonometric;

import net.alephdev.function.IterableFunction;

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
        double x = arg % (2 * Math.PI);

        if (Math.abs(x) < EPSILON) {
            x += 2 * Math.PI;
        }

        double cosValue = cos.calculate(x, precision);

        if (Math.abs(cosValue) < EPSILON) {
            throw new ArithmeticException("Секанс не определён для данного аргумента");
        }

        return 1.0 / cosValue;
    }
}
