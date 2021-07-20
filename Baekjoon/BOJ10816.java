import java.io.*;
import java.util.Arrays;

public class BOJ10816 {
    // Caution:
    // 1. Count key - binary search
    // 2. Caching
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            
            int[] cache = new int[20000001]; 
            for (int i = 0; i < cache.length; i++) {
                cache[i] = -1;
            }
            
            int N = Integer.parseInt(br.readLine());
            int[] data = new int[N];

            String[] tmp = br.readLine().split(" ");
            for (int i = 0 ; i < N; i++) {
                data[i] = Integer.parseInt(tmp[i]);
            }
            Arrays.sort(data);
            int M = Integer.parseInt(br.readLine());
            tmp = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                bw.write(numKey(data, Integer.parseInt(tmp[i]), cache) + " ");
            }
            tmp = null;
            bw.flush();
        } catch (IOException e) {
            // do nothing
        }
    }
    static int numKey(int[] data, int key, int[] cache) {
        if (cache[key + 10000000] == -1) {
            cache[key + 10000000] = binSearch(data, key, 0, data.length - 1);
        } 
        return cache[key + 10000000];
    }
    static int binSearch(int[] data, int key, int left, int right) {
        int result = 0;
        if (left <= right) {
            int mid = (left + right) / 2;
                             
            if (data[mid] > key) {
                result += binSearch(data, key, left, mid - 1);
            } else if (data[mid] < key) {
                result += binSearch(data, key, mid + 1, right);
            } else {
                result += binSearch(data, key, mid + 1, right);
                result += binSearch(data, key, left, mid - 1);
                result++;
            }
        }
        return result;
    }
}
