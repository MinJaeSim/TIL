package Algorithm;

import java.io.*;

public class Q_5554 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int first = Integer.parseInt(br.readLine());
        int second = Integer.parseInt(br.readLine());
        int third = Integer.parseInt(br.readLine());
        int fourth = Integer.parseInt(br.readLine());

        int sum = first + second + third + fourth;

        int x = sum / 60;
        int y = sum % 60;


        bw.write(String.valueOf(x + "\n"));
        bw.write(String.valueOf(y + "\n"));

        bw.close();
        br.close();
    }
}
