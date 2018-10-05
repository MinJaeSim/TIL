package Algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1212 {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String num = String.valueOf(in.readLine());

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < num.length(); i++)
            stringBuilder.append(changer(num.charAt(i)));

        String result = stringBuilder.toString();

        while (result.startsWith("0") && result.length() > 1) {
            result = result.substring(1,result.length());
        }

        System.out.println(result);

    }

    private static String changer(char num) {
        StringBuilder stringBuilder = new StringBuilder();

        int target = Integer.parseInt(String.valueOf(num));
        while (true) {
            if (target == 0)
                break;

            stringBuilder.append(target % 2);
            target /= 2;
        }
        while(stringBuilder.length() < 3)
            stringBuilder.append(0);

        return stringBuilder.reverse().toString();
    }
}
