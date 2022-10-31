package booktasks.classes;

import java.util.ArrayList;

public class Stack<E> {
    private final ArrayList<E> list = new ArrayList<>();

    public void push(E element) {
        list.add(element);
    }

    public E pop() throws Exception {
        int lastIndex = list.size() - 1;
        if (lastIndex < 0) {
            throw new Exception("The stack is empty");
        }
        return list.remove(lastIndex);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
