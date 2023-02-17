import java.io.*;

public class BOJ2877 {
    private static class Solution {
        private int n = 0, sum = 0, add = 2, len = 0;
        
        public Solution(int n) {
            len(n);
            this.n = n;
        }

        private int len(int n) {
            sum = 0;
            add = 2;
            len = 0;
            while (sum < n) {
                sum += add;
                add <<= 1;
                len++;
            }
            return len;
        }

        public String answer() {
            int val = n - sum/2;
            char[] arr = new char[len];
            for (int i = 0; i < len; i++) {
                if ((val & (1<<i)) == 0) {
                    arr[len-i-1] = '4';
                } else {
                    arr[len-i-1] = '7';
                }
            }
            return String.valueOf(arr);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Solution solution = new Solution(n);
        bw.write(solution.answer());
        bw.flush();
    }
}