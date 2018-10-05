package Algorithm.not_yet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q_2178_NOT {
    private static int n;
    private static int m;
    private static int[][] map;
    private static ArrayList<Integer> list;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        list = new ArrayList<>();
        map = new int[n][m];
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                int n = s.charAt(j) - '0';
                map[i][j] = n;
                if (n == 1)
                    list.add(i*n+j);
            }
        }
        bfs2(0,list.size()-1);

        System.out.println(list.size());
    }

    private static void bfs2(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        int[] prevNode = new int[list.size()];
        int[] prevNum = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            prevNum[i] = -1;
            prevNode[i] = -1;
        }

        q.offer(start);
        prevNode[start] = -1;
        prevNum[start] = 0;

        int temp;
        while (!q.isEmpty()) {
            temp = q.poll();
            for (int j = 0; j < list.size(); j++) {
                if (map[temp][j] == 1) {
                    q.offer(j);
                    prevNode[j] = temp;
                    prevNum[j] = prevNum[temp] + 1;
                }
            }
        }

        System.out.println(prevNum[target]);
    }

    private static int search(int x, int y, int count) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(x + "," + y + "   /" + count);

        System.out.println("===========================");
        if (x < 0 || x > n - 1 || y < 0 || y > m - 1)
            return -1;
        if (x == n - 1 && y == m - 1) {
            list.add(count);
            return 0;
        } else if (map[x][y] == 1) {
            map[x][y] = 2;
//            if (x + 1 <= n - 1 && map[x + 1][y] != 0)
                search(x + 1, y, count + 1);
//            else if (y + 1 <= m - 1 && map[x][y + 1] != 0)
                search(x, y + 1, count + 1);
//            else if (x - 1 >= 0 && map[x - 1][y] != 0)
                search(x - 1, y, count+1);
//            else
                search(x, y - 1, count + 1);

        }
        return -1;
    }
}
