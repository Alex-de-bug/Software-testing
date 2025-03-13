package net.alephdev;

public abstract class IterableFunction {

    private static final int DEFAULT_ITERATION = 1000;

    public  static final double EPSILON = 1e-10;

    protected int iter;

    protected IterableFunction(){
        this.iter = DEFAULT_ITERATION;
    }

    public abstract double calculate(final double arg, final double precision);
}
