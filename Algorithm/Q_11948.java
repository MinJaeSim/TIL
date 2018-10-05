package Algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q_11948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Integer> science = new ArrayList<>();
        List<Integer> society = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            science.add(Integer.valueOf(br.readLine()));

        for (int i = 0; i < 2; i++)
            society.add(Integer.valueOf(br.readLine()));

        science.remove(Collections.min(science));
        society.remove(Collections.min(society));

        int result = 0;

        for(int i : science)
            result += i;

        for(int i : society)
            result += i;


        bw.write(String.valueOf(result));
        bw.newLine();


        bw.flush();

        bw.close();
        br.close();
    }
}
