package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q_2775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[n + 1][k + 1];

            for (int x = 0; x <= k; x++)
                for (int y = 0; y <= n; y++)
                    if (x == 0)
                        map[y][x] = y;
                    else if (y == 0)
                        map[y][x] = y + map[y][x - 1];
                    else if (x == 0)
                        map[y][x] = map[y - 1][x] + 1;
                    else
                        map[y][x] = map[y - 1][x] + map[y][x - 1];

            result.add(map[n][k]);
        }
        for(int r : result)
            System.out.println(r);
    }
}
