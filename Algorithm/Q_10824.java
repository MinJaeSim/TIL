package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        StringBuilder builder = new StringBuilder();
        builder.append(s[0]).append(s[1]);
        double x = Double.parseDouble(builder.toString());
        builder = new StringBuilder();
        builder.append(s[2]).append(s[3]);
        double y = Double.parseDouble(builder.toString());

        System.out.println(String.format("%.0f",x+y));
    }
}
