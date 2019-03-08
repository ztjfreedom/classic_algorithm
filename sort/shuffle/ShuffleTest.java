package sort.shuffle;

public class ShuffleTest {

    public static void main(String[] args) {
        Integer[] array = new Integer[] {1, 2, 3, 4, 5};
        for (int i=0; i<10; i++) {
            Integer[] result = FisherYates.shuffle(array);
            print(result);
        }
        for (int i=0; i<10; i++) {
            array = new Integer[] {1, 2, 3, 4, 5};
            KnuthDurstenfeld.shuffle(array);
            print(array);
        }
        for (int i=0; i<10; i++) {
            Integer[] result = InsideOut.shuffle(array);
            print(result);
        }
    }

    public static void print(Integer[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
