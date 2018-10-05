package Algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int c = (int) s.charAt(i);
            if (65 <= c && c <= 90)
                c = c + 13 > 90 ? 65 + (c + 13) - 91 : c + 13;
            else if (97 <= c && c <= 122)
                c = c + 13 > 122 ? 97 + (c + 13) - 123 : c + 13;
            result.append((char) c);
        }
        System.out.println(result.toString());
    }
}
