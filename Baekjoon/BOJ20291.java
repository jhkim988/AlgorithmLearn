import java.io.*;
import java.util.*;

public class BOJ20291 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> countMap = new HashMap<>();
        while (n-- > 0) {
            String input = br.readLine();
            int index = input.indexOf(".");
            countMap.merge(input.substring(index+1), 1, (a, b) -> a+1);
        }

        countMap.entrySet().stream()
            .sorted((entry1, entry2) ->  entry1.getKey().compareTo(entry2.getKey()))
            .forEach(x -> write(x));
        bw.flush();
    }

    private static void write(Map.Entry<String, Integer> x) {
        try {
            bw.write(x.getKey());
            bw.write(' ');
            bw.write(Integer.toString(x.getValue()));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
