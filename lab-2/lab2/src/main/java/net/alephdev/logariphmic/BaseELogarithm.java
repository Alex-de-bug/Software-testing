package net.alephdev.logariphmic;

public class BaseELogarithm {
    public static double ln(double x, int iterations) {
        if (x <= 0) {
            throw new IllegalArgumentException("Логарифм определен только для положительных чисел");
        }

        double z = x - 1;

        if (x > 2 || x < 0.5) {
            int k = 0;
            while (x > 2) {
                x /= Math.E;
                k++;
            }
            while (x < 0.5) {
                x *= Math.E;
                k--;
            }
            z = x - 1;

            return k + lnTaylor(z, iterations);
        }

        return lnTaylor(z, iterations);
    }

    private static double lnTaylor(double z, int iterations) {
        double result = 0;
        double term = z;

        for (int n = 1; n <= iterations; n++) {
            result += term / n;
            term = -term * z;
        }

        return result;
    }
}
