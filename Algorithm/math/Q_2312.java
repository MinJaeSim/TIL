package Algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q_2312 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        List<String> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(br.readLine());

            int count = 0;
            int div = 2;
            while (num >= 2) {
                if (num % div == 0) {
                    num /= div;
                    count++;
                } else {
                    if (count > 0)
                        result.add(String.valueOf(div + " " + count));
                    div++;
                    count = 0;
                }
            }
            if (count > 0)
                result.add(String.valueOf(div + " " + count));
        }

        for (String str : result) {
            System.out.println(str);
        }
    }
}
