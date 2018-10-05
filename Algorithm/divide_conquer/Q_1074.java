package Algorithm.divide_conquer;

import java.util.Scanner;

public class Q_1074 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] s = scanner.nextLine().split(" ");

            int N = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);

            System.out.println(sqr(r, c));
        }
    }

    private static long sqr(int r, int c) {
        int m, i;
        long s = 0, re;
        m = max(r, c);
        if (m == 0) return 0;
        for (i = 0; i <= 15; i++) {
            s = tpw(i);
            if (m < s) break;
        }
        s = s / 2;
        re = 0;
        if (r < s && s <= c) re = sqr(r, (int) (c - s)) + (s * s);
        else if (s <= r && c < s) re = sqr((int) (r - s), c) + 2 * (s * s);
        else if (s <= r && s <= c) re = sqr((int) (r - s), (int) (c - s)) + 3 * (s * s);
        return re;
    }

    private static long tpw(int k) {
        if (k == 0) return 1;
        else return 2 * tpw(k - 1);
    }

    private static int max(int x, int y) {
        if (x > y) return x;
        else return y;
    }
}
