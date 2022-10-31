package booktasks.classes;

import java.util.ArrayList;

public class Arrays {
    public static <E extends Comparable<E>> E min(E[] elements) {
        E min = elements[0];
        for (E element : elements) {
            if (min.compareTo(element) > 0) {
                min = element;
            }
        }
        return min;
    }

    public static <E extends Comparable<E>> E max(E[] elements) {
        E max = elements[0];
        for (E element : elements) {
            if (max.compareTo(element) < 0) {
                max = element;
            }
        }
        return max;
    }

    public static <E extends Comparable<E>> Pair<E> minMax(E[] elements) {
        return new Pair<>(min(elements), max(elements));
    }
}
