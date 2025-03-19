package org.example;

import java.lang.Math;

public class MathFuncs {
    public static int factorial(int x) {
        if (x < 1) {
            throw new IllegalArgumentException("x can't be < 1");
        }
        int product = 1;
        for (int i = 2; i <= x; i++) {
            product *= i;
        }
        return product;
    }

    public static double sin(double x, int terms) {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double summ = 0;
        for (int i = 0; i < terms; i++) {
            summ += Math.pow(-1, i + 1) * Math.pow(x, 2 * i + 1) / factorial(2 * i + 1);
        }
        return summ;
    }

    public static double cos(double x, int terms) {
        return sin(x + Math.PI / 2, terms);
    }

    public static double cot(double x, int terms) {
        return cos(x, terms) / sin(x, terms);
    }

    public static double csc(double x, int terms) {
        return 1 / sin(x, terms);
    }

    public static double ln(double x, int terms) {
        if (x <= 0) {
            throw new IllegalArgumentException("x can't be <= 0");
        }
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double summ = 0;
        for (int i = 1; i <= terms; i++) {
            summ += Math.pow(-1, i + 1) * Math.pow(x - 1, i) / i;
        }
        return summ;
    }

    public static double log_2(double x, int terms) {
        return ln(x, terms) / ln(2, terms);
    }

    public static double log_5(double x, int terms) {
        return ln(x, terms) / ln(5, terms);
    }
}
