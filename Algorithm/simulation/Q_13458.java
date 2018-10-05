package Algorithm.simulation;

import java.util.Scanner;

public class Q_13458 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n = scanner.nextInt();
        double result = n;
        double[] students = new double[(int)n];

        scanner.nextLine();

        String[] s = scanner.nextLine().split(" ");

        for (int i = 0; i < n; i++)
            students[i] = Integer.parseInt(s[i]);
        String[] bc = scanner.nextLine().split(" ");

        for (int i = 0; i < n; i++) {
            students[i] -= Integer.parseInt(bc[0]);

            int sub = (int) (students[i] / Integer.parseInt(bc[1]));
            if (students[i] - sub * Integer.parseInt(bc[1]) > 0)
                sub++;
            if (sub > 0)
                result += sub;
        }

        System.out.println(String.format("%.0f",result));
    }
}