import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class BOJ2164 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();
        scn.close();
        Queue<Integer> que = new LinkedList<>();
        
        // initialize
        for (int i = 1; i <= N; i++) {
            que.add(i);
        }

        // process
        if (N == 1) {
            System.out.println(1);
            return;
        }
        
        while (true) {
            que.poll();
            int reInput = que.poll();
            if (que.isEmpty()) {
                System.out.println(reInput);
                break;
            }
            que.add(reInput);
        }
    }
}
