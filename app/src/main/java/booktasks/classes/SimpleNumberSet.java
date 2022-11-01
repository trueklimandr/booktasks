package booktasks.classes;

import java.util.BitSet;
import java.util.HashSet;

public class SimpleNumberSet {
    private final HashSet<Integer> hashSet;
    private final BitSet bitSet;
    private final int maxNumber;
    private final boolean useBitSet;

    private final int FIRST_NUMBER = 2;

    public SimpleNumberSet(int maxNumber) {
        this(maxNumber, false);
    }

    public SimpleNumberSet(int maxNumber, boolean useBitSet) {
        this.useBitSet = useBitSet;
        this.maxNumber = maxNumber;
        int size = maxNumber - 1;
        hashSet = new HashSet<>(size);
        bitSet = new BitSet(size);
        if (useBitSet) {
            initBitSet();
        } else {
            initHashSet();
        }
    }

    private void initBitSet() {
        fillBitSet();
        filterBitSet();
    }

    private void fillBitSet() {
        bitSet.set(FIRST_NUMBER, maxNumber);
    }

    private void filterBitSet() {
        int current = FIRST_NUMBER;
        while (current < maxNumber) {
            int i = 2;
            while ((current * i) <= maxNumber) {
                bitSet.set(current * i, false);
                i++;
            }
            int candidate = current + 1;
            while (true) {
                if (bitSet.get(candidate) || candidate > maxNumber) {
                    current = candidate;
                    break;
                }
                candidate++;
            }
        }
    }

    private void initHashSet() {
        fillHashSet();
        filterHashSet();
    }

    private void fillHashSet() {
        for (int i = FIRST_NUMBER; i <= maxNumber; i++) {
            hashSet.add(i);
        }
    }

    private void filterHashSet() {
        int current = FIRST_NUMBER;
        while (current < maxNumber) {
            int i = 2;
            while ((current * i) <= maxNumber) {
                hashSet.remove(current * i);
                i++;
            }
            int candidate = current + 1;
            while (true) {
                if (hashSet.contains(candidate) || candidate > maxNumber) {
                    current = candidate;
                    break;
                }
                candidate++;
            }
        }
    }

    public String toString() {
        return useBitSet ? bitSet.toString() : hashSet.toString();
    }

    public int size() {
        return useBitSet ? bitSet.cardinality() : hashSet.size();
    }
}
