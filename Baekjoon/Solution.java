import java.io.*;
import java.util.*;

public class Solution {
    private static int d = 1_000_000_009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int nTest = Integer.parseInt(br.readLine());
        for (int test = 1; test <= nTest; test++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[] answer = answer(arr);
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (sum + answer[i]) % d;
            }
            bw.write("#" + test + " " + sum);
            bw.newLine();
        }
        bw.flush();
    }

    private static int[] answer(int[] arr) {
        int n = arr.length;
        int[] psum = new int[n];
        int[] ret = new int[n];

        psum[0] = arr[0]; // arr.length > 0
        for (int i = 1; i < n; i++) {
            psum[i] = psum[i-1] + arr[i];
        }

        int[] rightMax = new int[n];
        int[] rightMaxExceptOne = new int[n];
        int min = Integer.min(0, arr[n-1]);
        rightMax[n-1] = psum[n-1];
        rightMaxExceptOne[n-1] = rightMax[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Integer.max(rightMax[i+1], psum[i]);
            rightMaxExceptOne[i] = rightMax[i] - min;
            min = Integer.min(min, arr[i]);
        }

        int[] leftMin = new int[n];
        int[] leftMinExceptOne = new int[n];
        min = Integer.min(0, arr[0]);
        leftMin[0] = psum[0];
        leftMinExceptOne[0] = psum[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Integer.min(leftMin[i-1], psum[i]);
            leftMinExceptOne[i] = leftMin[i] - min;
            min = Integer.min(min, arr[i]);
        }
        System.out.println(Arrays.toString(rightMaxExceptOne));
        for (int i = 0; i < n; i++) {
            int leftMinVal = i > 0 ? leftMin[i-1] : 0;
            int leftMinExceptOneVal = i > 0 ? leftMinExceptOne[i-1] : 0;
            leftMinVal = Integer.max(0, leftMinVal);
            ret[i] = Integer.max(rightMax[i] - leftMinVal, Integer.max(rightMaxExceptOne[i] - leftMinVal, rightMax[i] - leftMinExceptOneVal));
        }
        return ret;
    }
}