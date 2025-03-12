package net.alephdev.trigonometric;

import static net.alephdev.trigonometric.TanClass.tan;

public class CotClass {

    public static double cot(double x, int iterations) {
        return 1.0 / tan(x, iterations);
    }

}
