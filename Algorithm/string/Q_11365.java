package Algorithm.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q_11365 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while(true) {
            String s = br.readLine();

            if(s.equals("END"))
                break;
            else
                list.add(new StringBuilder(s).reverse().toString());
        }

        for(String s : list)
            System.out.println(s);
    }
}
