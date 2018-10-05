package Algorithm.math;

import java.util.Scanner;

public class Q_8393 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        double result = 0;

        for(int i = 1; i <= n; i++)
            result += i;

        System.out.println(String.format("%.0f",result));
    }
}
