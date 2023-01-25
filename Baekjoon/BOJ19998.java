import java.io.*;
import java.util.*;

public class BOJ19998 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
    }
    private static int[] permutation() {
        int[] ret = new int[9];
        for (int i = 0; i < 9; i++) {
            ret[i] = i+1;
        }
        for (int i = 9; i > 0; i--) {
            swap(ret, (int) (Math.random()*i), i-1);
        }
    }
}
