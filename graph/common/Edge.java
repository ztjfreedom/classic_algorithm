package graph.common;

public class Edge {

    public String start;
    public String end;
    public Integer weight;

    public Edge(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

}
