package Algorithm.not_yet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q_2887_NOT {
    private static List<Planet> planets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        planets = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String[] input = br.readLine().split(" ");
            planets.add(new Planet(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
        }

        int result = 0;

        planets.sort((Comparator.comparingInt(o -> o.x)));
        for (int i = 1; i < size; i++) {
            planets.get(i).cost = calculate(planets.get(i).x, planets.get(i - 1).x);
        }
        for (Planet p : planets)
            System.out.println(p);

        System.out.println("--------------------------");

        planets.sort((Comparator.comparingInt(o -> o.y)));
        for (int i = 1; i < size; i++) {
            planets.get(i).cost = calculate(planets.get(i).y, planets.get(i - 1).y) < planets.get(i).cost ? calculate(planets.get(i).y, planets.get(i - 1).y) : planets.get(i).cost;
        }
        for (Planet p : planets)
            System.out.println(p);

        System.out.println("--------------------------");

        planets.sort((Comparator.comparingInt(o -> o.z)));
        for (int i = 1; i < size; i++) {
            planets.get(i).cost = calculate(planets.get(i).z, planets.get(i - 1).z) < planets.get(i).cost ? calculate(planets.get(i).z, planets.get(i - 1).z) : planets.get(i).cost;
        }
        for (Planet p : planets)
            System.out.println(p);

        System.out.println("--------------------------");

        for (int i = 1; i < size; i++)
            result += planets.get(i).cost;

        System.out.println(result);
    }

    private static int calculate(int src, int des) {
        return Math.abs(src - des);
    }

    private static int find(Planet p) {
        if (p.root < 0)
            return p.root;

        return find(planets.get(p.root));
    }

    static class Planet {
        int x;
        int y;
        int z;

        int cost;
        int root;

        Planet(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.cost = Integer.MAX_VALUE;
            this.root = -1;
        }

        @Override
        public String toString() {
            return "Planet{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", cost=" + cost +
                    ", root=" + root +
                    '}';
        }
    }
}
