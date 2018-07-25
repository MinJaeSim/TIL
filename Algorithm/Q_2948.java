package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class Q_2948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] date = br.readLine().split(" ");

        Calendar c = Calendar.getInstance();
        c.set(2009, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));

        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        System.out.println(day[c.get(Calendar.DAY_OF_WEEK) - 1]);
    }
}