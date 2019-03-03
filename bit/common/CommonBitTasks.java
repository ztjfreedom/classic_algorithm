package bit.common;

public class CommonBitTasks {

    // Return true if bit i is 1, false if 0
    public static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    public static int clearBit(int num, int i) {
        int mask = ~ (1 << i);
        return num & mask;
    }

    // Clear all bits from the most significant bit through i (inclusive)
    public static int clearBitsMSBthroughI(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    // Clear all bits from i through 0 (inclusive)
    public static int clearBitsIthrough0(int num, int i) {
        int mask = -1 << (i + 1);
        return num & mask;
    }

    public static int updateBit(int num, int i, boolean is1) {
        int value = is1 ? 1 : 0;
        int mask = ~ (1 << i);
        return (num & mask) | (value << i);
    }

    public static void main(String[] args) {
        int a = 5;  // 0101
        System.out.println(getBit(a, 0));
        System.out.println(getBit(a, 1));
        System.out.println(getBit(a, 2));
        System.out.println(getBit(a, 3));
    }

}
