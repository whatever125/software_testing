package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;

// https://www.desmos.com/calculator/vdd1ytpqze
public class ArctanSeriesTest {
    @Test
    public void testBasicCases() {
        // Zero
        assertEquals(0, ArctanSeries.arctan(0, 100), 1e-6);
        // Low precision
        assertEquals(Math.PI / 4, ArctanSeries.arctan(1, 1000), 1e-3);
        assertEquals(-Math.PI / 4, ArctanSeries.arctan(-1, 1000), 1e-3);
        // High precision
        assertEquals(Math.atan(0.5), ArctanSeries.arctan(0.5, 100), 1e-6);
        assertEquals(Math.atan(-0.5), ArctanSeries.arctan(-0.5, 100), 1e-6);
    }

    @Test
    // Test arctan(-x) = -arctan(x)
    public void testEvenOddProperty() {
        double x = 0.7;
        assertEquals(-ArctanSeries.arctan(x, 50), ArctanSeries.arctan(-x, 50), 1e-6);
    }

    @Test
    // test x_1 < x_2 => arctan(x_1) < arctan(x_2)
    public void testMonotonicity() {
        assertTrue(ArctanSeries.arctan(0.3, 50) < ArctanSeries.arctan(0.5, 50));
    }

    @Test
    public void testRandom() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            double x = random.nextDouble() * 2 - 1; // x ∈ [-1, 1]
            assertEquals(Math.atan(x), ArctanSeries.arctan(x, 100000), 1e-6);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEdgeCasesInfinity() {
        ArctanSeries.arctan(Double.POSITIVE_INFINITY, 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEdgeCasesNaN() {
        ArctanSeries.arctan(Double.NaN, 100);
    }

    @Test
    public void testEdgeCasesConvergence() {
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(1 + 1e-6, 100));
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(-1 - 1e-6, 100));
    }

    @Test
    public void testEdgeCasesN() {
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(0, -1));
        assertThrows(IllegalArgumentException.class, () -> ArctanSeries.arctan(0, 0));
    }
}
