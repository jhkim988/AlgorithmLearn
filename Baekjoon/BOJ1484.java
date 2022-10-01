import java.io.*;
import java.util.*;

public class BOJ1484 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int g = Integer.parseInt(br.readLine());
        ArrayList<Long> answers = useTwoPointer(g);
        if (answers.isEmpty()) {
            bw.write("-1\n");
        } else {
            for (long ans : answers) {
                bw.write(Long.toString(ans));
                bw.newLine();
            }
        }
        bw.flush();
    }
    static ArrayList<Long> useBinarySearch(int g) {
        ArrayList<Long> answers = new ArrayList<>();
        for (long i = 1; i <= g/2; i++) {
            long sqrt = sqrt(i*i+g);
            if (sqrt == -1) continue;
            answers.add(sqrt);
        }
        return answers;
    }
    static ArrayList<Long> useTwoPointer(int g) {
        ArrayList<Long> answers = new ArrayList<>();
        long ptr1 = 1, ptr2 = 2;
        while (ptr1 <= g/2) {
            if (ptr1*ptr1 + g < ptr2*ptr2) {
                ptr1++;
            } else if (ptr1*ptr1 + g > ptr2*ptr2) {
                ptr2++;
            } else {
                answers.add(ptr2);
                ptr1++;
                ptr2++;
            }
        }
        return answers;
    }

    // use BinarySearch
    static long sqrt(long x) {
        long lo = 0, hi = x+1;
        while (lo+1 < hi) {
            long mid = (lo+hi) >> 1;
            if (mid*mid < x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi*hi == x ? hi : -1;
    }
}