package Algorithm.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_11718_11719 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> results = new ArrayList<>();

        String s;
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();

            results.add(s);
        }

        for(String r : results)
            System.out.println(r);
    }
}
