package sort.shuffle;

import java.util.*;

public class KnuthDurstenfeld {

    public static void shuffle(Integer[] array) {
        if (array == null) return;

        Random random = new Random();
        for (int i=array.length; i>1; i--) {
            int randIx = random.nextInt(i);
            swap(array, randIx, i - 1);
        }
    }

    public static void swap(Integer[] array, int ix1, int ix2) {
        int temp = array[ix1];
        array[ix1] = array[ix2];
        array[ix2] = temp;
    }

}
