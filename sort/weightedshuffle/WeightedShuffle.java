package sort.weightedshuffle;

import java.util.ArrayList;
import java.util.List;

public class WeightedShuffle {

    public static void main(String[] args) {
        WeightItem<String> item1 = new WeightItem<>("A", 5.0);
        WeightItem<String> item2 = new WeightItem<>("B", 90.0);
        WeightItem<String> item3 = new WeightItem<>("C", 5.0);
        List<WeightItem> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        for (int i=0; i<20; i++) {
            weightedShuffle(list);
            for (WeightItem item : list) {
                System.out.print(item.item + " ");
            }
            System.out.println();
        }
    }

    /**
     * As to a normal shuffle, we can think that the swap chance of each element is 50%.
     * If we change this chance, we can implement a weighted shuffle.
     * We use Collections.sort to implement.
     */
    public static void weightedShuffle(List<WeightItem> list) {
        /*
        Collections.sort(list, new Comparator<WeightItem>() {
            public int compare(WeightItem s1, WeightItem s2) {
                if (Math.random() * s1.weight > Math.random() * s2.weight) {
                    return -1;
                } else if (Math.random() * s1.weight == Math.random() * s2.weight) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
         */
       list.sort((s1, s2) -> Double.compare(Math.random() * s2.weight, Math.random() * s1.weight));
    }

}
