package Algorithm.bfs;

import java.util.*;

public class Q_2583 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int network[][] = new int[m][n];

        for (int i = 0; i < k; i++) {
            int x = scanner.nextInt();
            int y = m - scanner.nextInt() - 1;
            int x2 = scanner.nextInt();
            int y2 = m - scanner.nextInt() - 1;

            for (int p = y; p > y2; p--)
                for (int q = x; q < x2; q++)
                    network[p][q] = 1;
        }

        List<Integer> result = new ArrayList<>();
        boolean[][] visit = new boolean[m][n];

        for (int p = 0; p < m; p++)
            for (int q = 0; q < n; q++)
                if (network[p][q] == 0) {
                    int r = bfs(network, visit, p, q, m, n);
                    if (r > 0)
                        result.add(r);
                }


//        for (int p = 0; p < m; p++) {
//            for (int q = 0; q < n; q++)
//                System.out.print(network[p][q] + " ");
//            System.out.println();
//        }

        Collections.sort(result);
        System.out.println(result.size());
        for (int i : result)
            System.out.print(i + " ");

    }

    private static int bfs(int[][] network, boolean[][] visit, int x, int y, int m, int n) {
        if (visit[x][y])
            return 0;

        Queue<Integer> posX = new LinkedList<>();
        Queue<Integer> posY = new LinkedList<>();

        posX.offer(x);
        posY.offer(y);

        int size = 0;
        if (network[x][y] == 0) {
            visit[x][y] = true;
            size++;
        }

        while (!posX.isEmpty()) {
            int tempX = posX.poll();
            int tempY = posY.poll();
            for (int i = 0; i < m; i++) {
                if (tempX + 1 < m && network[tempX + 1][tempY] == 0 && !visit[(tempX + 1)][tempY]) {
                    visit[(tempX + 1)][tempY] = true;
                    posX.offer(tempX + 1);
                    posY.offer(tempY);
                    size++;
                } else if (tempY + 1 < n && network[tempX][tempY + 1] == 0 && !visit[tempX][tempY + 1]) {
                    visit[tempX][tempY + 1] = true;
                    posX.offer(tempX);
                    posY.offer(tempY + 1);
                    size++;
                } else if (tempX - 1 >= 0 && network[tempX - 1][tempY] == 0 && !visit[(tempX - 1)][tempY]) {
                    visit[(tempX - 1)][tempY] = true;
                    posX.offer(tempX - 1);
                    posY.offer(tempY);
                    size++;
                } else if (tempY - 1 >= 0 && network[tempX][tempY - 1] == 0 && !visit[tempX][tempY - 1]) {
                    visit[tempX][tempY - 1] = true;
                    posX.offer(tempX);
                    posY.offer(tempY - 1);
                    size++;
                }
            }
        }

        return size;
    }
}
