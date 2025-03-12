package net.alephdev.trigonometric;

import static net.alephdev.trigonometric.CosClass.cos;
import static net.alephdev.trigonometric.SinClass.sin;

public class TanClass {

    public static double tan(double x, int iterations) {
        return sin(x, iterations) / cos(x, iterations);
    }

}
