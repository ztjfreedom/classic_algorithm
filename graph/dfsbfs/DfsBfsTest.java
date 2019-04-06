package graph.dfsbfs;

import java.util.List;

public class DfsBfsTest {

    public static void main(String[] args) {
        int[][] board = new int[][] {
                {0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0}
        };

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        List<Point> route = depthFirstSearch.dfs(board);
        if (route != null) {
            for (Point point : route) {
                System.out.printf("%-5s %d %d\n", point.direction, point.x, point.y);
            }
        }
        System.out.println();

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        Point endPoint = breadthFirstSearch.bfs(board);
        route = breadthFirstSearch.createRoute(endPoint);
        if (route != null) {
            for (Point point : route) {
                System.out.printf("%-5s %d %d\n", point.direction, point.x, point.y);
            }
        }
    }

}
