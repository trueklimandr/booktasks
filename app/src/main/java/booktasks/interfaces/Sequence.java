package booktasks.interfaces;

public interface Sequence<T> {
    boolean hasNext();
    T next();
}
