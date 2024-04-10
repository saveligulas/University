package org.example.coll;

import java.math.BigInteger;

public class MyBigIntegerCollection extends MyCollection<BigInteger> {
    public int getIndexWhenNumberLarger(int number) {
        for (int i = 0; i < _pointer; i++) {
            if (get(i).intValue() == number) {
                return i;
            }
        }
        return size() - 1;
    }

    public BigInteger[] toArray() {
        return super.toArray(BigInteger.class);
    }


    public MyCollection<BigInteger> subList(int start, int end) {
        return super.subList(start, end);
    }

}
