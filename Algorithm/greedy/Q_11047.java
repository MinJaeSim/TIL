package Algorithm.greedy;

import java.io.*;
import java.util.StringTokenizer;

public class Q_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] money = new int[num];

        for (int i = 0; i < num; i++)
            money[i] = Integer.parseInt(br.readLine());

        int index = num - 1;
        int result = 0;

        while (target != 0) {
            if(target >= money[index]) {
                target -= money[index];
                result++;
            } else {
                index--;
            }
        }

        bw.write(String.valueOf(result));

        bw.flush();

        bw.close();
        br.close();
    }
}
