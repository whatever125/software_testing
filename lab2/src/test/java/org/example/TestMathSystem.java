package org.example;

import org.example.csv_exporter.*;
import org.example.math_funcs.*;
import org.example.math_system.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TestMathSystem {
    // Stub:
    private final MathFuncsStub mathFuncs = spy(new MathFuncsStub());
    private final Scanner scanner = mock(Scanner.class);
    // Real:
    private final App app = spy(new App());
    private final CSVExporterReal csvExporter = spy(new CSVExporterReal());
    private final MathSystemReal mathSystem = spy(new MathSystemReal());

    @BeforeEach
    public void setUp() {
        // replace csv exporter with spy
        app.setCSVExporter(csvExporter);
        // replace math funcs with stub
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
        verify(mathFuncs, atLeastOnce()).cot(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).csc(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).ln(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).log_2(anyDouble(), anyInt());
        verify(mathFuncs, atLeastOnce()).log_5(anyDouble(), anyInt());
        verifyNoMoreInteractions(mathFuncs);
    }

    @Test
    @DisplayName("Test MathSystem basic cases")
    public void testMathSystemBasic() {
        // x <= 0
        double x = -Math.PI / 2;
        int terms = 100;
        double eps = 1e-3;
        assertEquals(1, mathSystem.systemOfFunctions(x, terms), eps);
        verify(mathFuncs, times(2)).cot(x, terms);
        verify(mathFuncs, times(1)).csc(x, terms);
        verifyNoMoreInteractions(mathFuncs);
        // x > 0
        x = 2;
        assertEquals(1.98248, mathSystem.systemOfFunctions(x, terms), eps);
        verify(mathFuncs, times(4)).log_2(x, terms);
        verify(mathFuncs, times(2)).ln(x, terms);
        verify(mathFuncs, times(1)).log_5(x, terms);
        verifyNoMoreInteractions(mathFuncs);
    }

    @Test
    @DisplayName("Test MathSystem exception")
    public void testMathSystemException() {
        // terms < 1
        assertThrowsExactly(IllegalArgumentException.class, () -> mathSystem.systemOfFunctions(0, 0));
        verifyNoMoreInteractions(mathFuncs);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2 * Math.PI, -1 * Math.PI, 0, 1})
    @DisplayName("Test MathSystem NaN")
    public void testMathSystemNaN(double x) {
        int terms = 100;
        assertEquals(Double.NaN, mathSystem.systemOfFunctions(x, terms));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5})
    @DisplayName("Test MathSystem Infinity")
    public void testMathSystemInfinity(double x) {
        int terms = 100;
        assertEquals(Double.NEGATIVE_INFINITY, mathSystem.systemOfFunctions(x, terms));
    }
}
