package tree.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort {

    public static void main(String[] args) {
        // test1
        Integer[] array = new Integer[] {1, 0, 10, 20, 3, 5, 6, 4, 9, 8, 12, 17, 34, 11};
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        print("Initial", list);
        buildMaxHeap(list);
        print("Build max heap", list);
        insert(list, 22);
        print("Insert 22", list);
        removeTop(list);
        print("Remove top", list);
        heapSort(list);
        print("Heap sort", list);

        // test2
        array = new Integer[] {5, 10, 3, 8, 12, 15, 20, 35, 40, 18, 6};
        list = new ArrayList<>(Arrays.asList(array));
        print("Initial", list);
        buildMaxHeap(list);
        print("Build max heap", list);
        insert(list, 22);
        print("Insert 22", list);
        removeTop(list);
        print("Remove top", list);
        heapSort(list);
        print("Heap sort", list);
    }

    private static void upToDownMaxAdjust(List<Integer> list, int effectiveSize, int ix) {
        int leftIx = getLeftChildIndex(ix);
        int rightIx = getRightChildIndex(ix);
        int maxIx = ix;
        if (leftIx < effectiveSize) {
            if (list.get(ix) < list.get(leftIx)) {
                maxIx = leftIx;
            }
            if (rightIx < effectiveSize && list.get(maxIx) < list.get(rightIx)) {
                maxIx = rightIx;
            }
            if (maxIx != ix) {
                swap(list, ix, maxIx);
                upToDownMaxAdjust(list, effectiveSize, maxIx);
            }
        }
    }

    private static void downToUpMaxAdjust(List<Integer> list, int ix) {
        int parentIx = getParentIndex(ix);
        if (parentIx >= 0 && list.get(ix) > list.get(parentIx)) {
            swap(list, ix, parentIx);
            downToUpMaxAdjust(list, parentIx);
        }
    }

    private static void buildMaxHeap(List<Integer> list) {
        int startIx = getParentIndex(list.size() - 1);
        for (int i=startIx; i>=0; i--) {
            upToDownMaxAdjust(list, list.size(), i);
        }
    }

    private static void heapSort(List<Integer> list) {
        int effectiveSize = list.size();
        for (int i=0; i<list.size(); i++) {
            swap(list, 0, effectiveSize - 1);
            effectiveSize --;
            upToDownMaxAdjust(list, effectiveSize, 0);
        }
    }

    private static void insert(List<Integer> list, int insertData) {
        list.add(insertData);
        downToUpMaxAdjust(list, list.size() - 1);
    }

    private static void removeTop(List<Integer> list) {
        swap(list, 0, list.size() - 1);
        list.remove(list.size() - 1);
        upToDownMaxAdjust(list, list.size(), 0);
    }

    private static int getParentIndex(int ix) {
        return (ix - 1) / 2;
    }

    private static int getLeftChildIndex(int ix) {
        return ix * 2 + 1;
    }

    private static int getRightChildIndex(int ix) {
        return ix * 2 + 2;
    }

    private static void swap(List<Integer> list, int ix1, int ix2) {
        int tmp = list.get(ix1);
        list.set(ix1, list.get(ix2));
        list.set(ix2, tmp);
    }

    private static void print(String msg, List<Integer> list) {
        int pre = -2;
        for (int i = 0; i < list.size(); i++) {
            if (pre < (int) getLog(i + 1)) {
                pre = (int) getLog(i + 1);
                System.out.println();
            }
            System.out.print(list.get(i) + "|");
        }
        System.out.println();
        System.out.println(msg);
    }

    private static double getLog(double param) {
        return Math.log(param) / Math.log(2);
    }

}
