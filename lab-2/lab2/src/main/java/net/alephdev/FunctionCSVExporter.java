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
            String filename,
            String delimiter
    ) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setMinusSign('-');
        DecimalFormat df = new DecimalFormat("0." + "0".repeat(4), symbols);

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("X"+delimiter+"Результаты модуля (X)\n");

            for (double x = start; x <= end; x += step) {
                try {
                    double result = function.calculate(Double.parseDouble(df.format(x)), 1e-10); 
                    writer.write(df.format(x) + delimiter + df.format(result) + "\n");
                } catch (ArithmeticException e) {
                    writer.write(df.format(x) + delimiter + "undefined\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        if(args.length < 1) {
            System.err.println("Использование: lab2 [all/sin/cos/tan/sec/cot/ln/log2/func] (delimiter) (step)");
            return;
        }


        String mode = args[0].toLowerCase();
        String delimiter = args.length > 1 ? args[1] : ",";
        double step = 1;
        try {
            step = args.length > 2 ? Double.parseDouble(args[2]) : 1;
        } catch(NumberFormatException e) {
            System.err.println("Неверное число шага.");
            return;
        }

        SinClass sin = new SinClass();
        CosClass cos = new CosClass();
        TanClass tan = new TanClass();
        SecClass sec = new SecClass();
        CotClass cot = new CotClass();
        BaseELogarithm ln = new BaseELogarithm();
        AnyLogarithm log2 = new AnyLogarithm(2);
        FunctionalSystemClass func = new FunctionalSystemClass();

        switch (mode) {
            case "all":
                exportToCSV(sin, 0, 2 * Math.PI, step, "results/sin_results.csv", delimiter);
                exportToCSV(cos, 0, 2 * Math.PI, step, "results/cos_results.csv", delimiter);
                exportToCSV(tan, 0, 2 * Math.PI, step, "results/tan_results.csv", delimiter);
                exportToCSV(sec, 0, 2 * Math.PI, step, "results/sec_results.csv", delimiter);
                exportToCSV(cot, 0, 2 * Math.PI, step, "results/cot_results.csv", delimiter);
                exportToCSV(ln, 0.1, 10, step, "results/ln_results.csv", delimiter);
                exportToCSV(log2, 0.1, 10, step, "results/log2_results.csv", delimiter);
                exportToCSV(func, -6, 10, step, "results/func_system.csv", delimiter);
                break;
            case "sin":
                exportToCSV(sin, 0, 2 * Math.PI, step, "results/sin_results.csv", delimiter);
                break;
            case "cos":
                exportToCSV(cos, 0, 2 * Math.PI, step, "results/cos_results.csv", delimiter);
                break;
            case "tan":
                exportToCSV(tan, 0, 2 * Math.PI, step, "results/tan_results.csv", delimiter);
                break;
            case "sec":
                exportToCSV(sec, 0, 2 * Math.PI, step, "results/sec_results.csv", delimiter);
                break;
            case "cot":
                exportToCSV(cot, 0, 2 * Math.PI, step, "results/cot_results.csv", delimiter);
                break;
            case "ln":
                exportToCSV(ln, 0, 10, step, "results/ln_results.csv", delimiter);
                break;
            case "log2":
                exportToCSV(log2, 0, 10, step, "results/log2_results.csv", delimiter);
                break;
            case "func":
                exportToCSV(func, -6, 10, step, "results/func_system.csv", delimiter);
                break;
            default:
                System.err.println("Неизвестный режим: " + mode);
                System.err.println("Доступные режимы: all, sin, cos, tan, sec, cot, ln, log2, func");
                break;
        }
    }
}