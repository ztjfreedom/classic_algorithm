package sort.quickselect;

public class QuickSelect {

    public static void main(String[] args) throws Exception {
        int[] array = new int[] {8,10,4,3,9,5,1,6,7,2};
        System.out.println(quickSelect(array, 1));
        System.out.println(quickSelect(array, 2));
        System.out.println(quickSelect(array, 3));
        System.out.println(quickSelect(array, 8));
        System.out.println(quickSelect(array, 9));
        System.out.println(quickSelect(array, 10));
    }

    public static int quickSelect(int[] array, int k) throws Exception {
        if (array == null || array.length == 0 || k <= 0 || k > array.length) throw new Exception("Invalid Parameter");
        return quickSelect(array, k, 0, array.length - 1);
    }

    public static int quickSelect(int[] array, int k, int low, int high) {
        int index = partition(array, low, high);
        if (index == k - 1) {
            return array[index];
        } else if (index < k - 1) {
            return quickSelect(array, k, index + 1, high);
        } else {
            return quickSelect(array, k, low, index - 1);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int base = array[low];
        while (low < high) {
            while (low < high && array[high] >= base) high --;
            array[low] = array[high];
            while (low < high && array[low] <= base) low ++;
            array[high] = array[low];
        }
        array[low] = base;
        return low;
    }

}
