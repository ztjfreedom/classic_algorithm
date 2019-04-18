package tree.segmenttree;

public class AutoSegmentTreeTest {

    public static void main(String[] args) {
        AutoSegmentTree segmentTree = new AutoSegmentTree(0, 100);
        System.out.println(segmentTree.isIntervalEmpty(0, 100));
        segmentTree.store(1, 5);
        System.out.println(segmentTree.isIntervalEmpty(0, 2));
        System.out.println(segmentTree.isIntervalEmpty(6, 7));
        segmentTree.store(0, 10);
        System.out.println(segmentTree.isIntervalEmpty(0, 0));
        System.out.println(segmentTree.isIntervalEmpty(9, 10));
        System.out.println(segmentTree.isIntervalEmpty(15, 20));
    }

}
