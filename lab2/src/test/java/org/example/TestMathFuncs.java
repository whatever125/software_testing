package org.example;

import org.example.csv_exporter.*;
import org.example.math_funcs.*;
import org.example.math_system.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TestMathFuncs {
    // Stub:
    private final Scanner scanner = mock(Scanner.class);
    private final MathFuncsStub mathFuncsStub = spy(new MathFuncsStub());
    // Real:
    private final App app = spy(new App());
    private final CSVExporterReal csvExporter = spy(new CSVExporterReal());
    private final MathFuncsReal mathFuncs = spy(new MathFuncsReal());
    private final MathSystemReal mathSystem = spy(new MathSystemReal());

    @BeforeEach
    public void setUp() {
        // replace csv exporter with spy
        app.setCSVExporter(csvExporter);
        // replace math funcs with spy
        mathSystem.setMathFuncs(mathFuncs);
        verify(mathSystem).setMathFuncs(any());
        // replace math system with spy
        app.setMathSystem(mathSystem);
        // set mocked scanner to imitate user input
        Mockito.when(scanner.nextInt()).thenReturn(1);
        app.setScanner(scanner);
    }

    @Test
    @DisplayName("Test integration")
    public void testApp() throws IOException {
        app.start();
        // verify user input is taken
        verify(scanner).nextInt();
        verifyNoMoreInteractions(scanner);
        // verify csv is exported
        verify(csvExporter).exportToCSV(anyString(), any(BiFunction.class), anyDouble(), anyDouble(), anyDouble(), anyInt(), anyDouble());
        verifyNoMoreInteractions(csvExporter);
        // verify math system is used
        verify(mathSystem, atLeastOnce()).systemOfFunctions(anyDouble(), anyInt());
        verifyNoMoreInteractions(mathSystem);
        // verify math funcs are used
        verify(mathFuncs, atLeastOnce()).factorial(anyInt());
        verify(mathFuncs, atLeastOnce()).sin(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).cos(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).cot(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).csc(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).ln(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).log_2(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).log_5(anyDouble(), anyInt());
        verifyNoMoreInteractions(mathFuncs);
    }

    @Test
    public void testLog2() {
        // Stub ln() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.ln(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).ln(anyDouble(), anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for log_2(x) = ln(x) / ln(2) ---

        // x > 0
        assertEquals(0, mathFuncs.log_2(1, terms), eps);
        assertEquals(1.58496, mathFuncs.log_2(3, terms), eps);
        assertEquals(-3.32193, mathFuncs.log_2(0.1, terms), eps);
        // x = 0
        assertEquals(Double.NEGATIVE_INFINITY, mathFuncs.log_2(0, terms));
        // x < 0
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.log_2(-1, terms));
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.log_2(1, 0));
    }

    @Test
    public void testLog5() {
        // Stub ln() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.ln(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).ln(anyDouble(), anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for log_5(x) = ln(x) / ln(5) ---

        // x > 0
        assertEquals(0, mathFuncs.log_5(1, terms), eps);
        assertEquals(0.68261, mathFuncs.log_5(3, terms), eps);
        assertEquals(-1.43068, mathFuncs.log_5(0.1, terms), eps);
        // x = 0
        assertEquals(Double.NEGATIVE_INFINITY, mathFuncs.log_5(0, terms));
        // x < 0
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.log_5(-1, terms));
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.log_5(1, 0));
    }

    @Test
    public void testCsc() {
        // Stub sin() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.sin(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).sin(anyDouble(), anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for csc(x) = 1 / sin(x) ---

        // Basic cases
        assertEquals(1, mathFuncs.csc(Math.PI / 2, terms), eps);
        assertEquals(-1, mathFuncs.csc(3 * Math.PI / 2, terms), eps);
        // Check csc(-x) = -csc(x)
        assertEquals(-mathFuncs.csc(Math.PI / 2, terms), mathFuncs.csc(-Math.PI / 2, terms), eps);
        assertEquals(-mathFuncs.csc(3 * Math.PI / 2, terms), mathFuncs.csc(-3 * Math.PI / 2, terms), eps);
        // Check edge cases
        assertEquals(Double.NaN, mathFuncs.csc(0, terms));
        assertTrue(Math.abs(mathFuncs.csc(1e-10, terms)) > 1e9);
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.csc(Math.PI / 4, 0));
    }

    @Test
    public void testCot() {
        // Stub sin() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.sin(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).sin(anyDouble(), anyInt());
        // Stub cos() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.cos(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).cos(anyDouble(), anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for cot(x) = cos(x) / sin(x) ---

        // Basic cases
        assertEquals(0, mathFuncs.cot(Math.PI / 2, terms), eps);
        assertEquals(1, mathFuncs.cot(Math.PI / 4, terms), eps);
        assertEquals(-1, mathFuncs.cot(3 * Math.PI / 4, terms), eps);
        // Check cot(-x) == -cot(x)
        assertEquals(-mathFuncs.cot(Math.PI / 4, terms), mathFuncs.cot(-Math.PI / 4, terms), eps);
        // Check edge cases
        assertEquals(Double.NaN, mathFuncs.cot(0, terms));
        assertTrue(Math.abs(mathFuncs.cot(1e-10, terms)) > 1e9);
        assertEquals(Double.NaN, mathFuncs.cot(Math.PI, terms));
        assertTrue(Math.abs(mathFuncs.cot(Math.PI - 1e-10, terms)) > 1e9);
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.cot(Math.PI / 4, 0));
    }

    @Test
    public void testCos() {
        // Stub sin() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.sin(inv.getArgument(0), inv.getArgument(1))
        ).when(mathFuncs).sin(anyDouble(), anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for cos(x) = sin(x + PI/2) ---

        // Basic cases
        assertEquals(1, mathFuncs.cos(0, terms), eps);
        assertEquals(0, mathFuncs.cos(Math.PI / 2, terms), eps);
        assertEquals(-1, mathFuncs.cos(Math.PI, terms), eps);
        // Check cos(-x) == cos(x)
        assertEquals(mathFuncs.cos(Math.PI / 4, terms), mathFuncs.cos(-Math.PI / 4, terms), eps);
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.cos(Math.PI / 4, 0));
    }

    @Test
    public void testSin() {
        // Stub factorial() method
        Mockito.doAnswer(
                inv -> mathFuncsStub.factorial(inv.getArgument(0))
        ).when(mathFuncs).factorial(anyInt());

        double eps = 1e-3;
        int terms = 100;

        // --- Test cases for sin(x) ---

        // Basic cases
        assertEquals(0, mathFuncs.sin(0, terms), eps);
        assertEquals(1, mathFuncs.sin(Math.PI / 2, terms), eps);
        assertEquals(-1, mathFuncs.sin(-Math.PI / 2, terms), eps);
        // Check sin(-x) == -sin(x)
        assertEquals(-mathFuncs.sin(Math.PI / 4, terms), mathFuncs.sin(-Math.PI / 4, terms), eps);
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathFuncs.sin(Math.PI / 4, 0));
    }
}
