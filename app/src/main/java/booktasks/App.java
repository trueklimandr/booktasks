/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package booktasks;

import booktasks.classes.Employee;
import booktasks.interfaces.Measurable;
import com.google.common.base.Strings;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {
    private final static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Type exercise: ");
        String exercise = in.next();
        switch (exercise) {
            case "2.13" -> ex213();
            case "3.1" -> ex31();
            case "3.2" -> ex32();
            default -> ex();
        }
    }

    private static void ex32() {
        Measurable[] employees = getEmployees();
        Employee largest = (Employee) Employee.largest(employees);
        System.out.println(largest.getName() + " (" + largest.getMeasure() + ")");
    }

    private static void ex31() {
        Measurable[] employees = getEmployees();
        System.out.println(Employee.average(employees));
    }

    private static Measurable[] getEmployees() {
        return new Employee[]{
            new Employee("Fedor", 40000.0),
            new Employee("Alexander", 45000.5),
            new Employee("Ivan", 32500.5)
        };
    }

    private static void ex213() {
        CSVReader reader;
        try {
            reader = new CSVReaderBuilder(new FileReader("/home/klimandr/workspace/Test.csv")).build();
        } catch (Exception ignored) {
            reader = null;
            System.out.println("There is no file");
        }
        if (reader != null) {
            processCSV(reader);
        }
    }

    private static void processCSV(CSVReader reader) {
        String [] nextLine;
        while (true) {
            try {
                if ((nextLine = reader.readNext()) == null) break;
            } catch (IOException | CsvValidationException e) {
                throw new RuntimeException(e);
            }
            System.out.println(
                nextLine[0] +
                Strings.repeat(" ", (int) (4 - nextLine[0].codePoints().count())) +
                "| " +
                nextLine[1] +
                Strings.repeat(" ", (int) (13 - nextLine[1].codePoints().count())) +
                "| " +
                nextLine[2]
            );
        }
    }

    private static void ex() {
        System.out.println("There is no such exercise number");
    }
}
