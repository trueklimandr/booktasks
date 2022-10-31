package booktasks.classes;

import java.util.ArrayList;

public class Table<K, V> {
    private final ArrayList<Entry<K, V>> table = new ArrayList<>();

    private static class Entry<K, V> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public V get(K key) throws Exception {
        for (Entry<K, V> row : table) {
            if (key.equals(row.getKey())) {
                return row.getValue();
            }
        }
        throw new Exception("There is no such key");
    }

    public void set(K key, V value) {
        if (isSet(key)) remove(key);
        table.add(new Entry<>(key, value));
    }

    private boolean isSet(K key) {
        try {
            get(key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void remove(K key) {
        table.removeIf(row -> key.equals(row.getKey()));
    }
}
