package net.alephdev.trigonometric;

public abstract class TrigonometricFunc {

    private static final int DEFAULT_ITERATION = 1000;

    protected double arg;
    protected final int iter;

    protected TrigonometricFunc(final double arg) {
        this.arg = arg;
        this.iter = DEFAULT_ITERATION;
    }

    protected TrigonometricFunc(final double arg, final int iter) {
        this.arg = arg;
        this.iter = iter;
    }

    public abstract double calculate();
}
