package Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q_2346 {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        List<Balloon> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        scanner.nextLine();
        int[] val = new int[num];
        for (int i = 0; i < num; i++)
            val[i] = scanner.nextInt();

        for (int i = 0; i < num; i++) {
            list.add(new Balloon(i + 1, val[i]));
        }

        for (int i = 0; i < num; i++) {
            Balloon b = list.get(i);
            if (i == 0) {
                b.setNext(list.get(i + 1));
                b.setPrev(list.get(num - 1));
            } else if (i == num - 1) {
                b.setNext(list.get(0));
                b.setPrev(list.get(i - 1));
            } else {
                b.setNext(list.get(i + 1));
                b.setPrev(list.get(i - 1));
            }
        }

        Balloon b = list.get(0);
        b.isExplosion = true;
        result.append(b.pos).append(" ");
        num--;

        int iterator = b.val;

        while (num != 0) {
            if (iterator > 0) {
                while (iterator > 0) {
                    b = b.next;
                    if (!b.isExplosion)
                        iterator--;
                }
            } else {
                while (iterator < 0) {
                    b = b.prev;
                    if (!b.isExplosion)
                        iterator++;
                }
            }
            b.isExplosion = true;
            iterator = b.val;
            result.append(b.pos).append(" ");
            num--;
        }


        System.out.println(result.toString());
    }

    static class Balloon {
        int pos;
        int val;
        boolean isExplosion;
        Balloon next;
        Balloon prev;

        Balloon(int pos, int val) {
            this.pos = pos;
            this.val = val;
            this.isExplosion = false;
        }

        public void setNext(Balloon next) {
            this.next = next;
        }

        public void setPrev(Balloon prev) {
            this.prev = prev;
        }
    }
}
