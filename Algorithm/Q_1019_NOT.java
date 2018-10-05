package Algorithm;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1019_NOT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());
        int[] count = new int[10];

        for (int i = 1; i <= number; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++)
                count[s.charAt(j) - '0']++;
        }

        for (int i : count)
            System.out.print(i + " ");
    }


    @Test
    public void foo() {
        int number = 1000000000;
        int[] count = new int[10];

        for (int i = 1; i <= number; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++)
                count[s.charAt(j) - '0']++;
        }

        for (int i : count)
            System.out.print(i + " ");
    }
}