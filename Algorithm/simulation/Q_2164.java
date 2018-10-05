package Algorithm.simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Q_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());

        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < size; i++)
            cards.add(i + 1);

        while (true) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 1; i < cards.size(); i += 2)
                temp.add(cards.get(i));

            if (cards.size() % 2 == 1)
                temp.add(0, size);

            cards = temp;

            if (cards.size() < 2)
                break;
        }

        bw.write(String.valueOf(cards.get(0)));

        bw.flush();

        bw.close();
        br.close();
    }
}
