import java.io.*;
import java.util.*;

public class BOJ2910 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> xtoFreq = new HashMap<>();
        TreeMap<Integer, HashSet<Integer>> freqToX = new TreeMap<>((a, b) -> b-a);
        HashMap<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            freqToX.put(i, new HashSet<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            arr.add(x);            
            if (!index.containsKey(x)) {
                index.put(x, i);
            }
            if (!xtoFreq.containsKey(x)) {
                xtoFreq.put(x, 1);
                freqToX.get(1).add(x);
            } else {
                freqToX.get(xtoFreq.get(x)).remove(x);
                xtoFreq.put(x, xtoFreq.get(x)+1);
                freqToX.get(xtoFreq.get(x)).add(x);
            }
        }
        Collections.sort(arr, (a, b) -> {
            if (xtoFreq.get(a) == xtoFreq.get(b)) {
                return index.get(a) - index.get(b);
            }
            return xtoFreq.get(b) - xtoFreq.get(a);
        });

        StringBuilder sb = new StringBuilder();
        arr.forEach(x -> sb.append(x).append(' '));
        bw.write(sb.toString());
        bw.flush();
    }
}
