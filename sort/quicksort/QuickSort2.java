package sort.quicksort;

public class QuickSort2 {


    public static void main(String[] args) {
        int[] array = new int[] {10, 6, 4, 8, 1, 2, 5, 3, 7, 9};
        quickSort(array);
        printArray(array);

        array = new int[] {10, 9, 8, 7, 6, 5, 4 ,3 ,2};
        quickSort(array);
        printArray(array);

        array = new int[] {5, 4, 7, 8, 2, 9, 1, 10, 6, 3};
        quickSort(array);
        printArray(array);
    }

    public static void quickSort(int[] array) {
        partition(array, 0, array.length - 1);
    }

    public static void partition(int[] array, int start, int end) {
        if(start >= end) return;

        int base = array[start];
        int i = start;
        int j = end;
        while(i < j) {
            while (i < j && array[i] <= base) i ++;
            while (i < j && array[j] >= base) j --;
            if(i < j) swap(array, i, j);
        }

        if (array[i] > base) i --;
        swap(array, start, i);

        partition(array, start, i - 1);
        partition(array, i + 1, end);
    }

    public static void swap(int[] array, int ix1, int ix2) {
        int tmp = array[ix1];
        array[ix1] = array[ix2];
        array[ix2] = tmp;
    }

    public static void printArray(int[] array) {
        for (int n : array) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

}
