package booktasks.classes;

import java.util.Arrays;
import java.util.function.IntFunction;

public class ArrayStack<T> {
    private T[] list;

    public ArrayStack(IntFunction<T[]> constr) {
        list = constr.apply(0);
    }

    public void push(T element) {
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = element;
    }

    public T pop() throws Exception {
        int lastIndex = list.length - 1;
        if (lastIndex < 0) {
            throw new Exception("The stack is empty");
        }
        T lastElement = list[lastIndex];
        list = Arrays.copyOf(list, lastIndex);
        return lastElement;
    }

    public boolean isEmpty() {
        return list.length < 1;
    }
}
