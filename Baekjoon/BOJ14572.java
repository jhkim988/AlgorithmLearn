import java.io.*;
import java.util.*;

public class BOJ14572 {
    
    private static class Student {
        int power;
        List<Integer> algorithms = new ArrayList<>();
        Student(int power) {
            this.power = power;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int numAlgorithm = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            Student s = new Student(power);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numAlgorithm; j++) {
                s.algorithms.add(Integer.parseInt(st.nextToken()));
            }
            students[i] = s;
        }

        Arrays.sort(students, (s1, s2) -> Integer.compare(s1.power, s2.power));
        
        int[] set = new int[31];

        int lo = 0, hi = 0;
        int num = 0, numUnion = 0, numIntersection = 0, e = 0;
        int max = 0;
        while (lo <= hi && lo < n && hi < n) {
            while (hi < n && students[hi].power - students[lo].power <= d) {
                for (int algo: students[hi].algorithms) {
                    set[algo]++;
                }
                hi++;
            }
            num = (hi-lo);
            numUnion = 0;
            numIntersection = 0;
            for (int el : set) {
                if (el == num) numIntersection++;
                if (el > 0) numUnion++;
            }
            e = (numUnion - numIntersection)*num;
            max = Integer.max(max, e);
            for (int algo: students[lo].algorithms) {
                set[algo]--;
            }
            lo++;
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
