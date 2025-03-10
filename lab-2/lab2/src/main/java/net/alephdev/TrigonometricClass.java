package net.alephdev;

public class TrigonometricClass {
    public static double cos(double x, int iterations) {
        x = x % (2 * Math.PI);

        if (x > Math.PI) {
            x -= 2 * Math.PI;
        }
        if (x < -Math.PI) {
            x += 2 * Math.PI;
        }

        double result = 1.0;
        double term = 1.0;
        double xSquared = x * x;

        for (int i = 1; i <= iterations; i++) {
            term = -term * xSquared / ((2 * i - 1) * (2 * i));
            result += term;
        }

        return result;
    }

    public static double sin(double x, int iterations) {
        return cos(x + Math.PI / 2, iterations);
    }

    public static double sec(double x, int iterations) {
        return 1.0 / cos(x, iterations);
    }

    public static double cot(double x, int iterations) {
        return 1.0 / tan(x, iterations);
    }

    public static double tan(double x, int iterations) {
        return sin(x, iterations) / cos(x, iterations);
    }
}
