package Algorithm.string;

import java.util.Scanner;

public class Q_10809 {
    public static void main(String[] args) {
        int[] result = new int[26];
        for (int i = 0; i < result.length; i++)
            result[i] = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        for(int i = 97; i < 123; i++)
            result[i-97] = input.indexOf((char)i);

        for (int i : result)
            System.out.print(i + " ");
    }
}
