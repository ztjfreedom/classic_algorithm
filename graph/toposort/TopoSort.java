package graph.toposort;

import java.util.*;

public class TopoSort {

    /**
     * Node
     */
    private static class Node {
        public Object val;
        public int inPathNum = 0;  // num of in paths
        public Node(Object val) {
            this.val = val;
        }
    }

    /**
     * Graph
     */
    private static class Graph {
        // Set of nodes
        public Set<Node> nodesSet = new HashSet<>();
        // Map of edges
        public Map<Node, Set<Node>> edgesMap = new HashMap<>();

        // Add node and edge to graph
        public boolean addNode(Node start, Node end) {
            if (!nodesSet.contains(start)) {
                nodesSet.add(start);
            }
            if (!nodesSet.contains(end)) {
                nodesSet.add(end);
            }
            if (edgesMap.containsKey(start)
                    && edgesMap.get(start).contains(end)) {
                return false;
            }
            if (edgesMap.containsKey(start)) {
                edgesMap.get(start).add(end);
            } else {
                Set<Node> temp = new HashSet<>();
                temp.add(end);
                edgesMap.put(start, temp);
            }
            end.inPathNum++;
            return true;
        }
    }

    //Kahn Topo Algorithm
    private static class KahnTopo {
        private List<Node> result;  // results
        private Queue<Node> zeroInPathNodesQueue;  // in paths 0 nodes
        private Graph graph;

        // constructor
        public KahnTopo(Graph graph) {
            this.graph = graph;
            this.result = new ArrayList<>();
            this.zeroInPathNodesQueue = new LinkedList<>();
            for(Node node : this.graph.nodesSet){
                if(node.inPathNum == 0){
                    this.zeroInPathNodesQueue.offer(node);
                }
            }
        }

        // Topo sort
        private void process() {
            while (!zeroInPathNodesQueue.isEmpty()) {
                Node node = zeroInPathNodesQueue.poll();

                // add current node to results
                result.add(node);

                if (this.graph.edgesMap.keySet().isEmpty()){
                    return;
                }

                // iterate edges out from current node
                for (Node destinationNode : this.graph.edgesMap.get(node) ) {
                    // remove the edge
                    destinationNode.inPathNum--;
                    if (0 == destinationNode.inPathNum) {
                        zeroInPathNodesQueue.add(destinationNode);
                    }
                }
                this.graph.nodesSet.remove(node);
                this.graph.edgesMap.remove(node);
            }

            // If there are still some nodes with inPath, there must be a cycle
            if (!this.graph.nodesSet.isEmpty()) {
                throw new IllegalArgumentException("Has Cycle!");
            }
        }

        // result set
        public Iterable<Node> getResult() {
            return result;
        }
    }

    // test
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        Graph graph = new Graph();
        graph.addNode(A, B);
        graph.addNode(B, C);
        graph.addNode(B, D);
        graph.addNode(D, C);
        graph.addNode(E, C);
        graph.addNode(C, F);

        KahnTopo topo = new KahnTopo(graph);
        topo.process();
        for(Node temp : topo.getResult()){
            System.out.print(temp.val.toString() + "-->");
        }
    }

}
