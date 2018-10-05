package Algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_2738 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);

        String[][] s1 = new String[n][m];
        String[][] s2 = new String[n][m];


        for (int i =0; i < n; i++) {
            s1[i] = br.readLine().split(" ");
        }

        for (int i =0; i < n; i++) {
            s2[i] = br.readLine().split(" ");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                stringBuilder.append(Integer.parseInt(s1[i][j]) + Integer.parseInt(s2[i][j])).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
