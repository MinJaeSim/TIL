package Algorithm.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Q_2108 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        List<Integer> n = new ArrayList<>();
        int[] m = new int[size];
        int sum = 0;

        float mean;
        int center;
        int most = 0;
        int range;

        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;

            n.add(num);
            m[i] = num;
        }
        mean = sum / (float) size;
        Collections.sort(n);
        center = n.get(size / 2);
        range = Collections.max(n) - Collections.min(n);

        System.out.println(Math.round(mean));
        System.out.println(center);

        Map<Integer, Long> frequencies = Arrays.stream(m)
                .boxed()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        if (frequencies.isEmpty()) {
            System.out.println("No data");
        } else {
            long topFrequency = frequencies.values()
                    .stream()
                    .max(Long::compareTo)
                    .get();
            int[] topNumbers = frequencies.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == topFrequency)
                    .mapToInt(Map.Entry::getKey)
                    .toArray();

            long a = 1;
            List<Integer> val = new ArrayList<>();
            Arrays.sort(topNumbers);
            for (int number = 0; number < topNumbers.length; number++) {
                if (topFrequency == a) {
                    val.add(topNumbers[number]);
                }
                else if (topFrequency > a) {
                    a = topFrequency;
                    val.clear();
                    val.add(topNumbers[number]);
                }
//                System.out.println("" + topNumbers[number] + " => " + topFrequency);

                if (number == topNumbers.length -1) {
//                    System.out.println(val.get(0) + "   " + val.size());
                    if (val.size() > 1)
                        System.out.println(val.get(1));
                    else
                        System.out.println(val.get(0));
                }
            }
        }

        System.out.println(range);
    }
}
