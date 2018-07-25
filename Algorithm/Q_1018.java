package Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Q_1018 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String[] board = new String[n];
        char[][] input = new char[n][m];

        char[][] bw = new char[8][8];
        char[][] wb = new char[8][8];

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            board[i] = scanner.nextLine();
        }

        for (int i = 0; i < 8; i++) {
            int mod = i % 2;
            for (int j = 0; j < 8; j++)
                if (j % 2 == mod) {
                    bw[i][j] = 'B';
                    wb[i][j] = 'W';
                } else {
                    bw[i][j] = 'W';
                    wb[i][j] = 'B';
                }
        }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                input[i][j] = board[i].charAt(j);

        int x = 0;
        int y = 0;

        while (x+ 8 <= m && y + 8 <= n) {
            int bwCount = 0;
            int wbCount = 0;
            for (int i = y; i < y + 8; i++)
                for (int j = x; j < x + 8; j++) {
                    if (input[i][j] != bw[i-y][j-x])
                        bwCount++;
                    if (input[i][j] != wb[i-y][j-x])
                        wbCount++;
                }

            list.add(bwCount);
            list.add(wbCount);
            x++;

            if (x + 8 > m) {
                y++;
                x = 0;
            }
        }

        System.out.println(Collections.min(list));
    }
}
