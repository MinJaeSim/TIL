package Algorithm.bfs;

import java.util.*;

class Q_1963 {
    private static List<Integer> primeNumber = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        String[] value = new String[num];
        for (int i = 0; i < num; i++) {
            value[i] = scanner.nextLine();
        }

        int[] val = new int[num * 2];
        for (int i = 0; i < num; i++) {
            val[i * 2] = Integer.parseInt(value[i].split(" ")[0]);
            val[i * 2 + 1] = Integer.parseInt(value[i].split(" ")[1]);
        }

        for (int i = 1000; i < 9999; i++)
            isPrime(i);

        int[][] graph = new int[primeNumber.size()][primeNumber.size()];
        makeGraph(graph);

        for (int i = 0; i < num; i++)
            bfs2(primeNumber.indexOf(val[i * 2]), primeNumber.indexOf(val[i * 2 + 1]), graph);
    }


    public static void bfs2(int start, int target, int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        int[] prevNode = new int[primeNumber.size()];
        int[] prevNum = new int[primeNumber.size()];
        for (int i = 0; i < primeNumber.size(); i++) {
            prevNum[i] = -1;
            prevNode[i] = -1;
        }

        q.offer(start);
        prevNode[start] = -1;
        prevNum[start] = 0;

        int temp;
        while (!q.isEmpty()) {
            temp = q.poll();
            for (int j = 0; j < primeNumber.size(); j++) {
                if (graph[temp][j] == 1 && prevNum[j] == -1) {
                    q.offer(j);
                    prevNode[j] = temp;
                    prevNum[j] = prevNum[temp] + 1;
                }
            }
        }

        System.out.println(prevNum[target]);
    }

    public static void makeGraph(int[][] g) {
        for (int i = 0; i < primeNumber.size(); i++) {
            for (int j = i + 1; j < primeNumber.size(); j++) {
                String n1 = String.valueOf(primeNumber.get(i));
                String n2 = String.valueOf(primeNumber.get(j));
                int num = 0;
                for (int k = 0; k < 4; k++) {
                    num = n1.charAt(k) == n2.charAt(k) ? ++num : num;
                }

                if (num >= 3) {
                    g[i][j] = g[j][i] = 1;
                }
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1)
            return false;

        if (num % 2 == 0)
            return num == 2;


        for (int i = 3; i * i <= num; i++)
            if (num % i == 0)
                return false;

        primeNumber.add(num);
        return true;
    }
}