package graph.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        addEdge(graph, "A", "B", 12);
        addEdge(graph, "A", "F", 16);
        addEdge(graph, "A", "G", 14);
        addEdge(graph, "B", "C", 10);
        addEdge(graph, "B", "F", 7);
        addEdge(graph, "G", "F", 9);
        addEdge(graph, "G", "E", 8);
        addEdge(graph, "F", "C", 6);
        addEdge(graph, "F", "E", 2);
        addEdge(graph, "C", "E", 5);
        addEdge(graph, "C", "D", 3);
        addEdge(graph, "E", "D", 4);

        System.out.println(dijkstra(graph, "D", "A"));
    }

    public static int dijkstra(Map<String, Map<String, Integer>> graph, String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) return -1;

        Set<String> finished = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        // start node
        finished.add(start);
        for (Map.Entry<String, Integer> entry : graph.get(start).entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }

        // go through
        while (finished.size() < graph.size()) {
            int minDistance = Integer.MAX_VALUE;
            String minNode = "";
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (!finished.contains(entry.getKey()) && entry.getValue() < minDistance) {
                    minNode = entry.getKey();
                }
            }

            if (minNode.equals(end)) return map.get(minNode);

            Map<String, Integer> edges = graph.get(minNode);
            for (String node : edges.keySet()) {
                if (!finished.contains(node)) {
                    int distance = edges.get(node);
                    if (map.containsKey(node)) {
                        map.put(node, Math.min(map.get(node), distance + map.get(minNode)));
                    } else {
                        map.put(node, distance + map.get(minNode));
                    }
                }
            }

            finished.add(minNode);
        }

        return -1;
    }

    public static void addEdge(Map<String, Map<String, Integer>> graph, String start, String end, int distance) {
        if (!graph.containsKey(start)) graph.put(start, new HashMap<>());
        if (!graph.containsKey(end)) graph.put(end, new HashMap<>());
        graph.get(start).put(end, distance);
        graph.get(end).put(start, distance);
    }

}
