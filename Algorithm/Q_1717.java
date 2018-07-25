package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q_1717 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String[] size = br.readLine().split(" ");
//
//        ArrayList<Set> list = new ArrayList<>();
//        ArrayList<String> result = new ArrayList<>();
//
//        for (int i = 0; i < Integer.parseInt(size[0]) + 1; i++) {
//            list.add(new Set());
//        }
//
//        for (int i = 0; i < Integer.parseInt(size[1]); i++) {
//            String[] input = br.readLine().split(" ");
//            int op = Integer.parseInt(input[0]);
//            int a = Integer.parseInt(input[1]);
//            int b = Integer.parseInt(input[2]);
//
//            if (op == 0) {
//                list.get(a).arrayList.add(b);
//                list.get(b).arrayList.add(a);
//            } else {
//                boolean find = false;
//                for (Set aList : list) {
//                    if (aList.arrayList.contains(a) && aList.arrayList.contains(b)) {
//                        find = true;
//                        break;
//                    }
//                }
//
//                if (find)
//                    result.add("YES");
//                else
//                    result.add("NO");
//            }
//        }
//
//        for (String s : result)
//            System.out.println(s);
//
//    }
//
//    static class Set {
//        ArrayList<Integer> arrayList = new ArrayList<>();
//    }

    private static int[] parent = new int[10000001];
    private static int[] size = new int[10000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < Integer.parseInt(s[1]); i++) {
            String[] input = br.readLine().split(" ");
            int op = Integer.parseInt(input[0]);
            int a = Integer.parseInt(input[1]);
            int b = Integer.parseInt(input[2]);

            if (op == 0) {
                uni(a, b);
            }
            if (op == 1) {
                if (find(a) == find(b))
                    result.add("YES");
                else
                    result.add("NO");
            }
        }

        for(String r : result)
            System.out.println(r);
    }

    private static int find(int p) {
        if (parent[p] == p) return p;
        else return parent[p] = find(parent[p]);
    }

    private static void uni(int p, int q) {
        p = find(p);
        q = find(q);
        parent[p] = q;
        size[q] += size[p];
    }
}
