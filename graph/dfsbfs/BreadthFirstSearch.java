package graph.dfsbfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private List<Direction> directionList;

    public BreadthFirstSearch() {
        this.directionList = new LinkedList<>();
        this.directionList.add(new Direction(-1, 0, "up"));
        this.directionList.add(new Direction(0, 1, "right"));
        this.directionList.add(new Direction(1, 0, "down"));
        this.directionList.add(new Direction(0, -1, "left"));
    }

    public Point bfs(int[][] board) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] memo = new boolean[board.length][board[0].length];

        queue.offer(new Point(0, 0, "start", null));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == board.length - 1 && point.y == board[0].length - 1) return point;

            memo[point.x][point.y] = true;
            for (Direction nextDirection : this.directionList) {
                int nextX = point.x + nextDirection.x;
                int nextY = point.y + nextDirection.y;

                if (nextX >= 0 && nextX <= board.length - 1 && nextY >= 0 && nextY <= board[0].length - 1 &&
                        board[nextX][nextY] == 0 && !memo[nextX][nextY]) {
                    Point nextPoint = new Point(nextX, nextY, nextDirection.name, point);
                    queue.offer(nextPoint);
                }
            }
        }

        return null;
    }

    public List<Point> createRoute(Point endPoint) {
        LinkedList<Point> route = new LinkedList<>();
        while (endPoint != null) {
            route.addFirst(endPoint);
            endPoint = endPoint.prev;
        }
        return route;
    }

}
