package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int e = Integer.parseInt(str[0]);
        int s = Integer.parseInt(str[1]);
        int m = Integer.parseInt(str[2]);

        int ee = 1;
        int ss = 1;
        int mm = 1;
        int result = 1;
        while(true) {
            if(e == ee && s == ss && m == mm)
                break;
            else {
                ee = ee % 15 + 1;
                ss = ss % 28 + 1;
                mm = mm % 19 + 1;
                result++;
            }
        }

        System.out.println(result);
    }
}
