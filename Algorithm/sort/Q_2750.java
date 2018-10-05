package Algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Q_2750 {
    public static void main(String[] args) {
        ArrayList<Integer> num = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();

        for (int i = 0; i < size; i++) {
            num.add(s.nextInt());
        }

        Collections.sort(num);
        for (int i : num)
            System.out.println(i);
    }
}
