package net.alephdev.function.trigonometric;

import net.alephdev.function.IterableFunction;

public class SinClass extends IterableFunction {

    private final CosClass cos;

    public SinClass() {
        this.cos = new CosClass();
    }

    public SinClass(final CosClass cos) {
        this.cos = cos;
    }

    @Override
    public double calculate(double arg, double precision) {
        return cos.calculate(Math.PI / 2 - arg, precision);
    }

}
