package net.alephdev.trigonometric;

import static net.alephdev.trigonometric.CosClass.cos;

public class SecClass {

    public static double sec(double x, int iterations) {
        return 1.0 / cos(x, iterations);
    }

}
