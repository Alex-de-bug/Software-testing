package net.alephdev.function.trigonometric;

import net.alephdev.function.IterableFunction;

public class CotClass extends IterableFunction {

    private final SinClass sin;
    private final CosClass cos;

    public CotClass() {
        this.sin = new SinClass();
        this.cos = new CosClass();
    }

    public CotClass(final SinClass sin, final CosClass cos) {
        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public double calculate(double arg, double precision) {
        double x = arg % (2 * Math.PI);

        if (Math.abs(x) < EPSILON) x += 2 * Math.PI;

        double sinValue = sin.calculate(x, precision);
        double cosValue = cos.calculate(x, precision);

        if (Math.abs(sinValue) < EPSILON) {
            throw new ArithmeticException("Тангенс не определён для данного аргумента");
        }

        return cosValue / sinValue;
    }

}
