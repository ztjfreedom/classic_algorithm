package graph.unionfind;

import java.util.Scanner;

public class PathCompressionWeightedQuickUnion {

    // id[i] is the parent of i
    // sz[i] counts number of objects in the tree rooted at i
    private int[] id;
    private int[] sz;

    // O(N)
    public PathCompressionWeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i=0; i<N; i++) { id[i] = i; }
    }

    // root of i is id[id[...id[i]...]]
    // proposition: depth of any node is at most log(N)
    // lg*(N)
    private int root(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];  // make every other node in path point to its grandparent
            i = id[i];
        }
        return i;
    }

    // link root of smaller tree to root of larger tree
    // update sz[] to be the sum of numbers of two trees
    // lg*(N)
    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        if (sz[p] < sz[q]) {
            sz[rootq] += sz[rootp];
            id[rootp] = rootq;
        } else {
            sz[rootp] += sz[rootq];
            id[rootq] = rootp;
        }
    }

    // check if p and q have the same root
    // lg*(N)
    public boolean connected(int p, int q) { return root(p) == root(q); }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        PathCompressionWeightedQuickUnion uf = new PathCompressionWeightedQuickUnion(N);
        for (int i=0; i<M; i++) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
        }
    }
}
