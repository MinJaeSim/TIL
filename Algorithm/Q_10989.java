package Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q_10989 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        Bucket[] buckets = new Bucket[10];
        for (int i = 0; i < 10; i++)
            buckets[i] = new Bucket();

        int max = -1;

        for (int i = 0; i < size; i++) {
            String s = br.readLine();
            if (max < s.length())
                max = s.length();

            int num = Integer.parseInt(s);
            buckets[num % 10].list.add(num);
        }

        System.out.println(max);
        for (int k = 1; k <= max; k++)
            for (int i = 0; i < 10; i++) {
                Bucket b = buckets[i];
                for (int j = 0; j < b.list.size(); j++) {
                    System.out.println(j + " " + b.list.size());
                    if (b.list.get(j) > Math.pow(10,k))
                        buckets[(int) (b.list.get(j) / Math.pow(10,k))].list.add(b.list.get(j));
                }
            }
        // buckets[i] = new Bucket();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            Bucket b = buckets[i];
            for (int j = 0; j < b.list.size(); j++) {
                stringBuilder.append(b.list.get(j)).append('\n');
            }
        }

        System.out.println(stringBuilder.toString());


//        list.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() == o2.length()) {
//                    int i = 0;
//                    while (true) {
//                        if (Character.compare(o1.charAt(i), o2.charAt(i)) == 0)
//                            i++;
//                        else
//                            return Character.compare(o1.charAt(i), o2.charAt(i));
//                    }
//                } else {
//                    return o1.length() - o2.length();
//                }
//
//            }
//        });


//        for (String s : list)
//            System.out.println(s);
    }

    static class Bucket {
        List<Integer> list = new ArrayList<>();

    }
}

