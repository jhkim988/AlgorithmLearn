import java.io.*;
import java.util.*;

public class BOJ1394 {
    private static final int d = 900_528;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] charSet = br.readLine().toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < charSet.length; i++) {
            charMap.put(charSet[i], i+1);
        }
        
        int answer = 0;
        int len = charSet.length;
        char[] cypher = br.readLine().toCharArray();
        for (int i = 0; i < cypher.length; i++) {
            answer *= len; answer %= d;
            answer += charMap.get(cypher[i]); answer %= d;
        }
        bw.write(Integer.toString(answer));
        bw.flush();
    }
}