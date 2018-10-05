package Algorithm.simulation;

import java.io.*;
import java.util.Arrays;

public class Q_2490 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] result = new char[3];

        for (int i = 0; i < 3; i++) {
            String[] str = br.readLine().split(" ");
            Arrays.sort(str);
            int val = Integer.parseInt(str[0] + str[1] + str[2] + str[3]);

            if (val == 1111) {
                result[i] = 'E';
            } else if (val == 1) {
                result[i] = 'C';
            } else if (val == 11) {
                result[i] = 'B';
            } else if (val == 111) {
                result[i] = 'A';
            } else {
                result[i] = 'D';
            }
        }

        for (char r : result)
            bw.write(String.valueOf(r + "\n"));

        bw.close();
        br.close();
    }
}


