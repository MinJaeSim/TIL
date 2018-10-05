package Algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<String> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        while (true) {
            String str = br.readLine();
            if (str.equals(".")) {
                break;
            } else {
                boolean isCorrect = true;
                stack.clear();
                str = str.replaceAll("[a-zA-Z0-9 .]", "");

                for (int i = 0; i < str.length(); i++) {
                    char b = str.charAt(i);
                    switch (b) {
                        case '[':
                        case '(':
                            stack.push(b);
                            break;
                        case ']':
                            if (stack.isEmpty() || ( stack.pop()) != '[')
                                isCorrect = false;
                            break;
                        case ')':
                            if (stack.isEmpty() || ( stack.pop()) != '(')
                                isCorrect = false;
                            break;

                    }
                    if (!isCorrect)
                        break;
                }

                if (isCorrect && stack.empty())
                    result.add("yes");
                else
                    result.add("no");
            }
        }

        for (String s : result) {
            bw.write(s);
            bw.newLine();
        }

        bw.flush();

        bw.close();
        br.close();
    }
}
