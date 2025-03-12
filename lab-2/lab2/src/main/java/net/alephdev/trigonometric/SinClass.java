package net.alephdev.trigonometric;

import static net.alephdev.trigonometric.CosClass.cos;

public class SinClass {

    public static double sin(double x, int iterations) {
        return cos(x + Math.PI / 2, iterations);
    }

}
