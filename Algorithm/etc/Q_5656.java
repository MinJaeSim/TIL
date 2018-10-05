package Algorithm.etc;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Q_5656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Boolean> result = new ArrayList<>();
        boolean isEnd = false;

        while (true) {
            String[] str = br.readLine().split(" ");
            int left = Integer.parseInt(str[0]);
            int right = Integer.parseInt(str[2]);

            String op = str[1];

            switch (op) {
                case ">":
                    result.add(left > right);
                    break;
                case ">=":
                    result.add(left >= right);
                    break;
                case "<":
                    result.add(left < right);
                    break;
                case "<=":
                    result.add(left <= right);
                    break;
                case "==":
                    result.add(left == right);
                    break;
                case "!=":
                    result.add(left != right);
                    break;
                case "E":
                    isEnd = true;
            }
            if (isEnd)
                break;
        }

        for (int i = 0; i < result.size(); i++)
            if (result.get(i))
                bw.write(String.valueOf("Case " + (i + 1) + ": true\n"));
            else
                bw.write(String.valueOf("Case " + (i + 1) + ": false\n"));

        bw.close();
        br.close();
    }
}


