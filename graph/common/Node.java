package graph.common;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public String name;
    public List<Edge> edges;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

}
