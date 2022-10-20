import java.io.*;
import java.util.*;

public class BOJ1174 {
    private static List<Long> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= 9; i++) {
            recur(1, i);
        }
        Collections.sort(arr);
        if (n-1 < arr.size()) {
            bw.write(Long.toString(arr.get(n-1)));
        } else {
            bw.write("-1");
        }
        bw.flush();
    }
    private static void recur(long digit, long crnt) {
        arr.add(crnt);
        long first = crnt/digit;
        for (long i = first+1; i <= 9; i++) {
            recur(digit * 10, i * digit * 10 + crnt);
        }
    }
}
