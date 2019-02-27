package bit.bitset;

public class BitSetTest {

    public static void main(String[] args) throws Exception {
        BitSet bs = new BitSet();

        for (int i=0; i<10000; i++) {
            bs.add(i);
        }

        System.out.println(bs.contains(0));
        System.out.println(bs.contains(9));
        System.out.println(bs.contains(99));
        System.out.println(bs.contains(999));
        System.out.println(bs.contains(9999));
        System.out.println(bs.contains(10000));
    }

}
