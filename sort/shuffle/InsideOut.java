package sort.shuffle;

import java.util.Arrays;
import java.util.Random;

public class InsideOut {

    public static Integer[] shuffle(Integer[] array) {
        if (array == null) return null;

        Random random = new Random();
        Integer[] result = Arrays.copyOf(array, array.length);
        for (int i=0; i<result.length; i++) {
            int randIx = random.nextInt(i + 1);
            result[i] = result[randIx];
            result[randIx] = array[i];
        }
        return result;
    }

}
