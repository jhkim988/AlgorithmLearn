import java.io.*;
import java.util.*;

public class BOJ16120 {
    private static final char[] pat = { 'P', 'P', 'A', 'P'};
    private static final Stack<Character> tmpStack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] charArr = br.readLine().toCharArray();
        Stack<Character> stk = new Stack<>();
        for (char ch : charArr) {
            stk.push(ch);
            if (stk.size() >= 4) check(stk);
        }

        bw.write(stk.size() == 1 && stk.peek() == 'P' ? "PPAP" : "NP");
        bw.flush();
    }
    private static void check(Stack<Character> stk) {
        tmpStack.clear();
        boolean check = true;
        for (int i = 3; i >= 0; i--) {
            char pop = stk.pop();
            check = check && pop == pat[i];
            tmpStack.push(pop);
        }

        if (check) {
            stk.push('P');
        } else {
            while (!tmpStack.isEmpty()) stk.push(tmpStack.pop());
        }
    }
}
