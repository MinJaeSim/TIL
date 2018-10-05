package Algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q_1012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        int size = Integer.parseInt(br.readLine());

        List<Integer> posX = new ArrayList<>();
        List<Integer> posY = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            posX.clear();
            posY.clear();
            stringTokenizer = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(stringTokenizer.nextToken());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            int k = Integer.parseInt(stringTokenizer.nextToken());

            int[][] network = new int[m][n];

            for (int j = 0; j < k; j++) {
                stringTokenizer = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(stringTokenizer.nextToken());
                int y = Integer.parseInt(stringTokenizer.nextToken());
                posX.add(x);
                posY.add(y);
                network[x][y] = 1;
            }

            boolean[][] visit = new boolean[m][n];

            int count = 0;
            for (int j = 0; j < posX.size(); j++) {
                if (bfs(network, posX.get(j), posY.get(j), visit, n, m))
                    count++;
            }


            System.out.println(count);
        }
    }

    private static boolean bfs(int[][] network, int x, int y, boolean[][] visit, int n, int m) {

        if (visit[x][y])
            return false;

        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();

        queueX.offer(x);
        queueY.offer(y);

        visit[x][y] = true;
        while (!queueX.isEmpty()) {
            int tempX = queueX.poll();
            int tempY = queueY.poll();
            for (int i = 0; i < visit.length; i++) {
                if (tempX + 1 < m && network[tempX + 1][tempY] == 1 && !visit[(tempX + 1)][tempY]) {
                    visit[(tempX + 1)][tempY] = true;
                    queueX.offer(tempX + 1);
                    queueY.offer(tempY);
                } else if (tempY + 1 < n && network[tempX][tempY + 1] == 1 && !visit[tempX][tempY + 1]) {
                    visit[tempX][tempY + 1] = true;
                    queueX.offer(tempX);
                    queueY.offer(tempY + 1);
                } else if (tempX - 1 >= 0 && network[tempX - 1][tempY] == 1 && !visit[(tempX - 1)][tempY]) {
                    visit[(tempX - 1)][tempY] = true;
                    queueX.offer(tempX - 1);
                    queueY.offer(tempY);
                } else if (tempY - 1 >= 0 && network[tempX][tempY - 1] == 1 && !visit[tempX][tempY - 1]) {
                    visit[tempX][tempY - 1] = true;
                    queueX.offer(tempX);
                    queueY.offer(tempY - 1);
                }
            }
        }

        return true;

    }
}
