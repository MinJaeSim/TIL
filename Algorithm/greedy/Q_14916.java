package Algorithm.greedy;

import java.util.Scanner;

public class Q_14916 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int result;

        if (num % 5 == 0) {
            result = num / 5;
        } else {
            if (num / 5 - 1 < 0)
                result = -1;
            else {
                int n = num % 5;

                if (n % 2 == 0)
                    result = num / 5 + (num - 5 * (num / 5)) / 2;
                else {
                    result = (num - ((num / 5 - 1) * 5)) / 2 + (num / 5 - 1);
                }
            }
        }
        System.out.println(result);
    }
}
