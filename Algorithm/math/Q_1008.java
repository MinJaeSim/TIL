package Algorithm.math;

import java.util.Scanner;

public class Q_1008 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double num1 = Double.parseDouble(input.split(" ")[0]);
        double num2 = Double.parseDouble(input.split(" ")[1]);

        System.out.println(num1 / num2);
    }
}
