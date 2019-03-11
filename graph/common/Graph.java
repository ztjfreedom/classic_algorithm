package graph.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    public Map<String, Node> nodesMap;
    public List<Node> nodesList;
    public List<Edge> edgesList;

    public Graph() {
        this.nodesMap = new HashMap<>();
        this.nodesList = new ArrayList<>();
        this.edgesList = new ArrayList<>();
    }

    public void addNode(String name) {
        Node node = new Node(name);
        this.addNode(node);
    }

    public void addNode(Node node) {
        this.nodesMap.put(node.name, node);
        this.nodesList.add(node);
    }

    public void addEdge(String start, String end, int weight) {
        Edge edge = new Edge(start, end, weight);
        this.addEdge(edge);
    }

    public void addEdge(Edge edge) {
        this.edgesList.add(edge);
        this.nodesMap.get(edge.start).edges.add(edge);
    }

}
