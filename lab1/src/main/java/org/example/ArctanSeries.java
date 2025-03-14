package org.example;

public class ArctanSeries {
    // https://en.wikipedia.org/wiki/Arctangent_series
    public static double arctan(double x, int n) throws IllegalArgumentException {
        if (Double.isNaN(x) || Double.isInfinite(x) || Math.abs(x) > 1)
            throw new IllegalArgumentException("Некорректный ввод: x должен быть в диапазоне [-1, 1]");
        if (n <= 0)
            throw new IllegalArgumentException("Некорректный ввод: n должно быть в диапазоне [1, inf)");

        double sum = 0;
        for (int k = 0; k < n; k++) {
            double term = Math.pow(-1, k) * Math.pow(x, 2 * k + 1) / (2 * k + 1);
            sum += term;
        }
        return sum;
    }
}
