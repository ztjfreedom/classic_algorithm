package graph.mst;

import common.AdjacencyMatrix;

public class Prim {

    public static void main(String[] args) {
        AdjacencyMatrix graph = generateMatrix();
        Prim p = new Prim();
        p.prim(graph, 0);   // prim算法生成最小生成树
    }

    private static final int INF = Integer.MAX_VALUE;

    /**
     * 构造一个邻接矩阵
     * @return 邻接矩阵
     */
    private static AdjacencyMatrix generateMatrix() {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        // 采用已有的"图"
        return new AdjacencyMatrix(vertices, matrix);
    }

    /**
     * Prim 普里姆 构建最小生成树，使用邻接矩阵
     * https://www.cnblogs.com/skywang12345/p/3711510.html
     * @param start 从图中的第start个元素开始，生成最小树
     */
    private void prim(AdjacencyMatrix graph, int start) {
        int num = graph.vertices.length;         // 顶点个数
        int index = 0;                     // prim 最小树的索引，即 results 数组的索引
        char[] results  = new char[num];   // prim 最小树的结果数组
        int[] weights = new int[num];      // 顶点间边的权值

        // prim 最小生成树中第一个节点是"图中第 start 个节点"，因为是从 start 开始的。
        results[index++] = graph.vertices[start];

        // 初始化"节点的权值数组"，
        // 将每个节点的权值初始化为"第 start 个顶点"到"该顶点"的权值。
        System.arraycopy(graph.matrix[start], 0, weights, 0, num);

        // 将第 start 个节点的权值初始化为 0。
        // 可以理解为"第 start 个节点到它自身的距离为 0"。
        weights[start] = 0;

        for (int i = 0; i < num; i++) {
            // 由于从 start 开始的，因此不需要再对第 start 个节点进行处理。
            if(start == i)
                continue;

            int j = 0;
            int k = 0;
            int min = INF;
            // 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
            while (j < num) {
                // 若 weights[j] = 0 ，意味着"第 j 个节点已经被排序过"（或者说已经加入了最小生成树中）。
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }

            // 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
            // 将第 k 个顶点加入到最小生成树的结果数组中
            results[index++] = graph.vertices[k];
            // 将"第 k 个顶点的权值"标记为 0，意味着第 k 个顶点已经排序过了（或者说已经加入了最小树结果中）。
            weights[k] = 0;
            // 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
            for (j = 0 ; j < num; j++) {
                // 当第j个节点没有被处理，并且需要更新时才被更新。
                if (weights[j] != 0 && graph.matrix[k][j] < weights[j])
                    weights[j] = graph.matrix[k][j];
            }
        }

        // 计算最小生成树的权值
        int weight = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            // 获取 results[i] 在 matrix 中的位置
            int n = graph.getPosition(results[i]);
            // 在 vertices[0...i] 中，找出到 j 的权值最小的顶点。
            for (int j = 0; j < i; j++) {
                int m = graph.getPosition(results[j]);
                if (graph.matrix[m][n] < min)
                    min = graph.matrix[m][n];
            }
            weight += min;
        }

        // 打印最小生成树
        System.out.printf("Prim(%c) = %d : ", graph.vertices[start], weight);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", results[i]);
        System.out.println();
    }

}
