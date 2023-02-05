import java.io.*;
import java.util.*;

public class BOJ2535 {
    private static class Student {
        int nation, id, score;
        Student(int nation, int id, int score) {
            this.nation = nation;
            this.id = id;
            this.score = score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, (s1, s2) -> Integer.compare(s2.score, s1.score));
        int[] nations = new int[n+1];
        int nPrint = 0;
        for (int i = 0; i < n && nPrint < 3; i++) {
            Student s = arr[i];
            if (nations[s.nation] >= 2) continue;
            bw.write(Integer.toString(s.nation));
            bw.write(' ');
            bw.write(Integer.toString(s.id));
            bw.newLine();
            nations[s.nation]++;
            nPrint++;
        }

        bw.flush();
    }
}