import java.io.*;
import java.util.*;

public class BOJ1083 {
    private static class Pair {
        int idx, val;
        Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = Integer.parseInt(br.readLine());
    
        sort(arr, s);
        
        for (int val : arr) {
            bw.write(Integer.toString(val));
            bw.write(' ');
        }
        bw.flush();
    }

    private static void sort(int[] arr, int s) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.val == b.val) return a.idx - b.idx;
            return b.val - a.val;
        });
        for (int i = 0; i < arr.length; i++) {
            pq.clear();
            for (int j = i; j < arr.length; j++) {
                pq.add(new Pair(j, arr[j]));
            }

            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                if (s < p.idx - i) continue;
                for (int j = p.idx; j > i; j--) {
                    swap(arr, j-1, j);
                    s--;
                }
                break;
            }
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}