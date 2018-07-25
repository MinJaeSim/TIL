package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1297 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int diagonal = Integer.parseInt(s[0]);
        int heightRatio = Integer.parseInt(s[1]);
        int widthRatio = Integer.parseInt(s[2]);

        double len = Math.sqrt(diagonal*diagonal / (double)(heightRatio*heightRatio + widthRatio*widthRatio));


        System.out.println(String.format("%.0f %.0f",Math.floor(len*heightRatio),Math.floor(len*widthRatio)));
    }
}
