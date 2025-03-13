package net.alephdev.trigonometric;

public class CosClass extends TrigonometricFunc{

    public CosClass(double arg){
        super(arg);
    }
    public CosClass(double arg, int iter){
        super(arg, iter);
    }

    @Override
    public double calculate() {
        arg = arg % (2 * Math.PI);

        if (arg > Math.PI) {
            arg -= 2 * Math.PI;
        }
        if (arg < -Math.PI) {
            arg += 2 * Math.PI;
        }

        double result = 1.0;
        double term = 1.0;
        double xSquared = arg * arg;

        for (int i = 1; i <= iter; i++) {
            term = -term * xSquared / ((2 * i - 1) * (2 * i));
            result += term;
        }

        return result;
    }
}
