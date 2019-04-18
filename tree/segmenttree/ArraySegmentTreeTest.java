package tree.segmenttree;

public class ArraySegmentTreeTest {

    public static void main(String [] args){
        Integer nums[] = {1,2,3,4,-5,-9,10,-12,32};
        ArraySegmentTree<Integer> segmentTree = new ArraySegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0,1));
        System.out.println(segmentTree.query(0,4));
        System.out.println(segmentTree.query(2,6));
        System.out.println(segmentTree.query(3,8));
        System.out.println(segmentTree.query(7,8));
    }

}
