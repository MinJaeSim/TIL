package Algorithm.string;

import java.io.*;
import java.util.StringTokenizer;

public class Q_11944 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        int max = Integer.parseInt(st.nextToken());

        StringBuilder result = new StringBuilder();

        if (Integer.parseInt(s) < max) {
            for (int i = 0; i < Integer.parseInt(s); i++)
                result.append(s);
        } else {
            int c = 0;

            while (max > 0) {
                result.append(s.charAt(c % s.length()));
                c++;
                max--;
            }
        }
        bw.write(result.toString());

        bw.flush();

        bw.close();
        br.close();
    }
}
