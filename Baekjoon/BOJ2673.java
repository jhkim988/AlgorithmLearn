import java.io.*;
import java.util.*;

public class BOJ2673 {
    private static int n;
    private static boolean[][] cross;
    private static class Chord {
        int start, end;
        Chord(int start, int end) {
            if (start < end) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        Chord[] input = new Chord[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            input[i] = new Chord(start, end);
        }
        Arrays.sort(input, (c1, c2) -> Integer.compare(c1.start, c2.start));

        cross = new boolean[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(cross[i], true);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (!cross(input, i, j)) cross[i][j] = cross[j][i] = false;
            }
        }
        boolean[] stat= new boolean[n];
        int answer = recur(input, 0, stat);
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int recur(Chord[] arr, int depth, boolean[] stat) {
        if (depth >= arr.length) {
            int num = 0;
            for (int i = 0; i < stat.length; i++) {
                if (stat[i]) num++;
            }
            return num;
        }

        // include
        boolean flag = false;
        for (int i = 0; i < stat.length; i++) {
            if (stat[i] && cross[i][depth]) flag = true;
        }

        stat[depth] = true;
        int include = flag ? 0 : recur(arr, depth+1, stat);
        stat[depth] = false;    

        // exclude
        int exclude = recur(arr, depth+1, stat);
        return Integer.max(include, exclude);
    }

    private static boolean cross(Chord[] arr, int a, int b) {
        if (a == b) return true;
        if (b < a) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        return (arr[b].start <= arr[a].end) && (arr[b].end >= arr[a].end || arr[b].end <= arr[a].start);
    }
}