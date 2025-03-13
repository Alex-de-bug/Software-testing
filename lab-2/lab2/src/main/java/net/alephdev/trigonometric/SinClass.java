package net.alephdev.trigonometric;

import net.alephdev.IterableFunction;

public class SinClass extends IterableFunction {

    private final CosClass cos;

    public SinClass() {
        super();
        this.cos = new CosClass();
    }

    public SinClass(final CosClass cos) {
        super();
        this.cos = cos;
    }

    @Override
    public double calculate(double arg, double precision) {
        return cos.calculate(arg + Math.PI / 2, precision);
    }

}
