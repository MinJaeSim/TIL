package Algorithm;

import java.io.*;
import java.util.Stack;

public class Q_1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> stackLeft = new Stack<>();
        Stack<Character> stackRight = new Stack<>();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++)
            stackLeft.push(str.charAt(i));

        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            String input = br.readLine();

            if (input.charAt(0) == 'L') {
                if (!stackLeft.isEmpty())
                    stackRight.push(stackLeft.pop());
            } else if (input.charAt(0) == 'D') {
                if (!stackRight.isEmpty())
                    stackLeft.push(stackRight.pop());
            } else if (input.charAt(0) == 'B') {
                if (!stackLeft.isEmpty())
                    stackLeft.pop();
            } else if (input.charAt(0) == 'P') {
                stackLeft.push(input.charAt(2));
            }
        }

        while (!stackLeft.empty()) {
            stackRight.push(stackLeft.pop());
        }

        StringBuilder s = new StringBuilder();
        while (!stackRight.empty()) {
            s.append(stackRight.pop());
        }

        System.out.println(s.toString());
    }
}
