package bit.bitset;

public class BitSetTest {

    public static void main(String[] args) throws Exception {
        BitSet bs = new BitSet();

        bs.add(1);
        bs.add(3);
        bs.add(63);
        bs.add(64);
        bs.add(65);

        System.out.println(bs.contains(1));
        System.out.println(bs.contains(2));
        System.out.println(bs.contains(3));
        System.out.println(bs.contains(62));
        System.out.println(bs.contains(63));
        System.out.println(bs.contains(64));
        System.out.println(bs.contains(65));
        System.out.println(bs.contains(66));
    }

}
