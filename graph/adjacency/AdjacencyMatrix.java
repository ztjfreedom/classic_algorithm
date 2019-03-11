package graph.adjacency;

/*
 * 无向图 Undirected Graph 邻接矩阵 Adjacency Matrix
 */
public class AdjacencyMatrix {

    public static void main(String[] args) {
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
        AdjacencyMatrix graph = new AdjacencyMatrix(vertices, matrix);

        graph.print();   // 打印图
        graph.dfs();     // 深度优先遍历
        graph.bfs();     // 广度优先遍历
    }

    // 边的结构体
    public class Edge {
        public char start; // 边的起点
        public char end;   // 边的终点
        public int weight; // 边的权重

        public int getWeight() {
            return weight;
        }

        public Edge(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public int edgeNum;        // 边的数量
    public char[] vertices;    // 顶点集合
    public int[][] matrix;     // 邻接矩阵

    private static final int INF = Integer.MAX_VALUE;   // 最大值

    /**
     * 创建图
     * @param vertices -- 顶点数组
     * @param matrix -- 邻接矩阵
     */
    public AdjacencyMatrix(char[] vertices, int[][] matrix) {
        // 初始化"顶点数"和"边数"
        int length = vertices.length;

        // 初始化"顶点"
        this.vertices = new char[length];
        System.arraycopy(vertices, 0, this.vertices, 0, length);

        // 初始化"边"
        this.matrix = new int[length][length];
        for (int i = 0; i < length; i++)
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, length);

        // 统计"边"
        for (int i = 0; i < length; i++)
            for (int j = i+1; j < length; j++)
                if (this.matrix[i][j] != INF)
                    this.edgeNum ++;
    }

    /*
     * 返回ch位置，不存在则返回 -1
     */
    public int getPosition(char ch) {
        for(int i = 0; i< vertices.length; i++)
            if (vertices[i] == ch)
                return i;
        return -1;
    }

    /*
     * 返回顶点v的第一个邻接顶点的索引，失败或不存在则返回 -1
     */
    private int firstVertex(int v) {
        if (v<0 || v>(vertices.length-1))
            return -1;

        for (int i = 0; i < vertices.length; i++)
            if (matrix[v][i]!=0 && matrix[v][i]!=INF)
                return i;

        return -1;
    }

    /*
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败或不存在则返回 -1
     */
    private int nextVertex(int v, int w) {
        if (v<0 || v>(vertices.length-1) || w<0 || w>(vertices.length-1))
            return -1;

        for (int i = w + 1; i < vertices.length; i++)
            if (matrix[v][i]!=0 && matrix[v][i]!=INF)
                return i;

        return -1;
    }

    /*
     * 深度优先搜索遍历图的递归实现
     */
    private void dfs(int i, boolean[] visited) {
        visited[i] = true;
        System.out.printf("%c ", vertices[i]);
        // 遍历该顶点的所有邻接顶点。若是没有访问过，那么继续往下走
        for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
            if (!visited[w])
                dfs(w, visited);
        }
    }

    /*
     * 深度优先搜索遍历图
     */
    private void dfs() {
        boolean[] visited = new boolean[vertices.length];  // 顶点访问标记
        System.out.print("dfs: ");
        for (int i = 0; i < vertices.length; i++) {
            if (!visited[i])
                dfs(i, visited);
        }
        System.out.println();
    }

    /*
     * 广度优先搜索（类似于树的层次遍历）
     */
    private void bfs() {
        int head = 0;
        int tail = 0;
        int[] queue = new int[vertices.length];            // 辅助队列
        boolean[] visited = new boolean[vertices.length];  // 顶点访问标记

        System.out.print("bfs: ");
        for (int i = 0; i < vertices.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", vertices[i]);
                queue[tail++] = i;  // 入队列
            }

            while (head != tail) {
                int j = queue[head++];  // 出队列
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) { //k是为访问的邻接顶点
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", vertices[k]);
                        queue[tail++] = k;
                    }
                }
            }
        }
        System.out.println();
    }

    /*
     * 打印矩阵队列图
     */
    private void print() {
        System.out.println("Matrix Graph:");
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++)
                if (matrix[i][j] == INF)
                    System.out.printf("%10s ", "INF");
                else
                    System.out.printf("%10d ", matrix[i][j]);
            System.out.println();
        }
    }

}