package tree.segmenttree;

public class AutoSegmentTreeNode {
    public int start;
    public int end;  // [start, end] closed interval
    public int status;  // 0: full clean, 1: full stored, 2: partially stored
    public AutoSegmentTreeNode left;
    public AutoSegmentTreeNode right;

    public AutoSegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.status = 0;
        this.left = null;
        this.right = null;
    }
}
