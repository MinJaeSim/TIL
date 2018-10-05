package Algorithm.etc;

import java.util.Scanner;

public class Q_8977 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        double B = Double.valueOf(input[2]);
        int[] num = new int[N];

        int sum = 0;

        String[] numInput = sc.nextLine().split(" ");

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(numInput[i]);
        }

        int start = (int) (B % N) - 1;
        if (start < 0)
            start = 0;

        for (int i = 0; i < K; i++) {
            sum += num[start];
            start++;
            if (start >= N)
                start = 0;
        }

        System.out.println(sum);
    }
}
