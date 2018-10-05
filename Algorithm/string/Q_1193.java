package Algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        int sum = 0;
        int count = 1;
        int order;

        while (true) {
            if (target == 1)
                break;
            sum += count;
            count++;
            if (sum + count >= target)
                break;
        }


        // 1 2
        // 3 3
        // 6 4
        // 10 5
//        System.out.println(sum);
        order = target - sum;

        if (count % 2 == 0)
            System.out.println(String.format("%d/%d\n", order, (count + 1) - order));
        else
            System.out.println(String.format("%d/%d\n", (count + 1) - order, order));
    }
}
