package booktasks.classes;

import java.util.PriorityQueue;

public class City implements Comparable<City> {
    private final String name;
    private final PriorityQueue<Neighbor> neighbors;
    private int distance;
    private boolean isChecked;

    public static City empty() {
        return new City("");
    }

    public City(String name) {
        this(name, new PriorityQueue<>());
    }

    public City(String name, PriorityQueue<Neighbor> neighbors) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.neighbors = neighbors;
    }

    public boolean isEmpty() {
        return name.isEmpty();
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(City c) {
        return this.getName().compareTo(c.getName());
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked() {
        isChecked = true;
    }

    public PriorityQueue<Neighbor> neighbors() {
        return new PriorityQueue<Neighbor>(neighbors);
    }
}
