package Algorithm.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_10820 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        List<Result> results = new ArrayList<>();

        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            Result r = new Result(0,0,0,0);
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i);
                if (index == 32)
                    r.w++;
                else if (97 <= index && index <= 122)
                    r.l++;
                else if (65 <= index && index <= 90)
                    r.c++;
                else if (48 <= index && index <= 57)
                    r.n++;
            }
            results.add(r);
        }

        for(Result r : results)
            System.out.println(r.toString());
    }

    static class Result {
        int l;
        int c;
        int n;
        int w;

        Result(int l, int c, int n, int w) {
            this.l = l;
            this.c = c;
            this.n = n;
            this.w = w;
        }

        @Override
        public String toString() {
            return String.valueOf(l + " " + c + " " + n + " " + w);
        }
    }
}
