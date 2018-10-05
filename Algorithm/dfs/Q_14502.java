package Algorithm.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q_14502 {
    private static int[][] map;
    private static int[][] copy;
    private static List<Point> list;
    private static List<Integer> results;
    private static int n;
    private static int m;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        int wall = 0;

        map = new int[n][m];
        int[] com = new int[n * m];
        list = new ArrayList<>();
        results = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int val = scanner.nextInt();
                map[i][j] = val;
                if (val == 1)
                    wall++;
                else if (val == 2) {
                    list.add(new Point(i, j));
                }
            }

        combination(com, 0, n * m, 3, 0);

        System.out.println(n * m - Collections.min(results) - wall - 3);
    }

    private static int spread(int x, int y, int count, int n, int m, int[][] map) {
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1)
            return count;
        if (map[x][y] == 0 || map[x][y] == 2) {
            count += 1;
            map[x][y] = 3;
            count = spread(x + 1, y, count, n, m, map);
            count = spread(x - 1, y, count, n, m, map);
            count = spread(x, y + 1, count, n, m, map);
            count = spread(x, y - 1, count, n, m, map);
        } else if (map[x][y] == 1) {
            return count;
        }
        return count;
    }

    private static int[][] deepCopy(int[][] original, int n, int m) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[n][m];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    public static void combination(int[] arr, int index, int n, int r, int target) {
        if (r == 0) print(arr, index);
        else if (target == n) return;
        else {
            arr[index] = target;
            combination(arr, index + 1, n, r - 1, target + 1);
            combination(arr, index, n, r, target + 1);
        }
    }

    public static void print(int[] arr, int length) {
        int[] len = new int[length];
        System.arraycopy(arr, 0, len, 0, length);

        copy = deepCopy(map, n, m);

        if (copy[len[0] % map.length][len[0] / map.length] == 0 && copy[len[1] % map.length][len[1] / map.length] == 0 && copy[len[2] % map.length][len[2] / map.length] == 0) {

            copy[len[0] % map.length][len[0] / map.length] = 1;
            copy[len[1] % map.length][len[1] / map.length] = 1;
            copy[len[2] % map.length][len[2] / map.length] = 1;

            int val = 0;

            for (Point p : list) {
                val += spread(p.x, p.y, 0, n, m, copy);
            }
            results.add(val);
        }
    }


    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}