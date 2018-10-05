package Algorithm.math;

import java.io.*;

public class Q_8741 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < num; i++) {
            stringBuilder.append(1);
        }

        for (int i = 0; i < num - 1; i++) {
            stringBuilder.append(0);
        }

        bw.write(stringBuilder.toString());

        bw.flush();

        bw.close();
        br.close();
    }

}
