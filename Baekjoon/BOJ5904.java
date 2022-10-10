import java.io.*;

public class BOJ5904 {
    static long[] seq;
    static long seqLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        init();
        bw.write(answer(n));
        bw.flush();
    }
    static void init() {
        seq = new long[28];
        seqLen = 3;
        int seqIdx = 0;
        seq[0] = 3;
        while (seqLen <= 1_000_000_000L) {
            seqIdx++;
            seqLen = seqLen*2 + seqIdx + 3;
            seq[seqIdx] = seqLen;
        }
    }
    static char answer(long n) {
        int seqIdx = findIdx(n);
        while (seqIdx > 0) {
            if (seq[seqIdx-1] + seqIdx + 3 < n) {
                n -= seq[seqIdx-1] + seqIdx + 3;
                seqIdx--;
            } else if (seq[seqIdx-1] < n) {
                n -= seq[seqIdx-1];
                if (n == 1) return 'm';
                return 'o';
            } else {
                seqIdx = findIdx(n);
            }
        }
        if (n == 1) return 'm';
        return 'o';
    }
    static int findIdx(long n) {
        for (int i = 0; i < seq.length; i++) {
            if (n <= seq[i]) return i;
        }
        return -1;
    }
}
