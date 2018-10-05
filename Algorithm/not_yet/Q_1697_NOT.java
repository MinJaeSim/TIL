package Algorithm.not_yet;

import java.util.Scanner;

public class Q_1697_NOT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int count;

        if (n == k)
            count = 0;
        else if (n > k)
            count = k - n;
        else if (k - n == 1)
            count = 1;
        else
            count = find(n, k, 0);


        System.out.println(count);
    }

    private static int find(int n, int k, int count) {

        return 0;
    }
}
