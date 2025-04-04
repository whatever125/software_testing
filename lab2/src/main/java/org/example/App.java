package org.example;

import org.example.csv_exporter.*;
import org.example.math_system.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private CSVExporter csvExporter;
    private MathSystem mathSystem;
    private Scanner scanner;

    public App() {
        csvExporter = new CSVExporterReal();
        mathSystem = new MathSystemReal();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Generating CSV\n");

        System.out.print("""
                Available actions:
                1) Export system function plot to CSV
                
                Choose your action:\s""");

        int action;
        while (true) {
            try {
                action = scanner.nextInt();
                if (action < 1 || action > 2) {
                    System.out.println("Incorrect action number!");
                    throw new IllegalArgumentException();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.print("Incorrect input. Please enter an integer.\nChoose your action: ");
                scanner.next();
            } catch (Exception e) {
                System.out.print("Choose your action: ");
            }
        }

        if (action == 1) {
            try {
                csvExporter.exportToCSV("export/test.csv", mathSystem::systemOfFunctions, -10, 10, 0.05, 100, 20);
            } catch (Exception e) {
                System.out.println("Error writing to CSV:");
                System.out.println(e.getMessage());
            }
        }

    }

    public void setCSVExporter(CSVExporter csvExporter) {
        this.csvExporter = csvExporter;
    }

    public void setMathSystem(MathSystem mathSystem) {
        this.mathSystem = mathSystem;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
