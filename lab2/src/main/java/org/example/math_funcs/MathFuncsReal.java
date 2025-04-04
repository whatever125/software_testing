package org.example.math_funcs;

import java.lang.Math;

public class MathFuncsReal implements MathFuncs {
    public double factorial(int x) throws IllegalArgumentException {
        if (x < 0) {
            throw new IllegalArgumentException("x can't be < 0");
        }
        double product = 1;
        for (int i = 2; i <= x; i++) {
            product *= i;
        }
        return product;
    }

    public double sin(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double summ = 0;
        for (int i = 0; i < terms; i++) {
            summ += Math.pow(-1, i) * Math.pow(x, 2 * i + 1) / factorial(2 * i + 1);
        }
        return summ;
    }

    public double cos(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        return sin(x + Math.PI / 2, terms);
    }

    public double cot(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double sin = sin(x, terms);
        if (sin == 0) {
            return Double.NaN;
        }
        return cos(x, terms) / sin;
    }

    public double csc(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double sin = sin(x, terms);
        if (sin == 0) {
            return Double.NaN;
        }
        return 1 / sin;
    }

    public double ln(double x, int terms) throws IllegalArgumentException {
        if (x <= 0) {
            throw new IllegalArgumentException("x can't be <= 0");
        }
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        double summ = 0;
        if (x < 2) {
            for (int i = 1; i <= terms; i++) {
                summ += Math.pow(-1, i + 1) * Math.pow(x - 1, i) / i;
            }
            return summ;
        } else {
            for (int i = 1; i <= terms; i++) {
                summ += Math.pow(-1, i + 1) * Math.pow(x - 1, -i) / i;
            }
            double acc = ln(x - 1, terms);
            return summ + acc;
        }
    }

    public double log_2(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        return ln(x, terms) / ln(2, terms);
    }

    public double log_5(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        return ln(x, terms) / ln(5, terms);
    }
}
