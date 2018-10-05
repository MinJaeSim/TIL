package Algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q_1967 {

    private static ArrayList<Data> tree = new ArrayList<>();
    private static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        visit = new boolean[size + 1];
        for (int i = 0; i < size + 1; i++)
            tree.add(new Data());

        for (int i = 0; i < size - 1; i++) {
            String[] s = br.readLine().split(" ");
            int p = Integer.parseInt(s[0]);
            int c = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);

            tree.get(p).makeVal(c, w);
            tree.get(c).makeVal(p, w);
        }
        dfs(1, 0);
        m = 0;
        visit = new boolean[visit.length];
        dfs(index,0);
        System.out.println(m);
    }
    private static int index = -1;
    private static int m = 0;

    private static void dfs(int i, int max) {
        visit[i] = true;
        for (int[] j : tree.get(i).val) {
            int temp = max;
            if (!visit[j[0]]) {
                dfs(j[0], max + j[1]);
            } else if (tree.get(i).val.size() == 1) {
                if(max > m) {
                    m = max;
                    index = i;
                }
            }
            max = temp;
        }
    }

    public static class Data {
        ArrayList<int[]> val = new ArrayList<>();

        void makeVal(int to, int weight) {
            val.add(new int[]{to, weight});
        }
    }
}
