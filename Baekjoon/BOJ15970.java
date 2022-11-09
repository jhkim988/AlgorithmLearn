import java.io.*;
import java.util.*;

public class BOJ15970 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer, List<Integer>> map = new HashMap<>(); // <color, pos>
        
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            if (map.containsKey(color)) {
                map.get(color).add(pos);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pos);
                map.put(color, list);
            }
        }

        map.forEach((color, list) -> Collections.sort(list));
        long answer = map.values()
                            .stream()
                            .map(x -> answerForList(x))
                            .reduce((acc, x) -> acc + x)
                            .orElse(0L);
        bw.write(Long.toString(answer));
        bw.flush();
    }
    private static long answerForList(List<Integer> list) {
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long left = i != 0 ? list.get(i) - list.get(i-1) : Long.MAX_VALUE;
            long right = i+1 < list.size() ? list.get(i+1) - list.get(i) : Long.MAX_VALUE;
            sum += Long.min(left, right);
        }
        return sum;
    }
}