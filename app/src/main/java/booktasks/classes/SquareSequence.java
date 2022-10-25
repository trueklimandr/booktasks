package booktasks.classes;

import booktasks.interfaces.Sequence;

import java.math.BigInteger;

public class SquareSequence implements Sequence<BigInteger> {
    private BigInteger i = BigInteger.valueOf(0);

    public boolean hasNext() {
        return true;
    }

    public BigInteger next() {
        i = i.add(BigInteger.valueOf(1));
        return i.multiply(i);
    }
}
