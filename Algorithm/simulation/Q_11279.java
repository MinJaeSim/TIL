package Algorithm.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> q = new PriorityQueue<>(1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : o1.equals(o2) ? 0 : 1;
            }
        });

        int size = Integer.parseInt(br.readLine());
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0)
                if (q.size() > 0)
                    result.add(q.poll());
                else
                    result.add(0);
            else {
                q.offer(num);
            }
        }

        for(int n : result)
            System.out.println(n);
    }
}
