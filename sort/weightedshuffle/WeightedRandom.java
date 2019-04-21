package sort.weightedshuffle;

import java.util.Random;

public class WeightedRandom {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4};
        int[] freq = new int[] {15, 15, 15, 55};

        for (int i = 0; i < 20; i++) {
            System.out.println(rand(arr, freq, arr.length));
        }
    }

    public static int rand(int[] arr, int[] freq, int n) {
        // Create and fill prefix array
        int[] prefix = new int[n];
        prefix[0] = freq[0];
        for (int i=1; i<n; i++)
            prefix[i] = prefix[i - 1] + freq[i];

        // prefix[n-1] is sum of all frequencies.
        // Generate a random number from 0 to this sum
        Random random = new Random();
        int r = random.nextInt(prefix[n - 1]);

        // Find index of ceiling of r in prefix array
        int index = findCeil(prefix, r, 0, n - 1);
        return arr[index];
    }

    public static int findCeil(int[] prefix, int randomVal, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (randomVal <= prefix[mid]) {
                high = mid;
            } else if (randomVal > prefix[mid]) {
                low = mid + 1;
            }
        }
        return high;
    }

}
