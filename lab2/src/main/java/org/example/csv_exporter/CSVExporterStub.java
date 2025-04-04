package org.example.csv_exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CSVExporterStub implements CSVExporter {
    public void exportToCSV(String filename, BiFunction<Double, Integer, Double> function,
                            double xMin, double xMax, double interval, int terms, double filter)
            throws IOException, IllegalArgumentException {
        System.out.println("Stub exportToCSV called");
        if (filename == null || function == null) {
            throw new IllegalArgumentException("arguments can't be null");
        }
        File file = new File(filename);
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("x,y\n");
        }
    }

    public void exportToCSVWithComparison(String filename, BiFunction<Double, Integer, Double> function,
                                          Function<Double, Double> comparisonFunction,
                                          double xMin, double xMax, double interval, int terms, double filter)
            throws IOException, IllegalArgumentException {
        System.out.println("Stub exportToCSVWithComparison called");
        if (filename == null || function == null) {
            throw new IllegalArgumentException("arguments can't be null");
        }
        File file = new File(filename);
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("x,y\n");
        }
    }
}
