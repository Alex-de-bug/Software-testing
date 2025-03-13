package net.alephdev.function;

public abstract class IterableFunction {
    public  static final double EPSILON = 1e-10;
    public abstract double calculate(final double arg, final double precision);
}
