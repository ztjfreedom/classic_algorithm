package bit.bitset;

public class BitSet {
    private long maxNum;
    private long[] bitValueArray;  // 1 long value contains 64 bits, using them to store 64 values
    private long[] shiftArray;     // help to set specific bit to 1

    public BitSet() {
        this.maxNum = (long) Integer.MAX_VALUE + 1;
        this.bitValueArray = new long[(int) (this.maxNum / 64)];

        this.shiftArray = new long[64];
        long shiftValue = 1;
        for (int i=0; i<shiftArray.length; i++) {
            this.shiftArray[i] = shiftValue;
            shiftValue *= 2;      // not using (long) Math.pow(2,i), since (long) Math(2,63) equals 2^63-1, not 2^63
        }
    }

    public void add(int value) throws Exception {
        if (value < 0) throw new Exception("Input must be not less than 0");
        this.bitValueArray[value / 64] |= this.shiftArray[value % 64];
    }

    public boolean contains(int value) throws Exception {
        if (value < 0) throw new Exception("Input must be not less than 0");
        return (this.bitValueArray[value / 64] & this.shiftArray[value % 64]) != 0;
    }
}
