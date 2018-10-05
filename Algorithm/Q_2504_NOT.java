package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Q_2504_NOT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        Stack<Character> s = new Stack<>();
        int bracesBig = 0;
        int bracesSmall = 0;

        int result = 0;
        int[] r = new int[exp.length()];

        for (int i = 0; i < exp.length(); i++) {
            switch (exp.charAt(i)) {
                case '(':
                    bracesSmall += 1;
                    break;
                case ')':
                    r[i] = 2;
                    bracesSmall -= 1;
                    break;
                case '[':
                    bracesBig += 1;
                    break;
                case ']':
                    r[i] = 3;
                    bracesBig -= 1;
                    break;
            }
            if (bracesBig < 0 || bracesSmall < 0) {
                System.out.println(0);
                break;
            }
        }

        for (int i : r)
            System.out.print(i + " ");

        System.out.println(" ");
        if (bracesBig > 0 || bracesSmall > 0)
            System.out.println(0);
        else {
            int prev = 0;
            int tempVal = 0;
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < exp.length(); i += 2) {
                if (r[i] == 0) {
                    prev++;
                    result += tempVal;
                } else if (prev > 0) {
                    prev = i;
                    prev--;
                } else if (prev == 0) {

                    tempVal *= r[i];
                    prev = 0;
                }
            }

            System.out.println(result);
        }
    }
}
