package graph.toposort;

import java.util.*;

public class TopoSort {

    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        Graph graph = new Graph();
        graph.addNode(A);
        graph.addNode(B);
        graph.addNode(C);
        graph.addNode(D);
        graph.addNode(E);
        graph.addNode(F);
        graph.addEdge(A, B);
        graph.addEdge(B, C);
        graph.addEdge(B, D);
        graph.addEdge(D, C);
        graph.addEdge(E, C);
        graph.addEdge(C, F);

        List<Node> result = topoSort(graph);
        for(Node temp : result){
            System.out.print(temp.name + "-->");
        }
    }

    public static List<Node> topoSort(Graph graph) {
        List<Node> result = new LinkedList<>();
        Queue<Node> zeroInPathNodesQueue = new LinkedList<>();
        for (Node node : graph.nodesSet) {
            if (node.inPathNum == 0) {
                zeroInPathNodesQueue.offer(node);
            }
        }

        while (!zeroInPathNodesQueue.isEmpty()) {
            Node curr = zeroInPathNodesQueue.poll();
            result.add(curr);
            for (Node node : graph.edgesMap.get(curr)) {
                node.inPathNum --;
                if (node.inPathNum == 0) {
                    zeroInPathNodesQueue.offer(node);
                }
            }
            graph.nodesSet.remove(curr);
            graph.edgesMap.remove(curr);
        }

        if (!graph.nodesSet.isEmpty()) {
            System.out.println("cycle exists!");
        }
        return result;
    }

    private static class Node {
        public String name;
        public int inPathNum = 0;  // num of in paths
        public Node(String name) {
            this.name = name;
        }
    }

    private static class Graph {
        public Set<Node> nodesSet = new HashSet<>();
        public Map<Node, Set<Node>> edgesMap = new HashMap<>();

        public void addNode(Node node) {
            this.nodesSet.add(node);
            this.edgesMap.put(node, new HashSet<>());
        }

        public void addEdge(Node start, Node end) {
            this.edgesMap.get(start).add(end);
            end.inPathNum ++;
        }
    }

}
