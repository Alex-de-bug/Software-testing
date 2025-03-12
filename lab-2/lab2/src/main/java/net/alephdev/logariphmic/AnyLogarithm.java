package net.alephdev.logariphmic;

import static net.alephdev.logariphmic.BaseELogarithm.*;

public class AnyLogarithm {
    public static double logN(double x, double n, int iterations) {
        if (x <= 0) {
            throw new IllegalArgumentException("Логарифм определен только для положительных чисел");
        }
        if (n <= 0 || n == 1) {
            throw new IllegalArgumentException("Основание должно быть положительным и не равным 1");
        }

        return ln(x, iterations) / ln(n, iterations);
    }
}
