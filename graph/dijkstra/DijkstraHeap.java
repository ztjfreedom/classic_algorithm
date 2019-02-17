package graph.dijkstra;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraHeap {

    public static class NodeDistance {
        public String node;
        public Integer distance;
        public NodeDistance(String node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static void addEdge(Map<String, Map<String, Integer>> graph, String start, String end, int distance) {
        if (!graph.containsKey(start)) graph.put(start, new HashMap<>());
        if (!graph.containsKey(end)) graph.put(end, new HashMap<>());
        graph.get(start).put(end, distance);
        graph.get(end).put(start, distance);
    }

    public static int dijkstra(Map<String, Map<String, Integer>> graph, String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) return -1;

        // Set<String> finished = new HashSet<>();
        Map<String, NodeDistance> map = new HashMap<>();
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(new Comparator<NodeDistance>() {
            @Override
            public int compare(NodeDistance o1, NodeDistance o2) {
                return o1.distance.compareTo(o2.distance);
            }
        });

        // start node
        // finished.add(start);
        for (Map.Entry<String, Integer> entry : graph.get(start).entrySet()) {
            NodeDistance nd = new NodeDistance(entry.getKey(), entry.getValue());
            map.put(entry.getKey(), nd);
            pq.offer(nd);
        }

        // go through
        while (!pq.isEmpty()) {
            NodeDistance top = pq.poll();
            if (top.node.equals(end)) return top.distance;

            Map<String, Integer> edges = graph.get(top.node);
            for (Map.Entry<String, Integer> edge : edges.entrySet()) {
                String node = edge.getKey();
                int distance = edge.getValue();
                if (!node.equals(start)) {
                    if (map.containsKey(node)) {
                        map.get(node).distance = Math.min(map.get(top.node).distance + distance, map.get(node).distance);
                    } else {
                        NodeDistance nd = new NodeDistance(node, map.get(top.node).distance + distance);
                        map.put(node, nd);
                        pq.offer(nd);
                    }
                }
            }
        }

        return -1;
    }

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

}
