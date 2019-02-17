package sort.mergesort;

public class MergeSort {

        public static void main(String[] args) {
            int[] array = new int[]{10, 6, 4, 8, 1, 2, 5, 3, 7, 9};
            mergeSort(array, 0, array.length - 1);
            printArray(array);
            array = new int[] {10, 9, 8, 7, 6, 5, 4 ,3 ,2};
            mergeSort(array, 0, array.length - 1);
            printArray(array);
            array = new int[] {5, 4, 7, 8, 2, 9, 1, 10, 6, 3};
            mergeSort(array, 0, array.length - 1);
            printArray(array);
        }

        public static void mergeSort(int[] array, int left, int right) {
            if (left >= right) return;

            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, right, mid);
        }

        public static void merge(int[] array, int left, int right, int mid) {
            int[] temp = new int[right - left + 1];
            int ix = 0;
            int ix1 = left;
            int ix2 = mid + 1;
            while (ix1 <= mid && ix2 <= right) {
                if (array[ix1] <= array[ix2]) temp[ix++] = array[ix1++];
                else temp[ix++] = array[ix2++];
            }

            while(ix1 <= mid) temp[ix++] = array[ix1++];

            while(ix2 <= right) temp[ix++] = array[ix2++];

            for (int i=left; i<=right; i++) {
                array[i] = temp[i - left];
            }
        }

    public static void printArray(int[] array) {
        for (int n : array) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
