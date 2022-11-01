/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package booktasks;

import booktasks.classes.*;
import booktasks.classes.Arrays;
import booktasks.classes.Stack;
import booktasks.exceptions.IllegalFileFormatException;
import booktasks.interfaces.IntSequence;
import booktasks.interfaces.Measurable;
import com.google.common.base.Strings;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {
    private static final String NUMBERS_FILEPATH = "/home/klimandr/numbers";

    private final static Scanner in = new Scanner(System.in);
    private final static Random random = new Random();

    public static void main(String[] args) {
        System.out.print("Type exercise: ");
        String exercise = in.nextLine();
        switch (exercise) {
            case "2.13" -> ex213();
            case "3.1" -> ex31();
            case "3.2" -> ex32();
            case "3.4" -> ex34();
            case "3.5" -> ex35();
            case "3.6" -> ex36();
            case "3.8" -> ex38();
            case "3.9" -> ex39();
            case "3.12" -> ex312();
            case "props" -> props();
            case "5.1" -> ex51();
            case "5.2" -> ex52();
            case "5.11" -> ex511();
            case "6.1" -> ex61();
            case "6.2" -> ex62();
            case "6.3" -> ex63();
            case "6.5" -> ex65();
            case "6.6" -> ex66();
            case "6.7" -> ex67();
            case "6.11" -> ex611();
            case "6.20" -> ex620();
            case "6.21" -> ex621();
            default -> ex();
        }
    }

    /**
     * @deprecated not working
     */
    private static void ex621() {
        List<String>[] result = Arrays.construct(10);
        System.out.println(result.toString());
    }

    private static void ex620() {
        Integer[] list = repeat(5, random.nextInt(), random.nextInt());
        System.out.println(java.util.Arrays.toString(list));
    }

    @SafeVarargs public static final <T> T[] repeat(int n, T... objs) {
        T[] result;
        @SuppressWarnings("unchecked") T[] newArray = (T[]) java.lang.reflect.Array.newInstance(
            objs.getClass().getComponentType(),
            n * objs.length
        );
        result = newArray;
        for (int i = 1; i <= objs.length; i++) {
            for (int j = 1; j <= n; j++) result[(i * n) - n + j - 1] = objs[i - 1];
        }
        return result;
    }

    public static <T> T[] repeat(int n, T obj, T[] array) {
        T[] result;
        if (array.length >= n)
            result = array;
        else {
            @SuppressWarnings("unchecked") T[] newArray
                    = (T[]) java.lang.reflect.Array.newInstance(
                    array.getClass().getComponentType(), n);
            result = newArray;
        }
        for (int i = 0; i < n; i++) result[i] = obj;
        return result;
    }

    private static void ex611() {
        Integer[] numbers = new Integer[]{random.nextInt(), random.nextInt(), random.nextInt(), random.nextInt()};
        Pair<Integer> minMax = Arrays.minMax(numbers);
        System.out.println(java.util.Arrays.toString(numbers));
        System.out.println("Min: " + minMax.getFirst());
        System.out.println("Max: " + minMax.getSecond());
    }

    private static void ex67() {
        Pair<Integer> pair = new Pair<>(random.nextInt(), random.nextInt());
        System.out.println("First: " + pair.getFirst());
        System.out.println("Second: " + pair.getSecond());
        System.out.println("Min: " + pair.min());
        System.out.println("Max: " + pair.max());
    }

    private static void ex66() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Andrey", 1000.0));
        employees.add(new Employee("Pavel", 3500.0));
        ArrayList<Manager> managers = new ArrayList<>();
        managers.add(new Manager("Georgy", 5000.0));
        ArrayList<Employee> all = /*(ArrayList<Employee>)*/ merge(employees, managers);
        System.out.println("The list:");
        for (Employee employee : all) {
            System.out.println(employee.getName());
        }
    }

    private static <E> ArrayList<E> merge(ArrayList<E> list, ArrayList<? extends E> anotherList) {
        list.addAll(anotherList);
        return list;
    }

