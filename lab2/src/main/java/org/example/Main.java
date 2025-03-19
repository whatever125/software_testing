package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Generating CSV");

        try {
            CSVExporter.exportToCSV("export/test.csv", MathSystem::systemOfFunctions, -10, 10, 0.01, 100, 20);
        } catch (IOException e) {
            System.out.println("Error writing to CSV:");
            System.out.println(e.getMessage());
        }

        try {
            CSVExporter.exportToCSVWithComparison("export/test1.csv", MathFuncs::sin, Math::sin, -10, 10, 0.01, 100, 20);
        } catch (IOException e) {
            System.out.println("Error writing to CSV:");
            System.out.println(e.getMessage());
        }
    }
}
