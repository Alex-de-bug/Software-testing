package net.alephdev;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import net.alephdev.function.FunctionalSystemClass;
import net.alephdev.function.IterableFunction;
import net.alephdev.function.logariphmic.AnyLogarithm;
import net.alephdev.function.logariphmic.BaseELogarithm;
import net.alephdev.function.trigonometric.CosClass;
import net.alephdev.function.trigonometric.CotClass;
import net.alephdev.function.trigonometric.SecClass;
import net.alephdev.function.trigonometric.SinClass;
import net.alephdev.function.trigonometric.TanClass;

public class FunctionCSVExporter {

    public static void exportToCSV(
            IterableFunction function, 
            double start,             
            double end,               
            double step,              
            String filename           
    ) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setMinusSign('-');
        DecimalFormat df = new DecimalFormat("0." + "0".repeat(4), symbols);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X,Результаты модуля (X)\n");

            for (double x = start; x <= end; x += step) {
                try {
                    double result = function.calculate(Double.parseDouble(df.format(x)), 1e-10); 
                    writer.write(df.format(x) + "," + df.format(result) + "\n");       
                } catch (ArithmeticException e) {
                    writer.write(df.format(x) + ",undefined\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        SinClass sin = new SinClass();
        CosClass cos = new CosClass();
        TanClass tan = new TanClass();
        SecClass sec = new SecClass();
        CotClass cot = new CotClass();
        BaseELogarithm ln = new BaseELogarithm();
        AnyLogarithm log2 = new AnyLogarithm(2);
        FunctionalSystemClass func = new FunctionalSystemClass();

        exportToCSV(sin, 0, 2 * Math.PI, 0.1, "results/sin_results.csv");
        exportToCSV(cos, 0, 2 * Math.PI, 0.1, "results/cos_results.csv");
        exportToCSV(tan, 0, 2 * Math.PI, 0.1, "results/tan_results.csv");
        exportToCSV(sec, 0, 2 * Math.PI, 0.1, "results/sec_results.csv");
        exportToCSV(cot, 0, 2 * Math.PI, 0.1, "results/cot_results.csv");
        exportToCSV(ln, 0.1, 10, 0.1, "results/ln_results.csv");
        exportToCSV(log2, 0.1, 10, 0.1, "results/log2_results.csv");
        exportToCSV(func, -1, 10, 0.01, "results/func_system.csv");
    }
}