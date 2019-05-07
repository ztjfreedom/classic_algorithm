package math.gcd;

/**
 * greatest common divisor
 */
public class GCD {

    public static void main(String[] args) throws Exception {
        long[] array = new long[] {100, 20, 80, 50};
        System.out.println(getGCD(array));
    }

    public static long getGCD(long[] array) throws Exception {
        if (array == null || array.length == 0) throw new Exception();

        long gcd = array[0];
        for (int i=1; i<array.length; i++) {
            gcd = getGCD(gcd, array[i]);
        }
        return gcd;
    }

    public static long getGCD(long a, long b) {
        while (b % a != 0) {
            long temp = b % a;
            b = a;
            a = temp;
        }
        return a;
    }

}
