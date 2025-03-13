package net.alephdev.trigonometric;

import net.alephdev.IterableFunction;

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
        double normalizedArg = arg % Math.PI;

        if (Math.abs(normalizedArg - Math.PI / 2) < EPSILON) {
            throw new ArithmeticException("Тангенс не определён для данного аргумента");
        }

        double sinValue = sin.calculate(arg, precision);
        double cosValue = cos.calculate(arg, precision);

        if (Math.abs(sinValue) < EPSILON) {
            throw new ArithmeticException("Тангенс не определён для данного аргумента");
        }

        return cosValue / sinValue;
    }

}
