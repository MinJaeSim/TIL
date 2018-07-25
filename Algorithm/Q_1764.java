package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Q_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        HashMap<String, Integer> hashMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < Integer.parseInt(size[0]); i++) {
            hashMap.put(br.readLine(), 0);
        }

        for (int i = 0; i < Integer.parseInt(size[1]); i++) {
            String s = br.readLine();
            if (hashMap.containsKey(s)) {
                list.add(s);
                count++;
            }
        }

        Collections.sort(list);

        for(String s : list)
            stringBuilder.append(s).append("\n");

        System.out.println(count);
        if (count > 0)
            System.out.println(stringBuilder.toString());

    }
}
