package Algorithm.dp;

import java.util.Scanner;

public class Q_1463 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[] past = new int[number + 1];

        if (number < 3) {
            past = new int[4];
        }
        past[0] = -1;
        past[1] = 0;
        past[2] = 1;
        past[3] = 1;

        for (int i = 4; i <= number; i++) {
            past[i] = findNum(past, i);
        }

        System.out.println(past[number]);
    }

    private static int findNum(int[] past, int target) {
        int[] val = new int[3];

        val[0] = past[target - 1];

        if (target % 2 == 0)
            val[1] = past[target / 2];
        else
            val[1] = -1;
        if (target % 3 == 0)
            val[2] = past[target / 3];
        else
            val[2] = -1;

        int min = 99999999;
        for (int i = val.length - 1; i >= 0; i--) {
            if (min > val[i] && val[i] != -1)
                min = val[i];
        }

        return min + 1;
    }
}