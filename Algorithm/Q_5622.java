package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Q_5622 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        HashMap<Character, Integer> num = new HashMap<>();

        int result = 0;

        num.put('A',2);
        num.put('B',2);
        num.put('C',2);

        num.put('D',3);
        num.put('E',3);
        num.put('F',3);

        num.put('G',4);
        num.put('H',4);
        num.put('I',4);

        num.put('J',5);
        num.put('K',5);
        num.put('L',5);

        num.put('M',6);
        num.put('N',6);
        num.put('O',6);

        num.put('P',7);
        num.put('Q',7);
        num.put('R',7);
        num.put('S',7);

        num.put('T', 8);
        num.put('U', 8);
        num.put('V', 8);

        num.put('W', 9);
        num.put('X', 9);
        num.put('Y', 9);
        num.put('Z', 9);


        for (int i = 0; i < s.length(); i++) {
            result += num.get(s.charAt(i)) + 1;
        }

        System.out.println(result);
    }
}
