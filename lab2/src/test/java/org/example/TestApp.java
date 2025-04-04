package org.example;

import org.example.csv_exporter.*;
import org.example.math_system.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.BiFunction;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TestApp {
    // Real:
    private final App app = spy(new App());
    // Stubs:
    private final CSVExporterStub csvExporter = spy(new CSVExporterStub());
    private final MathSystemStub mathSystem = spy(new MathSystemStub());
    private final Scanner scanner = mock(Scanner.class);

    @BeforeEach
    public void setUp() {
        // replace csv exporter with stub
        app.setCSVExporter(csvExporter);
        // replace math system with stub
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
    }
}