//    private static <E> ArrayList<? super E> merge(ArrayList<? super E> list, ArrayList<E> anotherList) {
//        list.addAll(anotherList);
//        return list;
//    }

    private static void ex65() {
        Double[] result = swap(0, 1, 1.5, 2.0, 3.0);
    }

    private static <T> T[] swap(int i, int j, T... values) {
        T temp = values[i];
        values[i] = values[j];
        values[j] = temp;
        return values;
    }

    private static void ex63() {
        Table<String, Integer> table = new Table<>();
        table.set("first", 1);
        table.set("second", 2);
        try {
            Integer value = table.get("third");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Second value is " + table.get("second"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ex62() {
        AnotherArrayStack<Integer> stack = new AnotherArrayStack<>();
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stack.push(random.nextInt());
        if (!stack.isEmpty()) {
            try {
                System.out.println("Now it's not empty with the number " + stack.pop());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void ex61() {
        Stack<Integer> stack = new Stack<>();
        try {
            stack.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        stack.push(random.nextInt());
        if (!stack.isEmpty()) {
            try {
                System.out.println("Now it's not empty with the number " + stack.pop());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void ex511() {
        NoSuchMethodException ex = new NoSuchMethodException("New exception");
        ex.printStackTrace();
    }

    private static void ex52() {
        try {
            System.out.println(sumOfValues(NUMBERS_FILEPATH));
        } catch (FileNotFoundException | IllegalFileFormatException e) {
            System.out.println("EXCEPTION: " + e.getClass().getSimpleName());
        }
    }

    private static Double sumOfValues(String filepath) throws FileNotFoundException, IllegalFileFormatException {
        Double sum = 0.0;
        for (Double number : readValues(filepath)) {
            sum += number;
        }
        
        return sum;
    }

    private static void ex51() {
        ArrayList<Double> doubles;
        try {
            doubles = readValues(NUMBERS_FILEPATH);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getClass().getSimpleName());
            return;
        }

        doubles.forEach(System.out::println);
    }

    private static ArrayList<Double> readValues(String filepath) throws FileNotFoundException, IllegalFileFormatException {
        ArrayList<Double> list = new ArrayList<>();

        try (
            FileReader reader = new FileReader(filepath);
            Scanner scanner = new Scanner(reader);
        ) {
            while (scanner.hasNextLine()) {
                try {
                    list.add(Double.valueOf(scanner.nextLine()));
                } catch (NumberFormatException e) {
                    throw new IllegalFileFormatException();
                }
            }
        } catch (IOException e) {
            FileNotFoundException fnfEx = new FileNotFoundException();
            fnfEx.addSuppressed(e);
            throw fnfEx;
        }

        return list;
    }

    private static void props() {
        Properties p = System.getProperties();
        p.list(System.out);
    }

    private static void ex312() {
        File directory = new File("/home/klimandr/workspace");
        if (!directory.isDirectory()) {
            System.out.println("It is not a directory");
            return;
        }

        for (String filename : Objects.requireNonNull(directory.list((File dir, String file) -> file.endsWith(".csv")))) {
            System.out.println(filename);
        }
    }

    private static void ex39() {
        Greeter greeter = new Greeter(random.nextInt(1, 7), "First");
        Greeter anotherGreeter = new Greeter(random.nextInt(1, 7), "Second");
        Thread firstThread = new Thread(greeter);
        Thread scndThread = new Thread(anotherGreeter);

        firstThread.start();
        scndThread.start();
    }

    private static void ex38() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Vladimir");
        list.add("Andrey");
        list.add("Zack");
        list.add("Jack");
        list.add("Igor");
        list.add("Charles");
        list.add("Alexander");

        luckySort(list, String::compareToIgnoreCase);
    }

    private static void luckySort(ArrayList<String> strings, Comparator<String> comp) {
        ArrayList<String> sortedStrings = new ArrayList<>(strings);
        sortedStrings.sort(comp);
        int count = 0;

        while (!strings.equals(sortedStrings)) {
            Collections.shuffle(strings);
            count++;
        }

        System.out.printf("It took %d times", count);
    }

    private static void ex36() {
        System.out.print("Type number: ");
        int number = in.nextInt();

        SquareSequence seq = new SquareSequence();

        for (int i = 1; i <= number; i++) {
            System.out.print(seq.next().toString());
            if (i != number) System.out.print(", ");
        }
    }

    private static void ex35() {
        System.out.print("Type digit: ");
        int digit = in.nextInt();

        IntSequence constSeq = IntSequence.generate(() -> new IntSequence() {
            public boolean hasNext() {
                return true;
            }

            public int next() {
                return digit;
            }
        });

        for (int i = 1; i <= digit; i++) {
            System.out.println(i + ". " + constSeq.next());
        }
    }

    private static void ex34() {
        System.out.print("Type sequence: ");
        String s = in.nextLine();
        String[] sequence = s.split(" ");
        IntSequence intSequence = IntSequence.of(getIntSequenceFromStringSequence(sequence));

        System.out.print("Sequence: ");
        while (intSequence.hasNext()) {
            System.out.print(intSequence.next() + ", ");
        }
    }

    private static int[] getIntSequenceFromStringSequence(String[] sequence) {
        int[] ints = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            ints[i] = Integer.parseInt(sequence[i]);
        }
        return ints;
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
