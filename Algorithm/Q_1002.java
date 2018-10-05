package Algorithm;

import java.io.*;
import java.util.StringTokenizer;

public class Q_1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int size = Integer.parseInt(br.readLine());

        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            result[i] = calculate(new Circle(st.nextToken(), st.nextToken(), st.nextToken()), new Circle(st.nextToken(), st.nextToken(), st.nextToken()));
            bw.flush();
        }

        for(int r : result)
            bw.write(String.valueOf(r+"\n"));

        bw.close();
        br.close();
    }

    private static int calculate(Circle c1, Circle c2) {
        double distance = Math.sqrt(Math.pow(c1.x - c2.x, 2) + Math.pow(c1.y - c2.y, 2));

        int diff = Math.abs(c1.radius - c2.radius);
        int sum = c1.radius + c2.radius;

        if(diff == 0 && distance == 0)
            return -1;

        if(diff < distance && distance < sum)
            return 2;
        else if (diff == distance || sum == distance)
            return 1;
        else
            return 0;
    }

    static class Circle {
        int x;
        int y;
        int radius;

        public Circle(String x, String y, String radius) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
            this.radius = Integer.parseInt(radius);
        }
    }
}
