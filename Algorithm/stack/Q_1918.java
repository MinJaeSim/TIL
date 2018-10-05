package Algorithm.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Q_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> op = new Stack<>();

        HashMap<Character, Integer> prec = new HashMap<>();
        prec.put('(', 0);
        prec.put(')', 0);
        prec.put('+', 1);
        prec.put('-', 1);
        prec.put('*', 2);
        prec.put('/', 2);

        String exp = br.readLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!op.empty()) {
                        if (op.peek() == '(')
                            break;
                        else if (prec.get(op.peek()) >= prec.get(c))
                            result.append(op.pop());
                        else
                            break;
                    }
                    op.push(c);
                    break;
                case ')':
                    while (!op.empty()) {
                        if (op.peek() == '(') {
                            op.pop();
                            break;
                        }
                        result.append(op.pop());
                    }
                    break;
                case '(':
                    op.push(c);
                    break;
                default:
                    result.append(c);
                    break;
            }
        }

        while (!op.empty())
            result.append(op.pop());

        System.out.println(result.toString());
    }
}
