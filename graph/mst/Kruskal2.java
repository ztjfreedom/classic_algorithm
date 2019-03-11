package graph.mst;

import graph.common.Edge;
import graph.common.Graph;
import graph.common.Node;

import java.util.*;

// implementation of kruskal mst algorithm using Graph
public class Kruskal2 {

    public static void main(String[] args) {
        Graph graph = createGraph();
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

    public static Graph createGraph () {
        Graph graph = new Graph();
        String[] names = new String[] {"A", "B", "C", "D", "E", "F", "G"};
        for (String name : names) graph.addNode(name);

        String[][] edges = new String[][] {{"A", "B"}, {"A", "F"}, {"A", "G"}, {"B", "C"}, {"B", "F"},
                {"C", "D"}, {"C", "E"}, {"C", "F"}, {"D", "E"}, {"E", "F"}, {"E", "G"}, {"F", "G"}};
        int[] weights = new int[] {12, 16, 14, 10, 7, 3, 5, 6, 4, 2, 8, 9};
        for (int i=0; i<edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1], weights[i]);
        }
        return graph;
    }

    public static List<Edge> kruskal(Graph graph) {
        List<Edge> result = new ArrayList<>();
        Map<String, String> startMap = new HashMap<>();
        for (Map.Entry<String, Node> entry : graph.nodesMap.entrySet()) {
            startMap.put(entry.getKey(), entry.getKey());
        }

        graph.edgesList.sort(Comparator.comparing(o -> o.weight));
        for (Edge edge : graph.edgesList) {
            // using an easy quick find algorithm to check if two nodesSet are connected
            // quick union with weighted and path compression will have higher performance
            String start = startMap.get(edge.start);
            String end = startMap.get(edge.end);
            if (!start.equals(end)) {
                result.add(edge);
                for (Map.Entry<String, String> entry : startMap.entrySet()) {
                    if (entry.getValue().equals(end)) {
                        entry.setValue(start);
                    }
                }
            }
        }

        return result;
    }
}
