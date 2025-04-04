package org.example.math_funcs;

import java.util.Hashtable;
import java.util.Map;

import static java.lang.Math.*;

public class MathFuncsStub implements MathFuncs {
    public double factorial(int x) throws IllegalArgumentException {
        System.out.println("Stub factorial called");
        final Map<Integer, Double> tableValues = new Hashtable<>() {{
            put(0, 1.0);
            put(1, 1.0);
            put(2, 2.0);
            put(3, 6.0);
            put(4, 24.0);
            put(5, 120.0);
            put(6, 720.0);
            put(7, 5040.0);
            put(8, 40320.0);
            put(9, 362880.0);
            put(10, 3628800.0);
        }};
        if (x < 0) {
            throw new IllegalArgumentException("x can't be < 0");
        }
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return sqrt(2 * PI * x) * pow(x / E, x);
    }

    public double sin(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub sin called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, 0.0);                  // 0°
            put(PI / 6, 0.5);               // 30°
            put(PI / 4, sqrt(2) / 2);       // 45°
            put(PI / 3, sqrt(3) / 2);       // 60°
            put(PI / 2, 1.0);               // 90°
            put(2 * PI / 3, sqrt(3) / 2);   // 120°
            put(3 * PI / 4, sqrt(2) / 2);   // 135°
            put(5 * PI / 6, 0.5);           // 150°
            put(PI, 0.0);                   // 180°
        }};
        x = x % (2 * PI);
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        if (x > PI) {
            if (tableValues.containsKey(2 * PI - x)) {
                return -tableValues.get(2 * PI - x);
            }
        }
        return Math.sin(x);
    }

    public double cos(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub cos called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, 1.0);                  // 0°
            put(PI / 6, sqrt(3) / 2);       // 30°
            put(PI / 4, sqrt(2) / 2);       // 45°
            put(PI / 3, 0.5);               // 60°
            put(PI / 2, 0.0);               // 90°
            put(2 * PI / 3, -0.5);          // 120°
            put(3 * PI / 4, -sqrt(2) / 2);  // 135°
            put(5 * PI / 6, -sqrt(3) / 2);  // 150°
            put(PI, -1.0);                  // 180°
        }};
        x = x % (2 * PI);
        if (x > PI) {
            x = 2 * PI - x;
        }
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return Math.cos(x);
    }

    public double cot(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub cot called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, Double.POSITIVE_INFINITY); // 0°
            put(PI / 6, sqrt(3));               // 30°
            put(PI / 4, 1.0);                   // 45°
            put(PI / 3, sqrt(3) / 3);           // 60°
            put(PI / 2, 0.0);                   // 90°
            put(2 * PI / 3, -sqrt(3) / 3);      // 120°
            put(3 * PI / 4, -1.0);              // 135°
            put(5 * PI / 6, -sqrt(3));          // 150°
            put(PI, Double.NEGATIVE_INFINITY);  // 180°
        }};
        x = x % PI;
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return 1 / Math.tan(x);
    }

    public double csc(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub csc called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, Double.NaN);               // 0°
            put(PI / 6, sqrt(3));               // 30°
            put(PI / 4, 1.0);                   // 45°
            put(PI / 3, sqrt(3) / 3);           // 60°
            put(PI / 2, 0.0);                   // 90°
            put(2 * PI / 3, -sqrt(3) / 3);      // 120°
            put(3 * PI / 4, -1.0);              // 135°
            put(5 * PI / 6, -sqrt(3));          // 150°
            put(PI, Double.NaN);                // 180°
        }};
        x = x % (2 * PI);
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        if (x > PI) {
            if (tableValues.containsKey(2 * PI - x)) {
                return -tableValues.get(2 * PI - x);
            }
        }
        return 1 / Math.sin(x);
    }

    public double ln(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub ln called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, Double.NEGATIVE_INFINITY);
            put(0.1, -2.30258509299);
            put(1 / E, -1.0);
            put(0.5, -0.69314718056);
            put(1.0, 0.0);
            put(2.0, 0.69314718056);
            put(E, 1.0); // e = 2.71828182846
            put(3.0, 1.09861228867);
            put(4.0, 1.38629436112);
            put(5.0, 1.60943791243);
            put(6.0, 1.79175946923);
            put(7.0, 1.94591014906);
            put(pow(E, 2), 2.0); // e^2 = 7.38905609893
            put(8.0, 2.07944154168);
            put(9.0, 2.19722457734);
            put(10.0, 2.30258509299);
        }};
        if (x < 0) {
            throw new IllegalArgumentException("x can't be < 0");
        }
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return Math.log(x);
    }

    public double log_2(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub log_2 called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, Double.NEGATIVE_INFINITY);
            put(0.1, -3.32192809489);
            put(0.5, -1.0);
            put(1.0, 0.0);
            put(2.0, 1.0);
            put(3.0, 1.58496250072);
            put(4.0, 2.0);
            put(5.0, 2.32192809489);
            put(6.0, 2.58496250072);
            put(7.0, 2.80735492206);
            put(8.0, 3.0);
            put(9.0, 3.16992500144);
            put(10.0, 3.32192809489);
        }};
        if (x < 0) {
            throw new IllegalArgumentException("x can't be < 0");
        }
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return Math.log(x) / Math.log(2);
    }

    public double log_5(double x, int terms) throws IllegalArgumentException {
        System.out.println("Stub log_5 called");
        final Map<Double, Double> tableValues = new Hashtable<>() {{
            put(0.0, Double.NEGATIVE_INFINITY);
            put(0.1, -1.43067655807);
            put(0.2, -1.0);
            put(0.5, -0.430676558073);
            put(1.0, 0.0);
            put(2.0, 0.430676558073);
            put(3.0, 0.682606194486);
            put(4.0, 0.861353116147);
            put(5.0, 1.0);
            put(6.0, 1.11328275256);
            put(7.0, 1.20906195512);
            put(8.0, 1.29202967422);
            put(9.0, 1.36521238897);
            put(10.0, 1.43067655807);
        }};
        if (x < 0) {
            throw new IllegalArgumentException("x can't be < 0");
        }
        if (tableValues.containsKey(x)) {
            return tableValues.get(x);
        }
        return Math.log(x) / Math.log(5);
    }
}
