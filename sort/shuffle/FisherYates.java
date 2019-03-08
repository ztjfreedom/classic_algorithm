package sort.shuffle;

import java.util.*;

public class FisherYates {

    public static Integer[] shuffle(Integer[] array) {
        if (array == null) return null;

        List<Integer> inputList = new LinkedList<>(Arrays.asList(array));
        List<Integer> outputList = new LinkedList<>();
        Random random = new Random();

        while (inputList.size() > 0) {
            int randIx = random.nextInt(inputList.size());
            outputList.add(inputList.get(randIx));
            inputList.remove(randIx);
        }

        return outputList.toArray(new Integer[0]);
    }

}
