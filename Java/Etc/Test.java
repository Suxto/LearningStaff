package Etc;

import java.math.BigInteger;
import java.util.Random;

public class Test extends BigInteger {
    public Test(byte[] val, int off, int len) {
        super(val, off, len);
    }

    public Test(byte[] val) {
        super(val);
    }

    public Test(int signum, byte[] magnitude, int off, int len) {
        super(signum, magnitude, off, len);
    }

    public Test(int signum, byte[] magnitude) {
        super(signum, magnitude);
    }

    public Test(String val, int radix) {
        super(val, radix);
    }

    public Test(String val) {
        super(val);
    }

    public Test(int numBits, Random rnd) {
        super(numBits, rnd);
    }

    public Test(int bitLength, int certainty, Random rnd) {
        super(bitLength, certainty, rnd);
    }

    public static void main(String[] args) {
    }

}
