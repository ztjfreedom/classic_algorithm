package graph.unionfind;


import java.util.Scanner;

public class QuickFind {

    // p and q are connected iff they have the same id
    private int[] id;

    // O(N)
    public QuickFind(int N) {
        id = new int[N];
        for (int i=0; i<N; i++) { id[i] = i; }
    }

    // change all entries whose id equals id[p] to id[q]
    // O(N)
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i=0; i<id.length; i++) {
            if (id[i] == pid) { id[i] = qid; }
        }
    }

    // check if p and q have the same id
    // O(1)
    public boolean connected(int p, int q) { return id[p] == id[q]; }

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        QuickFind uf = new QuickFind(N);
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
