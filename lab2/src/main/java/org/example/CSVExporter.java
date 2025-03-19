package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CSVExporter {
    public static void exportToCSV(String filename, BiFunction<Double, Integer, Double> function,
                                   double xMin, double xMax, double interval, int terms, double filter) throws IOException {
        File file = new File(filename);
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("x,y\n");
            for (double i = xMin; i < xMax; i += interval) {
                double value = function.apply(i, terms);
                if (!Double.isNaN(filter) && Math.abs(value) > filter)
                    value = Double.NaN;
                writer.append(String.valueOf(i)).append(",").append(String.valueOf(value)).append("\n");
            }
        }
    }
    public static void exportToCSVWithComparison(String filename, BiFunction<Double, Integer, Double> function,
                                                 Function<Double, Double> comparisonFunction,
                                   double xMin, double xMax, double interval, int terms, double filter) throws IOException {
        File file = new File(filename);
        file.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.append("x,y1,y2\n");
            for (double i = xMin; i < xMax; i += interval) {
                double value1 = function.apply(i, terms);
                double value2 = comparisonFunction.apply(i);
                if (!Double.isNaN(filter) && Math.abs(value1) > filter)
                    value1 = Double.NaN;
                if (!Double.isNaN(filter) && Math.abs(value2) > filter)
                    value2 = Double.NaN;
                writer.append(String.valueOf(i)).append(",").append(String.valueOf(value1)).append(",").append(String.valueOf(value2)).append("\n");
            }
        }
    }
}
