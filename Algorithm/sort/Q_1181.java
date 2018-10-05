package Algorithm.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Q_1181 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ArrayList<String> list = new ArrayList<>();
        scanner.nextLine();
        for (int i = 0; i < num; i++) {
            String s = scanner.nextLine();
            if (!list.contains(s))
                list.add(s);
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    return -1;
                } else if (o1.length() > o2.length()){
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        for (String s : list)
            System.out.println(s);
    }
}
