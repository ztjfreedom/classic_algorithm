package graph.floyd;

import common.AdjacencyList;

public class Floyd2 {

    public static void floyd(AdjacencyList graph) {

        int[][] path = new int[graph.mVexs.length][graph.mVexs.length];
        int[][] dist = new int[graph.mVexs.length][graph.mVexs.length];

        // 初始化
        for (int i = 0; i < graph.mVexs.length; i++) {
            for (int j = 0; j < graph.mVexs.length; j++) {
                dist[i][j] = graph.getWeight(i, j);  // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                path[i][j] = j;                      // "顶点i"到"顶点j"的最短路径是经过顶点j。
            }
        }

        // 计算最短路径
        for (int k = 0; k < graph.mVexs.length; k++) {
            for (int i = 0; i < graph.mVexs.length; i++) {
                for (int j = 0; j < graph.mVexs.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k]==AdjacencyList.INF || dist[k][j]==AdjacencyList.INF) ?
                            AdjacencyList.INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        // 打印 floyd 最短路径的结果
        System.out.printf("Floyd: \n");
        for (int i = 0; i < graph.mVexs.length; i++) {
            for (int j = 0; j < graph.mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
    }



    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        AdjacencyList.EData[] edges = {
                // 起点 终点 权
                new AdjacencyList.EData('A', 'B', 12),
                new AdjacencyList.EData('A', 'F', 16),
                new AdjacencyList.EData('A', 'G', 14),
                new AdjacencyList.EData('B', 'C', 10),
                new AdjacencyList.EData('B', 'F',  7),
                new AdjacencyList.EData('C', 'D',  3),
                new AdjacencyList.EData('C', 'E',  5),
                new AdjacencyList.EData('C', 'F',  6),
                new AdjacencyList.EData('D', 'E',  4),
                new AdjacencyList.EData('E', 'F',  2),
                new AdjacencyList.EData('E', 'G',  8),
                new AdjacencyList.EData('F', 'G',  9),
        };

        // 采用已有的"图"
        AdjacencyList graph = new AdjacencyList(vexs, edges);
        graph.print();   // 打印图

        // floyd 算法获取各个顶点之间的最短距离
        floyd(graph);
    }
}
