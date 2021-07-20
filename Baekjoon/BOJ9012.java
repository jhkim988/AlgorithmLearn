import java.io.*;
import java.util.*;

public class BOJ9012 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            int numTest = Integer.parseInt(br.readLine());
            for (int i = 0 ; i < numTest; i++) {
                if (isVPS(br.readLine())) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
            bw.flush();
        } catch (IOException e) {
            // do nothing
        }
    }
    static boolean isVPS(String str) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ')') {
                if (stk.isEmpty()) {
                    return false;
                }
                char checker = stk.pop();
                if (checker == '(') {
                    continue;
                } else {
                    return false;
                }
            } else {
                stk.push(str.charAt(i));
            }
        }
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }
}
