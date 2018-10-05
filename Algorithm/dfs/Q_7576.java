package Algorithm.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_7576 {
    private static int[][] map;
    private static List<Point> newList;
    private static int result;
    private static int n;
    private static int m;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        map = new int[m][n];

        List<Point> list = new ArrayList<>();
        newList = new ArrayList<>();

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int val = scanner.nextInt();
                map[i][j] = val;
                if (val == 1)
                    list.add(new Point(i, j));
            }
        while (list.size() != 0) {
            for (Point p : list) {
                int x = p.x;
                int y = p.y;
                spread(x + 1, y);
                spread(x - 1, y);
                spread(x, y + 1);
                spread(x, y - 1);
            }
            if (newList.size() == 0)
                break;
            list = cloneList(newList);
            newList.clear();
            result++;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 0)
                    result = -1;
            }
        }

        System.out.println(result);
    }

    private static List<Point> cloneList(List<Point> list) {
        List<Point> clone = new ArrayList<>(list.size());
        for (Point item : list) clone.add(item.clone());
        return clone;
    }

    private static void spread(int x, int y) {
        if (x < 0 || x > m - 1 || y < 0 || y > n - 1)
            return;
        if (map[x][y] == 0) {
            map[x][y] = 2;
            newList.add(new Point(x, y));
        }
    }

    static class Point implements Cloneable {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public Point clone() {
            try {
                return (Point) super.clone();
            } catch (Exception e) {
                return null;
            }
        }
    }
}
