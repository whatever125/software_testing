package org.example.math_funcs;

public interface MathFuncs {
    double factorial(int x) throws IllegalArgumentException;
    double sin(double x, int terms) throws IllegalArgumentException;
    double cos(double x, int terms) throws IllegalArgumentException;
    double cot(double x, int terms) throws IllegalArgumentException;
    double csc(double x, int terms) throws IllegalArgumentException;
    double ln(double x, int terms) throws IllegalArgumentException;
    double log_2(double x, int terms) throws IllegalArgumentException;
    double log_5(double x, int terms) throws IllegalArgumentException;
}
