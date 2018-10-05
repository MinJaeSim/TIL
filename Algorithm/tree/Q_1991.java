package Algorithm.tree;

import java.util.*;

public class Q_1991 {
    private static final String[] WORDS =
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<String, Integer> tree = new TreeMap<>();
//        ArrayList<Map.Entry<Integer, String>> tree2 = new ArrayList<>()
        
        int size = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < size; i++) {
            String[] s = scanner.nextLine().split(" ");
            if (tree.isEmpty()) {
                tree.put(s[0], 0);
                tree.put(s[1], 1);
                tree.put(s[2], 2);
            } else {
                tree.put(s[1], tree.get(s[0]) * 2 + 1);
                tree.put(s[2], tree.get(s[0]) * 2 + 2);
            }
        }

        int point = 1;
        boolean isRight = false;
        for (int i = 0; i < tree.size(); ) {
            if (isRight) {
                point = point / 4 + 1;
                isRight = false;
            }
            if (tree.containsKey(WORDS[point - 1])) {
                System.out.println(point);
                point = point * 2;
                i++;
            } else {
                point = point + 1;
                isRight = true;
            }
        }
    }

    private static void inorder(TreeMap<String,Integer> tree) {
        int point = 1;
        System.out.print(WORDS[point - 1]);
        for (int i = 0; i < tree.size(); i++) {
            if (tree.containsKey(WORDS[point * 2 - 1])) {
                System.out.print(WORDS[point * 2 - 1]);
                i++;
            }
            if (tree.containsKey(WORDS[point * 2])) {
                System.out.print(WORDS[point * 2]);
                i++;
            }
            point++;
        }
    }
}
