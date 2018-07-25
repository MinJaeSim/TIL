package Algorithm;

import java.util.HashMap;
import java.util.Scanner;

public class Q_1157 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Integer> hashMap = new HashMap<>();

        String[] s = scanner.nextLine().split("");
        for (String alphabet : s) {
            alphabet = alphabet.toLowerCase();
            if (hashMap.containsKey(alphabet))
                hashMap.put(alphabet, hashMap.get(alphabet) + 1);
            else
                hashMap.put(alphabet, 1);
        }

        String result = null;
        int max = -1;

        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) > max) {
                result = key;
                max = hashMap.get(key);
            }else if (hashMap.get(key)== max)
                result = null;
        }

        if (result != null)
            System.out.println(result.toUpperCase());
        else
            System.out.println("?");

    }
}
