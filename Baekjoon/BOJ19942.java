import java.io.*;
import java.util.*;

public class BOJ19942 {
    private static int n;
    private static Nutrient[] nutrients;
    private static Nutrient standard;
    private static int minCost = Integer.MAX_VALUE;
    private static String minChoose = "9999999";
    private static class Nutrient {
        int p, f, s, v, c;
        
        public Nutrient(int p, int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }

        public boolean isNutrientBigger(Nutrient other) {
            return this.p >= other.p && this.f >= other.f && this.s >= other.s && this.v >= other.v;
        }

        public Nutrient add(Nutrient other) {
            return new Nutrient(this.p + other.p, this.f + other.f, this.s + other.s, this.v + other.v, this.c + other.c);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        nutrients = new Nutrient[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        standard = new Nutrient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nutrients[i] = new Nutrient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        recur(0, new Nutrient(0, 0, 0, 0, 0), 0);

        if (minCost == Integer.MAX_VALUE) {
            bw.write(Integer.toString(-1));
            bw.flush();
            return;
        }

        bw.write(Integer.toString(minCost));
        bw.newLine();
        bw.write(minChoose);
        bw.flush();
    }

    private static void recur(int depth, Nutrient acc, int chooseBit) {
        if (depth >= n) {
            if (!acc.isNutrientBigger(standard)) return;
            String choose = chooseToString(chooseBit);
            if (acc.c < minCost) {
                minCost = acc.c;
                minChoose = choose;
            } else if (acc.c == minCost) {
                if (minChoose.compareTo(choose) <= 0) return;
                minCost = acc.c;
                minChoose = choose;
            }
            return;
        }
        recur(depth + 1, acc.add(nutrients[depth]), chooseBit | (1 << depth));
        recur(depth + 1, acc, chooseBit);
    }

    private static int compareBit(int bit1, int bit2) {
        for (int bit = 1; bit <= bit1 || bit <= bit2; bit <<= 1) {
            if ((bit1 & bit) == (bit2 & bit)) continue; 
            return Integer.compare((bit1 & bit), (bit2 & bit));
        }
        return 0;
    }

    private static String chooseToString(int bit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1, idx = 1; i <= bit; i <<= 1, idx++) {
            if ((bit & i) == 0) continue;
            sb.append(idx).append(" ");
        }
        return sb.toString();
    }
}
