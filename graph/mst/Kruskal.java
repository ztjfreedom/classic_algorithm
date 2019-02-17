package graph.mst;

import common.AdjacencyMatrix;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {

    public static void main(String[] args) {
        AdjacencyMatrix graph = generateMatrix();
        Kruskal k = new Kruskal();
        k.kruskal(graph);
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
     * Kruskal 克鲁斯卡尔 构建最小生成树，使用邻接矩阵
     * https://www.cnblogs.com/skywang12345/p/3711504.html
     * @param graph 邻接矩阵
     */
    private void kruskal(AdjacencyMatrix graph) {
        int index = 0;                      // res 数组的索引
        int[] vends = new int[graph.edgeNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
        AdjacencyMatrix.Edge[] res = new AdjacencyMatrix.Edge[graph.edgeNum];     // 结果数组，保存 kruskal 最小生成树的边

        // 获取"图中所有的边"
        AdjacencyMatrix.Edge[] edges = getEdges(graph);
        // 将边按照"权"的大小进行排序（从小到大）
        sortEdges(edges);

        for (int i = 0; i< graph.edgeNum; i++) {
            int p1 = graph.getPosition(edges[i].start);      // 获取第 i 条边的"起点"的序号
            int p2 = graph.getPosition(edges[i].end);        // 获取第 i 条边的"终点"的序号

            int m = getEnd(vends, p1);                 // 获取 p1 在"已有的最小生成树"中的终点
            int n = getEnd(vends, p2);                 // 获取 p2 在"已有的最小生成树"中的终点
            // 如果 m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
            if (m != n) {
                vends[m] = n;                       // 设置 m 在"已有的最小生成树"中的终点为 n
                res[index++] = edges[i];            // 保存结果
            }
        }

        // 统计并打印"kruskal最小生成树"的信息
        int weight = 0;
        for (int i = 0; i < index; i++)
            weight += res[i].weight;
        System.out.printf("Kruskal = %d : ", weight);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", res[i].start, res[i].end);
        System.out.println();
    }

    /*
     * 获取图中的边
     */
    private AdjacencyMatrix.Edge[] getEdges(AdjacencyMatrix graph) {
        int index = 0;
        AdjacencyMatrix.Edge[] edges = new AdjacencyMatrix.Edge[graph.edgeNum];

        for (int i = 0; i < graph.vertices.length; i++) {
            for (int j = i+1; j < graph.vertices.length; j++) {
                if (graph.matrix[i][j]!=INF) {
                    edges[index++] = graph.new Edge(graph.vertices[i], graph.vertices[j], graph.matrix[i][j]);
                }
            }
        }

        return edges;
    }

    /*
     * 对边按照权值大小进行排序（由小到大）
     */
    private void sortEdges(AdjacencyMatrix.Edge[] edges) {
        Arrays.sort(edges, Comparator.comparing(AdjacencyMatrix.Edge::getWeight));
    }

    /*
     * 获取 i 的终点
     */
    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

}
