import java.io.*;
import java.util.*;

public class BOJ1034 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nRow = Integer.parseInt(st.nextToken());
        int nCol = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();        
        for (int i = 0; i < nRow; i++) {
            String str = br.readLine();
            if (map.containsKey(str)) map.put(str, map.get(str)+1);
            else map.put(str, 1);
        }
        int k = Integer.parseInt(br.readLine());

        int answer = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int numZero = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') numZero++;
            }
            if (k < numZero) continue;
            if ((numZero % 2) != (k % 2)) continue;
            answer = Integer.max(answer, entry.getValue());
        }

        bw.write(Integer.toString(answer));
        bw.flush();
    }
}