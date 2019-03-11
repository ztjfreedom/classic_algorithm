package graph.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// implementation of kruskal mst algorithm using adjacency matrix
public class Kruskal1 {

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

        List<Edge> result = kruskal(graph);
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

    public static List<Edge> kruskal(int[][] graph) {
        List<Edge> result = new ArrayList<>();

        int[] startsArray = new int[graph.length];
        for (int i=0; i<startsArray.length; i++) {
            startsArray[i] = i;
        }

        List<Edge> edges = getEdges(graph);
        edges.sort(Comparator.comparing(o -> o.weight));

        for (Edge edge : edges) {
            // using an easy quick find algorithm to check if two nodesSet are connected
            // quick union with weighted and path compression will have higher performance
            int start = startsArray[edge.start];
            int end = startsArray[edge.end];
            if (start != end) {
                result.add(edge);
                for (int i=0; i<startsArray.length; i++) {
                    if (startsArray[i] == end) {
                        startsArray[i] = startsArray[edge.start];
                    }
                }
            }
        }

        return result;
    }

    // convert adjacency matrix to edgesList list
    public static List<Edge> getEdges(int[][] graph) {
        List<Edge> edges = new ArrayList<>();
        for (int i=0; i<graph.length; i++) {
            for (int j=i+1; j<graph[0].length; j++) {
                if (graph[i][j] != INF) edges.add(new Edge(i, j, graph[i][j]));
            }
        }
        return edges;
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
