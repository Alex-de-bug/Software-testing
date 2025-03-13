package net.alephdev.function.trigonometric;

import net.alephdev.function.IterableFunction;

public class CosClass extends IterableFunction {

    @Override
    public double calculate(final double arg, final double precision) {
        double result = 1.0; // Начальное значение результата (первый член ряда)
        double term = 1.0;   // Текущий член ряда
        double x = arg;      // Аргумент косинуса
        int n = 1;           // Счётчик для итераций

        // Приводим аргумент к диапазону [0, 2*PI] для улучшения точности
        x = x % (2 * Math.PI);

        while (Math.abs(term) >= precision) {
            term *= -x * x / ((2 * n - 1) * (2 * n)); // Вычисляем следующий член ряда
            result += term; // Добавляем его к результату
            n++; // Увеличиваем счётчик
            if(n > 1000) break;
        }

        return result;
    }
}
