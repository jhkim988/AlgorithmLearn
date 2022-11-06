import java.io.*;
import java.util.*;

public class BOJ1028 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nrow = Integer.parseInt(st.nextToken());
        int ncol = Integer.parseInt(st.nextToken());
        
        char[][] arr = new char[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        //   leftUp = /\ = rightUp
        // leftDown = \/ = rightDown 
    
        int[][] leftUp = new int[nrow][ncol];
        int[][] rightUp = new int[nrow][ncol];
        int[][] leftDown = new int[nrow][ncol];
        int[][] rightDown = new int[nrow][ncol];
    
        for (int i = 0; i < ncol; i++) {
            leftUp[nrow-1][i] = rightUp[nrow-1][i] = arr[nrow-1][i] == '1' ? 1 : 0;
        }
        for (int i = nrow-2; i >= 0; i--) {
            for (int j = 0; j < ncol; j++) {
                if (arr[i][j] == '0') continue;
                leftUp[i][j] = 1; 
                if (j > 0) leftUp[i][j] += leftUp[i+1][j-1];
            }
            for (int j = 0; j < ncol; j++) {
                if (arr[i][j] == '0') continue;
                rightUp[i][j] = 1; 
                if (j+1 < ncol) rightUp[i][j] += rightUp[i+1][j+1];
            }
        }

        for (int i = 0; i < ncol; i++) {
            leftDown[0][i] = rightDown[0][i] = arr[0][i] == '1' ? 1 : 0;
        }
        for (int i = 1; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (arr[i][j] == '0') continue;
                leftDown[i][j] = 1; 
                if (j > 0) leftDown[i][j] += leftDown[i-1][j-1];
            }
            for (int j = 0; j < ncol; j++) {
                if (arr[i][j] == '0') continue;
                rightDown[i][j] = 1; 
                if (j+1 < ncol) rightDown[i][j] += rightDown[i-1][j+1];
            }
        }

        int max = 0;
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                int minLen = Integer.min(leftUp[i][j], rightUp[i][j]);
                if (minLen <= max) continue;
                int bottom = Integer.max(i + (max-1)*2, i);
                for (int k = bottom; k <= Integer.min(i+(minLen-1)*2, nrow-1); k += 2) {
                    int bottomMinLen = Integer.min(leftDown[k][j], rightDown[k][j]);
                    if (bottomMinLen < (k-i)/2+1) continue;
                    max = Integer.max(max, (k-i)/2+1);
                }
            }
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
