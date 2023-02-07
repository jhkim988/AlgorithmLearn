import java.io.*;

public class BOJ5555 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] pat = br.readLine().toCharArray();
        int[] kmpPat = pi(pat);
        int answer = 0;
        int numTest = Integer.parseInt(br.readLine());
        for (int i = 0; i < numTest; i++) {
            String test = br.readLine();
            char[] str = (test + test).toCharArray();
            if (match(str, pat, kmpPat)) {
                answer++;
            }
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static int[] pi(char[] pat) {
        int[] pi = new int[pat.length];
        int j = 0;
        for (int i = 1; i < pat.length; i++) {
            while (j > 0 && pat[i] != pat[j]) {
                j = pi[j - 1];
            }
            if (pat[i] == pat[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    private static boolean match(char[] str, char[] pat, int[] pi) {
        int j = 0;
        for (int i = 0; i < str.length; i++) {
            while (j > 0 && str[i] != pat[j]) {
                j = pi[j - 1];
            }
            if (str[i] == pat[j]) {
                if (j == pat.length - 1) {
                    return true;
                } else {
                    j++;
                }
            }

        }
        return false;
    }
}