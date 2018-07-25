package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        int start = 665;
        int count = 0;
        while (true) {
            if (String.valueOf(start).contains("666")) {
                count++;
            }

            if (count == target)
                break;

            start++;
        }
        System.out.println(start);

    }
}
