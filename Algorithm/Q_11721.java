package Algorithm;

import java.io.*;

public class Q_11721 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        StringBuilder result = new StringBuilder();
        for(int i = 1; i <= str.length(); i++) {
            result.append(str.charAt(i-1));
            if(i % 10 == 0 && i > 0)
                result.append("\n");
        }
        bw.write(result.toString());

        bw.flush();

        bw.close();
        br.close();
    }
}
