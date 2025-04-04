package org.example.math_system;

import org.example.math_funcs.MathFuncsReal;
import org.example.math_funcs.MathFuncs;

public class MathSystemReal implements MathSystem {
    private MathFuncs mathFuncs;

    public MathSystemReal() {
        this.mathFuncs = new MathFuncsReal();
    }

    public double systemOfFunctions(double x, int terms) throws IllegalArgumentException {
        if (terms < 1) {
            throw new IllegalArgumentException("terms can't be < 1");
        }
        if (x <= 0) {
            return (mathFuncs.cot(x, terms) - mathFuncs.csc(x, terms)) - mathFuncs.cot(x, terms);
        } else {
            return (Math.pow(Math.pow(mathFuncs.log_2(x, terms), 2) / mathFuncs.ln(x, terms), 2) / (mathFuncs.ln(x, terms) - mathFuncs.log_5(x, terms))) / Math.pow(mathFuncs.log_2(x, terms) + (mathFuncs.log_2(x, terms) * mathFuncs.log_2(x, terms)), 2);
        }
    }

    public void setMathFuncs(MathFuncs mathFuncs) {
        this.mathFuncs = mathFuncs;
    }
}
