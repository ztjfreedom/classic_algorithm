package graph.floyd;

/**
 * Java: Floyd算法获取最短路径(邻接矩阵)
 */

public class Floyd1 {

    private static final int INF = Integer.MAX_VALUE;   // 最大值

    /*
     * 打印矩阵队列图
     */
    public static void print(int[][] matrix) {
        int nodeNum = matrix.length;
        System.out.println("Matrix:");
        for (int i = 0; i < nodeNum; i++) {
            for (int j = 0; j < nodeNum; j++)
                System.out.printf("%10d ", matrix[i][j]);
            System.out.println();
        }
        System.out.println();
    }

    public static void floyd(int[][] matrix) {
        int nodeNum = matrix.length;
        int[][] path = new int[nodeNum][nodeNum];

        // 初始化
        for (int i = 0; i < nodeNum; i++) {
            for (int j = 0; j < nodeNum; j++) {
                path[i][j] = j;  // "顶点i"到"顶点j"的最短路径是经过顶点j。
            }
        }

        // 计算最短路径
        for (int k = 0; k < nodeNum; k++) {
            for (int i = 0; i < nodeNum; i++) {
                for (int j = 0; j < nodeNum; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (matrix[i][k]==INF || matrix[k][j]==INF) ? INF : (matrix[i][k] + matrix[k][j]);
                    if (matrix[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        matrix[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        print(matrix);
        print(path);    // path的内容竖向阅读
    }

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

        int[][] matrix2 = {{   0,   2, INF, 100},
                           {   2,   0,   3, INF},
                           { INF,   3,   0, 4},
                           { 100, INF,   4, 0}};
        print(matrix2);
        floyd(matrix2);
    }
}
