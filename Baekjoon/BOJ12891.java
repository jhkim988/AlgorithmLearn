import java.io.*;
import java.util.*;

public class BOJ12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        char[] arr = br.readLine().toCharArray();
        int[] required = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            required[i] = Integer.parseInt(st.nextToken());
        }

        int[] status = new int[4];
        for (int i = 0; i < p; i++) {
            add(arr, status, i, 1);
        }
        int num = check(required, status) ? 1 : 0;
        
        for (int i = p; i < s; i++) {
            add(arr, status, i-p, -1);
            add(arr, status, i, 1);
            num += check(required, status) ? 1 : 0;
        }

        bw.write(Integer.toString(num));
        bw.flush();
    }
    static boolean check(int[] required, int[] status) {
        for (int i = 0; i < 4; i++) {
            if (status[i] < required[i]) {
                return false;
            }
        }
        return true;
    }
    static void add(char[] arr, int[] status, int idx, int val) {
        switch (arr[idx]) {
            case 'A':
                status[0] += val;
                break;
            case 'C':
                status[1] += val;
                break;
            case 'G':
                status[2] += val;
                break;
            default:
                status[3] += val;
                break;
        }
    }
}