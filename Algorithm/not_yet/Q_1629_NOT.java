package Algorithm.not_yet;

import java.util.Scanner;

public class Q_1629_NOT {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a = s.nextDouble();
        double b = s.nextDouble();
        double c = s.nextDouble();

        System.out.println(a + " " + b + " " + c);
        for(int i = 0; i < b; i++)
            a *= a;
        System.out.println(a);
        System.out.println(a%c);
    }
}
