package graph.floyd;

// implementation of floyd algorithm using adjacency matrix
public class Floyd1 {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        print(matrix);
        floyd(matrix);

        int[][] matrix2 = {
                {   0,   2, INF, 100},
                {   2,   0,   3, INF},
                { INF,   3,   0, 4},
                { 100, INF,   4, 0}};
        print(matrix2);
        floyd(matrix2);
    }

    public static void floyd(int[][] matrix) {
        int nodeNum = matrix.length;
        int[][] path = new int[nodeNum][nodeNum];

        // init path array
        for (int i = 0; i < nodeNum; i++) {
            for (int j = 0; j < nodeNum; j++) {
                path[i][j] = j;  // at the beginning, shortest path from node i to node j is through node j
            }
        }

        // update shortest distance and path
        for (int k = 0; k < nodeNum; k++) {
            for (int i = 0; i < nodeNum; i++) {
                for (int j = 0; j < nodeNum; j++) {
                    // matrix[i][k]==INF || matrix[k][j]==INF means can not reach
                    // otherwise, get the distance of node i going through node k to node j
                    int tmp = (matrix[i][k]==INF || matrix[k][j]==INF) ? INF : (matrix[i][k] + matrix[k][j]);
                    if (matrix[i][j] > tmp) {
                        // update distance
                        matrix[i][j] = tmp;
                        // update path
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        print(matrix);  // print distance matrix
        print(path);    // print path matrix (read from up to down)
    }

    // print adjacency matrix
    public static void print(int[][] matrix) {
        int nodeNum = matrix.length;
        for (int i = 0; i < nodeNum; i++) {
            for (int j = 0; j < nodeNum; j++)
                System.out.printf("%10d ", matrix[i][j]);
            System.out.println();
        }
        System.out.println();
    }

}
