import java.io.*;
import java.util.*;

public class BOJ9711 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int nTest = Integer.parseInt(br.readLine());
        for (int i = 1; i <= nTest; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());
            bw.write("Case #");
            bw.write(Integer.toString(i));
            bw.write(": ");
            bw.write(Long.toString(fibonacci(p,q)));
            bw.newLine();    
        }
        bw.flush();
    } 
    private static long fibonacci(long p, long q) {
        if (p <= 2) return 1%q;
        if (p == 3) return 2%q;
        long[][] matrix = {{1, 1}, {1, 0}};
        matrix = power(matrix, p-2, q);
        return (matrix[0][0] + matrix[0][1])%q;
    }
    private static long[][] power(long[][] matrix, long p, long q) {
        if (p == 0) return new long[][] {{ 1, 0 }, {0, 1}};
        if (p == 1) return matrix;
        long[][] temp = power(matrix, p/2, q);
        temp = multiply(temp, temp, q);
        if (p % 2 == 0) return temp;
        return multiply(temp, matrix, q);
    }

    private static long[][] multiply(long[][] a, long[][] b, long q) {
        long[][] temp = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    temp[i][j] += a[i][k] * b[k][j] % q;
                    temp[i][j] %= q;
                }
            }
        }
        return temp;
    }
}
