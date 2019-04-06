package graph.dfsbfs;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearch {

    private List<Direction> directionList;

    public DepthFirstSearch() {
        this.directionList = new LinkedList<>();
        this.directionList.add(new Direction(-1, 0, "up"));
        this.directionList.add(new Direction(0, 1, "right"));
        this.directionList.add(new Direction(1, 0, "down"));
        this.directionList.add(new Direction(0, -1, "left"));
    }

    public List<Point> dfs(int[][] board) {
        LinkedList<Point> route = new LinkedList<>();
        boolean[][] memo = new boolean[board.length][board[0].length];
        Point startPoint = new Point(0, 0, "start");
        boolean result = dfs(board, memo, startPoint, route);
        return result ? route : null;
    }

    private boolean dfs(int[][] board, boolean[][] memo, Point point, LinkedList<Point> route) {
        route.add(point);
        if (point.x == board.length - 1 && point.y == board[0].length - 1) return true;

        memo[point.x][point.y] = true;

        for (Direction nextDirection : this.directionList) {
            int nextX = point.x + nextDirection.x;
            int nextY = point.y + nextDirection.y;

            if (nextX >= 0 && nextX <= board.length - 1 && nextY >= 0 && nextY <= board[0].length - 1 &&
                    board[nextX][nextY] == 0 && !memo[nextX][nextY]) {
                Point nextPoint = new Point(nextX, nextY, nextDirection.name);
                boolean result = dfs(board, memo, nextPoint, route);
                if (result) return true;
            }
        }

        route.removeLast();
        return false;
    }

}
