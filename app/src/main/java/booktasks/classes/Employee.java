package booktasks.classes;

import booktasks.interfaces.Measurable;

import java.util.Arrays;
import java.util.stream.Stream;

public class Employee implements Measurable {
    private final String name;
    private final double salary;


    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public static double average(Measurable[] objects) {
        Stream<Double> stream = Arrays.stream(objects).map(Measurable::getMeasure);
        return stream.reduce(Double::sum).get() / objects.length;
    }

    public static Measurable largest(Measurable[] employees) {
        Arrays.sort(employees, ((o1, o2) -> o1.getMeasure() < o2.getMeasure() ? 1 : -1));
        return employees[0];
    }

    public double getMeasure() {
        return getSalary();
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}
