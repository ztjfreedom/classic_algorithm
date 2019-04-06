package graph.dfsbfs;

public class Point {
    public int x;
    public int y;
    public String direction;
    public Point prev;

    public Point(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Point(int x, int y, String direction, Point prev) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.prev = prev;
    }
}
