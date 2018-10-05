package Algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q_10159 {
    private static int totalSize;
    private static int[][] graph;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        totalSize = Integer.parseInt(br.readLine()) + 1;
        int preSizedNum = Integer.parseInt(br.readLine());

        graph = new int[totalSize][totalSize];

        result = new int[totalSize];

        for (int i = 0; i < preSizedNum; i++) {
            String[] str = br.readLine().split(" ");
            int first = Integer.parseInt(str[0]);
            int second = Integer.parseInt(str[1]);
            graph[first][second]++;
            graph[second][first]--;
        }

        for (int i = 1; i < totalSize; i++) {
            result[i] += bfs(i);
        }


        for (int i = 1; i < totalSize; i++) {
            System.out.println(totalSize - result[i] - 1);
        }
    }

    private static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[totalSize];
        q.offer(start);
        visit[start] = true;

        int r = 1;
        int temp;

        while (!q.isEmpty()) {
            temp = q.poll();
            for (int j = 0; j < totalSize; j++) {
                if (graph[temp][j] > 0 && visit[j] == false) {
                    q.offer(j);
                    result[j]++;
                    visit[j] = true;
                    r++;
                }
            }
        }
        return r;
    }
}
