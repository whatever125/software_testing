package org.example;

import static org.example.MathFuncs.*;

public class MathSystem {
    public static double systemOfFunctions(double x, int terms) {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        if (x <= 0) {
            return (cot(x, terms) - csc(x, terms)) - cot(x, terms);
        } else {
            return (Math.pow(Math.pow(log_2(x, terms), 2) / ln(x, terms), 2) / (ln(x, terms) - log_5(x, terms))) / Math.pow(log_2(x, terms) + (log_2(x, terms) * log_2(x, terms)), 2);
        }
    }
}
