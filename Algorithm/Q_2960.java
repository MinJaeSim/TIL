package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_2960 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        int size = Integer.parseInt(s[0]);
        int[] num = new int[size+1];
        int target = Integer.parseInt(s[1]);

        for(int i =2; i <= size; i++)
            num[i] = i;

        int result = 0;

        int p = 2;
        int pivot = 2;

        while (result != target) {
            if(num[p] != -1) {
                num[p] = -1;
                result++;

                if(result == target)
                    break;
            }
            if (p + pivot > size) {
                while (true) {
                    if (isPrime(++pivot)) {
                        p = pivot;
                        break;
                    }
                }
            } else
                p += pivot;
        }

        System.out.println(p);

    }

    private static boolean isPrime(int num) {
        if (num <= 1)
            return false;

        if (num % 2 == 0)
            return num == 2;


        for (int i = 3; i * i <= num; i++)
            if (num % i == 0)
                return false;

        return true;
    }
}
