package list.unrolledlinkedlist;

public class UnrolledLinkedListTest {

    public static void main(String[] args) {
        int[] array = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
        UnrolledLinkedList<Integer> unrolled = new UnrolledLinkedList<>(4);
        for (int num : array) {
            unrolled.insert(num);
            unrolled.print();
        }

        for (int i=0; i<array.length; i++) {
            System.out.print(unrolled.get(i) + " ");
        }
        System.out.println();
        System.out.println();
        unrolled.insert(0, 10);
        unrolled.insert(0, 11);
        unrolled.insert(0, 12);
        unrolled.insert(0, 13);
        unrolled.print();
        unrolled.insert(0, 14);
        unrolled.print();
        unrolled.insert(9, 20);
        unrolled.insert(10, 21);
        unrolled.insert(11, 22);
        unrolled.insert(12, 23);
        unrolled.print();
    }

}
