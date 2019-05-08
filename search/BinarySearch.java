package search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = new int[] {0,0,2,2,2,5,5,7,7,9,9,11};
        for (int i=0; i<=11; i++) System.out.print(binarySearch(array, i) + " ");
        System.out.println();

        // result is not right, but no err occurs
        System.out.print(binarySearch(new int[] {}, 5) + " ");
        System.out.print(binarySearch(array, 15) + " ");
    }

    /**
     * search for the first element >= a given value in a given sorted array, return the index
     *
     * this is a bug free common implementation of binary search
     * it works even the interval is null, no answer, elements duplicate, closed/open interval
     * +/-1 only appears once, and finally left == right (low == high), so both are correct
     * key point is [left, right): left closed and right open
     *
     * [left,right), l<r, l=m+1, r=m, are a combination, must not change any of them
     */
    public static int binarySearch(int[] array, int value) {
        int l = 0;
        int r = array.length;  // [left, right)
        while (l < r) {  // must be '<', not '<='
            int m = l + (r - l) / 2;  // prevent overflow
            if (array[m] < value) {
                l = m + 1;  // only left + 1 once
            } else {
                r = m;  // must not - 1 here
            }
        }
        return l;  // both l and r are right
    }

}
