package tree.segmenttree;

public class ArraySegmentTree<E> {
    private E[] tree;
    private E data[];
    private ArraySegmentTreeMerger<E> merger;

    public ArraySegmentTree(E[] arr, ArraySegmentTreeMerger<E> merge) {
        this.merger = merge;
        this.data = (E[])new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
        this.tree = (E[])new Object[4*arr.length]; // in worst case we need 4*N space
        buildTree(0,0,data.length - 1);
    }

    private void buildTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        buildTree(leftTreeIndex, l, mid);
        buildTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public E get(int index) {
        return data[index];
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public E query(int queryL, int queryR) {
        return query(0, 0, data.length - 1, queryL, queryR);  // start from root
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) return tree[treeIndex];
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult,rightResult);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i <tree.length ; i++) {
            if (tree[i]!=null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            if (i!=tree.length-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

}
