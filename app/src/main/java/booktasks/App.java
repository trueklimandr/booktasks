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
import com.google.common.collect.Streams;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
//import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final String NUMBERS_FILEPATH = "/home/klimandr/numbers";
    private static final String TEXT_FILEPATH = "/home/klimandr/text";
    private static final String ALICE_TEXT_FILEPATH = "/home/klimandr/alice.txt";
    private static final String WAP_TEXT_FILEPATH = "/home/klimandr/wap.txt";

    private final static Scanner in = new Scanner(System.in);
    private final static Random random = new Random();

    public static void main(String[] args) {
        System.out.print("Type exercise in the format like [chapterNumber].[exerciseNumber]: ");
        String exercise = in.nextLine();
        App app = new App();
        try {
            app.getClass().getDeclaredMethod("ex" + exercise.replace(".", "")).invoke(app);
        } catch (Exception e) {
            e.getCause().printStackTrace();
//            ex();
        }
    }

    private static boolean done = false;

    public static void ex10x2() {
        Runnable hellos = () -> {
            for (int i = 1; i <= 1000; i++)
                System.out.println("Hello " + i);
            done = true;
        };
        Runnable goodbye = () -> {
            int i = 1;
            while (!done) i++;
            System.out.println("Goodbye " + i);
        };
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbye);
    }

    public static void ex10x1() {
        System.out.println("Имеем " + Runtime.getRuntime().availableProcessors() + " процессоров");

        Runnable hellos = () -> {
            for (int i = 1; i <= 1000; i++)
                System.out.println("Hello " + i);
        };
        Runnable goodbyes = () -> {
            for (int i = 1; i <= 1000; i++)
                System.out.println("Goodbye " + i);
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbyes);
        executor.shutdown();
    }

    public static void ex910() {
        System.out.print("Type string with integers: ");
        String string = in.nextLine();

        List<Integer> numbers = getNumbersListFromStringUsingSplit(string);
        System.out.println("Using split:");
        numbers.forEach(System.out::println);

        numbers = getNumbersListFromStringUsingFind(string);
        System.out.println("Using find:");
        numbers.forEach(System.out::println);
    }

    private static List<Integer> getNumbersListFromStringUsingSplit(String string) {
        return java.util.Arrays
            .stream(string.split("[^-0-9]"))
            .filter(s -> !s.isBlank())
            .map(Integer::valueOf)
            .toList();
    }

    private static ArrayList<Integer> getNumbersListFromStringUsingFind(String string) {
        ArrayList<Integer> list = new ArrayList<>();

        Matcher matcher = Pattern.compile("-?\\d+").matcher(string);
        while (matcher.find()) {
            list.add(Integer.valueOf(matcher.group()));
        }

        return list;
    }

    public static void ex99() {
        try {
            URL url = new URL("https://www.google.com/");
            URLConnection connection = url.openConnection();
            connection.connect();
            try (InputStream in = connection.getInputStream()) {
                byte[] bytes = in.readAllBytes();
                System.out.println(new String(bytes, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void ex97() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(Files.readAllBytes(Path.of(ALICE_TEXT_FILEPATH)));
            byte[] bytes = messageDigest.digest();
            //This byte[] has bytes in decimal format;
            //Convert it to hexadecimal format
            System.out.println(getStringFromDecimalBytesArray(bytes));
        } catch (NoSuchAlgorithmException | IOException e) {
            System.out.println("ERROR, YO!");
        }
    }

    private static String getStringFromDecimalBytesArray(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static void ex94() {
        Path wapPath = Path.of(WAP_TEXT_FILEPATH);

        long m = System.currentTimeMillis();
        try (Scanner in = new Scanner(Files.newInputStream(wapPath))) {
            int linesCount = 0;
            while (in.hasNextLine()) {
                linesCount++;
                in.nextLine();
            }
            System.out.println("Lines count: " + linesCount);
        } catch (IOException e) {
            System.out.println("STREAMS ERROR: " + e.getMessage());
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);

        m = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(Files.newInputStream(wapPath))
        )) {
            int linesCount = 0;
            while (reader.readLine() != null) {
                linesCount++;
            }
            System.out.println("Lines count: " + linesCount);
        } catch (IOException e) {
            System.out.println("STREAMS ERROR: " + e.getMessage());
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);

        m = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(Files.newInputStream(wapPath))
        )) {
            System.out.println("Lines count: " + reader.lines().count());
        } catch (IOException e) {
            System.out.println("STREAMS ERROR: " + e.getMessage());
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    public static void ex92() {
        System.out.print("Type filepath: ");
        saveTextFileStats(in.nextLine());
    }

    private static void saveTextFileStats(String filepath) {
        Path source = Path.of(filepath);
        Path target = source.resolveSibling(
            Path.of(source.getFileName().toString().replaceFirst("\\..+$", "") + ".toc")
        );
        try {
            List<Word> words = new ArrayList<>();
            List<String> lines = Files.readAllLines(source, StandardCharsets.UTF_8);
            int currentLine = 0;
            while (currentLine < lines.size()) {

                for (String w : List.of(lines.get(currentLine).split("\\PL+"))) {
                    if (w.matches("[A-Za-zА-Яа-яЁё0-9]+")) {
                        words.add(new Word(currentLine, w));
                    }
                }
                currentLine++;
            }

            Map<String, Set<Integer>> wordsList = words
                .stream()
                .collect(Collectors.groupingBy(Word::content, Collectors.mapping(Word::number, Collectors.toSet())));

            Files.writeString(target, wordsList.entrySet().stream()
                .reduce(
                    "",
                    (total, entry) -> total + "\n" + entry.getKey() + " ------ " + entry.getValue().toString(),
                    (total1, total2) -> total1 + total2
                )
            );
        } catch (IOException e) {
            System.out.println("STREAMS ERROR: " + e.getMessage());
        }
    }

    public static void ex91() {
        try (
            InputStream in = Files.newInputStream(Path.of(ALICE_TEXT_FILEPATH));
            OutputStream out = Files.newOutputStream(Path.of("/home/klimandr/test_file_copy"))
        ) {
            copyStreams(in, out);
        } catch (IOException e) {
            System.out.println("STREAMS ERROR: " + e.getMessage());
        }
    }

    private static void copyStreams(InputStream in, OutputStream out) throws IOException {
        in.transferTo(out);
    }

    public static void ex817() {
        long m = System.currentTimeMillis();

        List<String> longestWords = getFileWords(WAP_TEXT_FILEPATH)
            .stream()
//            .parallel()
            .sorted(Comparator.comparingInt(String::length))
            .limit(500)
            .toList();

        System.out.println("Найдено слов: " + longestWords.size());
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    public static void ex816() {
        Stream<BigInteger> integers = Stream
            .iterate(new BigInteger("1" + "0".repeat(49)), n -> n.add(BigInteger.ONE));
        long m = System.currentTimeMillis();
        List<BigInteger> list = integers
//            .parallel()
            .filter(i -> i.isProbablePrime(100))
            .limit(50)
            .toList();
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    public static <T> ArrayList<T> join(Stream<ArrayList<T>> stream) {
        return stream.reduce(new ArrayList<T>(), App::joinLists, App::joinLists);
    }

    public static <T> ArrayList<T> joinLists(ArrayList<T> first, ArrayList<T> second) {
        first.addAll(second);
        return first;
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        return Streams.zip(first, second, Stream::of).flatMap(Function.identity());
    }

    private static <T> boolean isStreamFinite(Stream<T> stream) {
        return stream.spliterator().estimateSize() != Long.MAX_VALUE;
    }

    public static void ex811() {
        Optional<List<String>> longestWords = getFileWords(TEXT_FILEPATH)
            .stream()
            .collect(Collectors.groupingBy(String::length))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue);
        System.out.println(longestWords.orElse(new ArrayList<>()));
    }

    public static void ex810() {
        OptionalDouble length = getFileWords(TEXT_FILEPATH).stream().mapToInt(String::length).average();
        System.out.println("Средняя длина строки: " + length.orElse(0));
    }

    public static void ex87() {
        String filepath = ALICE_TEXT_FILEPATH;

        System.out.println("The real ten words:");
        getFileWords(filepath).stream().filter(App::isRightWord).limit(10).forEach(System.out::println);

        System.out.println("The most frequent ten words:");
        getTextMap(filepath).entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
            .limit(10)
            .forEach(System.out::println);
    }

    public static void ex86() {
        System.out.print("Type a word: ");
        String string = in.next();
        System.out.println("It's " + (isRightWord(string) ? "" : "not ") + "a word");
    }

    private static boolean isRightWord(String string) {
        return string.codePoints().allMatch(Character::isAlphabetic);
    }

    public static Stream<String> codePoints(String s) {
        return s.codePoints().mapToObj(cp -> new String(new int [] { cp }, 0, 1));
    }

    private static void ex84() {
        System.out.print("Type x0 (integer): ");
        int x0 = in.nextInt();
        Stream<Long> stream = getRandomStream(x0, 25214903917L, 11, (long) Math.pow(2, 48));
        stream.limit(10).forEach(System.out::println);
    }

    private static Stream<Long> getRandomStream(long xn, long a, long c, long m) {
        return Stream.iterate(xn, n -> (a * n + c) % m);
    }

    private static void ex81() throws IOException {
        long m = System.currentTimeMillis();

        List<String> words = getFileWords(ALICE_TEXT_FILEPATH);

        long count = words
//            .parallelStream()
            .stream()
            .filter(w -> {
                boolean isLong = w.length() > 12;
                System.out.println("Processing " + w + (isLong ? " ++++++++++++++++" : ""));
                return isLong;
            })
            .limit(5)
            .count();
        System.out.println(count);

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    private static void ex8x2() throws IOException {
        long m = System.currentTimeMillis();

//        AtomicLong counter = new AtomicLong(0);
//        counter.
        List<String> words = getFileWords(ALICE_TEXT_FILEPATH);
        System.out.println(words.size());

        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("largest: " + largest.orElse(""));

        boolean aWordStartsWithQ = words.stream().anyMatch(s -> s.startsWith("Q"));
        System.out.println("aWordStartsWithQ: " + aWordStartsWithQ);

        Optional<String> startsWithQ = words.stream().parallel().filter(s -> s.startsWith("Q")).findAny();
        System.out.println("startsWithQ: " + startsWithQ.orElse("(None)"));
        // Run the program again to see if it finds a different word

        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    private static List<String> getFileWords(String filepath) {
        String contents = null;
        try {
            contents = Files.readString(Path.of(filepath));
        } catch (IOException e) {
            System.out.println("File reading error");
        }
        return List.of(contents.split("\\PL+"));
    }

    private static void ex8x1() {
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE)).limit(60000000L);
        //производительность упирается в конкуренцию потоков за ограниченный ресурс в виде этого итератора
        //теперь можно оценить производительность потоков отдельно от формирования списка чисел
        Stream<BigInteger> storedIntegers = integers.toList()
//            .stream()
            .parallelStream()
        ;
        long m = System.currentTimeMillis();
        Map<Integer, Long> map = storedIntegers
            .filter(i -> i.compareTo(BigInteger.valueOf(49999999L)) > 0)
            .collect(
//                Collectors.groupingByConcurrent(BigInteger::bitLength,
                Collectors.groupingBy(BigInteger::bitLength,
                Collectors.counting())
            );
        map.forEach((i, l) -> System.out.println(i + ": " + l));
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - m) / 1000.0);
    }

    private static void ex710() {
        Map<String, City> graph = generateCitiesGraph();
        String startCity = "Питер";
        City currentNode = graph.getOrDefault(startCity, City.empty());
        currentNode.setDistance(0);
        while (!currentNode.isEmpty()) {
            PriorityQueue<Neighbor> neighbors = currentNode.neighbors();
            while (!neighbors.isEmpty()) {
                Neighbor currentNeighbor = neighbors.poll();
                City neighborCity = graph.getOrDefault(currentNeighbor.name(), City.empty());
                if (neighborCity.isEmpty()) {
                    System.out.println("Граф несвязный!");
                    return;
                }
                if (neighborCity.isChecked()) {
                    continue;
                }
                int distance = currentNode.getDistance() + currentNeighbor.distance();
                System.out.println(currentNode.getName() + " - " + currentNeighbor.name());
                System.out.println("distance " + distance);
                System.out.println("currentDistance " + neighborCity.getDistance());
                if (distance < neighborCity.getDistance()) {
                    neighborCity.setDistance(distance);
                }
            }
            currentNode.setChecked();
            neighbors = currentNode.neighbors();
            currentNode = City.empty();
            while (!neighbors.isEmpty() && currentNode.isEmpty()) {
                Neighbor currentNeighbor = neighbors.poll();
                City neighborCity = graph.getOrDefault(currentNeighbor.name(), City.empty());
                if (!neighborCity.isChecked()) {
                    currentNode = neighborCity;
                }
            }
        }
        for (City city : graph.values()) {
            System.out.println(city.getName() + ": " + city.getDistance());
        }
    }

    private static Map<String, City> generateCitiesGraph() {
        Map<String, City> graph = new HashMap<>();
        List<String> cities = java.util.Arrays.asList("Москва", "Казань", "Нижний", "Саранск", "Питер", "Рязань");
        for (String city : cities) {
            PriorityQueue<Neighbor> neighbors = new PriorityQueue<>();
            switch (city) {
                case "Москва" -> {
                    neighbors.add(new Neighbor("Нижний", 350));
                    neighbors.add(new Neighbor("Питер", 750));
                    neighbors.add(new Neighbor("Рязань", 200));
                }
                case "Рязань" -> {
                    neighbors.add(new Neighbor("Москва", 200));
                    neighbors.add(new Neighbor("Саранск", 450));
                }
                case "Казань" -> {
                    neighbors.add(new Neighbor("Нижний", 350));
                }
                case "Нижний" -> {
                    neighbors.add(new Neighbor("Казань", 350));
                    neighbors.add(new Neighbor("Москва", 350));
                    neighbors.add(new Neighbor("Саранск", 290));
                }
                case "Саранск" -> {
                    neighbors.add(new Neighbor("Нижний", 290));
                    neighbors.add(new Neighbor("Рязань", 450));
                }
                case "Питер" -> {
                    neighbors.add(new Neighbor("Москва", 750));
                }
            }
            graph.put(city, new City(city, neighbors));
        }

        return graph;
    }

    private static void ex77() {
        getTextMap(TEXT_FILEPATH).forEach((String word, Integer count) -> System.out.println(word + "  ---  " + count));
    }

    private static Map<String, Integer> getTextMap(String filepath) {
        Map<String, Integer> map = new TreeMap<>();
        try (
                FileReader reader = new FileReader(filepath);
                Scanner scanner = new Scanner(reader);
        ) {
            while (scanner.hasNext()) {
                String word = scanner.next().trim().replaceAll("[^A-Za-zА-Яа-яЁё0-9]", "").toLowerCase();
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
        }

        return map;
    }

    private static void ex72() {
        ArrayList<String> strings = generateStrings();
        printStrings(strings);
        System.out.println("----------------------------");
        processStrings(strings);
        printStrings(strings);
    }

    private static ArrayList<String> generateStrings() {
        int size = 0;
        while (size < 2) {
            size = random.nextInt() % 10;
        }
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final int index = i == 0 ? i : (size % i) + 1;
            switch (i % 3) {
                case 0 -> strings.add("Строка Первого Типа " + index);
                case 1 -> strings.add("строка ВТОРОГО типа " + index);
                default -> strings.add("СтрокА ТретьегО ТипА " + index);
            }
        }
        return strings;
    }

    private static void printStrings(ArrayList<String> strings) {
        for (String str : strings) {
            System.out.println(str);
        }
    }

    private static void processStrings(ArrayList<String> strings) {
//        for (int i = 0; i < strings.size(); i++) {
//            strings.set(i, processString(strings.get(i)));
//        }
        strings.replaceAll(App::processString);
    }

    private static String processString(String str) {
        return str.toUpperCase();
    }

    private static void ex71() {
        System.out.print("Type integer: ");
        int number = in.nextInt();
        SimpleNumberSet set = new SimpleNumberSet(number, true);
        if (set.size() < 300) {
            System.out.println(set);
        }
        System.out.println("Размер множества: " + set.size());
    }

    private static void ex622() {
        try {
            Exceptions.doWork(() -> new FileReader("/abracadabra").ready() ? "adf" : "sdfs", ClassCastException::new);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getCause().getMessage());
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
