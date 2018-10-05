package Algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q_11656 {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String result = String.valueOf(in.readLine());
        list.add(result);
        while (result.length() > 1) {
            result = result.substring(1, result.length());
            list.add(result);
        }

        list.sort(Q_11656::compare);

        for (String s : list)
            System.out.println(s);
    }

    private static int compare(String o1, String o2) {
        int index = 0;
        int result = Character.compare(o1.charAt(index), o2.charAt(index));
        while (result == 0) {
            index += 1;
            if (o1.length()-1 < index) {
                return -1;
            } else if (o2.length()-1 < index) {
                return 1;
            } else {
                result = Character.compare(o1.charAt(index), o2.charAt(index));
            }
        }
        return result;
    }
}
