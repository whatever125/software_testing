package org.example.csv_exporter;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface CSVExporter {
    void exportToCSV(
            String filename, BiFunction<Double, Integer, Double> function,
            double xMin, double xMax, double interval, int terms, double filter) throws IOException, IllegalArgumentException;
    void exportToCSVWithComparison(
            String filename, BiFunction<Double, Integer, Double> function, Function<Double, Double> comparisonFunction,
            double xMin, double xMax, double interval, int terms, double filter) throws IOException, IllegalArgumentException;
}
