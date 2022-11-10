import java.io.*;
import java.util.*;

public class BOJ4659 {
    private static Set<Character> aeiou = new HashSet<>(Arrays.asList(new Character[] { 'a', 'e', 'i', 'o', 'u' }));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        while (!"end".equals(input)) {
            boolean result = test(input);
            bw.write(new StringBuilder("<")
            .append(input).append("> is ")
            .append(result ? "acceptable." : "not acceptable.")
            .toString());
            bw.newLine();
            input = br.readLine();
        }
        bw.flush();
    }
    private static boolean test(String input) {
        return atMostOneAEIOU(input) && No3Type(input) && No2Same(input);
    }
    private static boolean atMostOneAEIOU(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (aeiou.contains((input.charAt(i)))) return true;
        }
        return false;
    }
    private static boolean No3Type(String input) {
        int a = 0, b = 0;
        int lo = 0, hi = 0;
        for (; hi < Integer.min(input.length(), 3); hi++) {
            char ch = input.charAt(hi);
            if (aeiou.contains(ch)) a++;
            else b++;
        }
        if (a >= 3 || b >= 3) return false;
        for (; hi < input.length(); lo++, hi++) {
            if (aeiou.contains(input.charAt(lo))) a--;
            else b--;
            if (aeiou.contains(input.charAt(hi))) a++;
            else b++;
            if (a >= 3 || b >= 3) return false;
        }
        return true;
    }
    private static boolean No2Same(String input) {
        char ch = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char next = input.charAt(i);
            if (next == 'e' || next == 'o') {
                ch = next;
                continue;
            }
            if (ch == next) return false;
            ch = next;
        }
        return true;
    }
}