package booktasks.interfaces;

public interface IntSequence {
    static IntSequence generate(GenerateIntSequence generator) {
        return generator.generate();
    }

    static IntSequence of(int[] numbers) {
        return new IntSequence() {
            private int position = 0;
            private final int[] sequence = numbers;

            public boolean hasNext() {
                return sequence.length >= (position + 1);
            }

            public int next() {
                return sequence[position++];
            }
        };
    }

    boolean hasNext();
    int next();
}
