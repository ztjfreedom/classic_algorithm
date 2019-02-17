package graph.unionfind;

import java.util.Scanner;

public class QuickUnion {

    // id[i] is the parent of i
    private int[] id;

    // O(N)
    public QuickUnion(int N) {
        id = new int[N];
        for (int i=0; i<N; i++) { id[i] = i; }
    }

    // root of i is id[id[...id[i]...]]
    // O(N) worst case
    private int root(int i) {
        while (id[i] != i) { i = id[i]; }
        return i;
    }

    // change root of p point to root of q
    // O(N) worst case
    public void union(int p, int q) { id[root(p)] = root(q); }

    // check if p and q have the same root
    // O(N) worst case
    public boolean connected(int p, int q) { return root(p) == root(q); }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        QuickUnion uf = new QuickUnion(N);
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
