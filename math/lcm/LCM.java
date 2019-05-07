package math.lcm;

import math.gcd.GCD;

/**
 * least common multiple
 */
public class LCM {

    public final static long OVERFLOW = -1;

    public static void main(String[] args) throws Exception {
        long[] array = new long[] {100, 20, 80, 50};
        System.out.println(getLCM(array, Long.MAX_VALUE));
    }

    public static long getLCM(long[] array) throws Exception {
        if (array == null || array.length == 0) throw new Exception();

        long lcm = array[0];
        for (int i=2; i<array.length; i++) {
            lcm = getLCM(lcm, array[i]);
        }
        return lcm;
    }

    public static long getLCM(long a, long b) {
        return a * b / GCD.getGCD(a, b);
    }

    /**
     * optimized: prevent overflow
     */
    public static long getLCM(long[] array, long max) throws Exception {
        if (array == null || array.length == 0) throw new Exception();

        long lcm = array[0];
        for (int i=2; i<array.length; i++) {
            lcm = getLCM(lcm, array[i], max);
            if (lcm == OVERFLOW) return OVERFLOW;
        }
        return lcm;
    }

    /**
     * optimized: prevent overflow
     */
    public static long getLCM(long a, long b, long max) {
        long quotient = b / GCD.getGCD(a, b);
        if (max / a < quotient) {
            return OVERFLOW;
        } else {
            return a * quotient;
        }
    }
}
