package Algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q_2606 {
    private static int size;
    private static int[][] network;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine()) + 1;

        network = new int[size][size];
        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            network[a][b] = 1;
            network[b][a] = 1;
        }

        System.out.println(bfs(1));

    }

    private static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[size];
        q.offer(start);
        visit[start] = true;

        int r = 0;
        int temp;
        while (!q.isEmpty()) {
            temp = q.poll();
            for (int j = 0; j < size; j++) {
                if (network[temp][j] == 1 && visit[j] == false) {
                    q.offer(j);
                    visit[j] = true;
                    r++;
                }
            }
        }

        return r;
    }
}
