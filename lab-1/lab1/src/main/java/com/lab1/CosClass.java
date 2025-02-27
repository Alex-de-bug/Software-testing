package com.lab1;

public class CosClass
{
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
}
