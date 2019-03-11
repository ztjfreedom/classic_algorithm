package graph.mst;

import java.util.*;

// implementation of prim mst algorithm using adjacency matrix
public class Prim {

    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] graph = {
                {   0,  12, INF, INF, INF,  16,  14},
                {  12,   0,  10, INF, INF,   7, INF},
                { INF,  10,   0,   3,   5,   6, INF},
                { INF, INF,   3,   0,   4, INF, INF},
                { INF, INF,   5,   4,   0,   2,   8},
                {  16,   7,   6, INF,   2,   0,   9},
                {  14, INF, INF, INF,   8,   9,   0}
        };

        List<Edge> result = prim(graph);
        int sum = 0;
        for (Edge edge : result) {
            sum += edge.weight;
            System.out.print("start: " + edge.start + ", ");
            System.out.print("end: " + edge.end + ", ");
            System.out.print("weight: " + edge.weight);
            System.out.println();
        }
        System.out.println("sum: "+ sum);
    }

    public static List<Edge> prim(int[][] graph) {
        List<Edge> result = new ArrayList<>();

        // start from node 0
        List<Edge> weightList = new LinkedList<>();
        for (int i=1; i<graph[0].length; i++) {
            Edge edge = new Edge(0, i, graph[0][i]);
            weightList.add(edge);
        }

        while (!weightList.isEmpty()) {
            // find minimum weight
            Edge minWeightEdge = null;
            int minWeight = Integer.MAX_VALUE;
            for (Edge edge : weightList) {
                if (edge.weight < minWeight) {
                    minWeight = edge.weight;
                    minWeightEdge = edge;
                }
            }

            if (minWeightEdge != null) {
                result.add(minWeightEdge);
                weightList.remove(minWeightEdge);

                // re calculate the weight
                for (Edge edge : weightList) {
                    int weight = graph[minWeightEdge.end][edge.end];
                    if (weight < edge.weight) {
                        edge.start = minWeightEdge.end;
                        edge.weight = weight;
                    }
                }
            }
        }

        return result;
    }

    public static class Edge {
        public int start;
        public int end;
        public Integer weight;
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

}
