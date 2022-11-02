package booktasks.classes;

public record Neighbor(String name, int distance) implements Comparable<Neighbor> {
    @Override
    public int compareTo(Neighbor n) {
        return Integer.compare(this.distance, n.distance);
    }
}
