package tree.segmenttree;

public class AutoSegmentTree {
    public AutoSegmentTreeNode root;

    public AutoSegmentTree(int start, int end) { this.root = new AutoSegmentTreeNode(start, end); }

    public void store(int start, int end) { store(this.root, start, end); }

    private void store(AutoSegmentTreeNode node, int start, int end) {
        if (node.start <= start && node.end >= end && node.status == 1) return;

        if (node.start == start && node.end == end){
            node.status = 1;
            node.left = null;
            node.right = null;
            return;
        }

        int mid = node.start + (node.end - node.start) / 2;
        if (node.status == 0) {
            node.left = new AutoSegmentTreeNode(node.start, mid);
            node.right = new AutoSegmentTreeNode(mid + 1, node.end);
        }
        if (start <= mid) store(node.left, start, Math.min(end, mid));
        if (end >= mid + 1) store(node.right, Math.max(start, mid + 1), end);
        node.status = 2;
    }

    public boolean isIntervalEmpty(int start, int end) { return isIntervalEmpty(this.root, start, end); }

    private boolean isIntervalEmpty(AutoSegmentTreeNode node, int start, int end) {
        if (node.status == 0) return true;
        if (node.status == 1) return false;

        int mid = node.start + (node.end - node.start) / 2;

        boolean result = true;
        if (start <= mid) {
            result = isIntervalEmpty(node.left, start, Math.min(mid, end));
        }
        if (end >= mid + 1) {
            result &= isIntervalEmpty(node.right, Math.max(start, mid + 1), end);
        }

        return result;
    }

}
