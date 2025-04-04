package org.example.math_system;

public class MathSystemStub implements MathSystem {
    public double systemOfFunctions(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub systemOfFunctions called");
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        if (x <= 0) {
            return -1 / Math.sin(x);
        } else {
            if (x < 0.5)
                return -100;
            else if (x > 0.5 && x < 1)
                return -55;
            else if (x > 1)
                return 1;
            else
                return Double.NaN;
        }
    }
}
