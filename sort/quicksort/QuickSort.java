package sort.quicksort;

public class QuickSort {


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
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int index = partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int key = arr[start];
        while (start < end) {
            while (arr[end] >= key && end > start) end --;
            arr[start] = arr[end];
            while (arr[start] <= key && end > start) start ++;
            arr[end] = arr[start];
        }
        arr[start] = key;
        return start;
    }

    public static void printArray(int[] array) {
        for (int n : array) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

}
