package Algorithm.math;

import java.util.Scanner;

public class Q_11050 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int k = s.nextInt();

        System.out.println(bino(n,k));

    }

    private static int bino(int n, int k) {
        if (k == 0 || n == k)
            return 1;
        else
            return bino(n - 1, k - 1) + bino(n - 1, k);
    }
}
