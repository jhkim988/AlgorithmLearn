import java.util.*;
import java.io.*;

public class BOJ11866 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = scn.nextInt();
        int K = scn.nextInt();
        scn.close();
    
        Queue<Integer> que = new LinkedList<>();    
        
        // initialize
        for (int i = 1; i <= N; i++) {
            que.add(i);
        }

        // process
        try {
            bw.write("<");
            while (que.size() > 1) {
                for (int i = 0; i < K - 1; i++) {
                    que.add(que.poll());
                }
                bw.write(que.poll() + ", ");
            }
            bw.write(que.poll() + ">");
            bw.flush();
        } catch (IOException e) {
            // do nothing;
        }
    }
}
