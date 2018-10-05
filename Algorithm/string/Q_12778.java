package Algorithm.string;

import java.io.*;
import java.util.StringTokenizer;

public class Q_12778 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());
        String[] results = new String[size];

        // A == 65

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            String type = st.nextToken();

            st = new StringTokenizer(br.readLine());

            StringBuilder stringBuilder = new StringBuilder();
            while (st.hasMoreTokens()) {
                if (type.equals("C")) {
                    stringBuilder.append(st.nextToken().charAt(0) - 64).append(" ");
                } else {
                    stringBuilder.append((char)(Integer.parseInt(st.nextToken()) + 64)).append(" ");
                }
            }

            results[i] = stringBuilder.toString();
        }

        for (String r : results) {
            bw.write(String.valueOf(r));
            bw.newLine();
        }

        bw.flush();

        bw.close();
        br.close();

    }
}
