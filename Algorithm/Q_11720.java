package Algorithm;

import java.io.*;

public class Q_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        String num = br.readLine();

        int result = 0;

        for (int i = 0; i < size; i++) {
            result += num.charAt(i) - 48;
        }

        bw.write(String.valueOf(result));

        bw.flush();

        bw.close();
        br.close();
    }
}
