import java.io.*;
import java.util.*;

public class BOJ2800 {
    private static char[] str;
    private static boolean isFirst = true;
    private static int[] pcode;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static HashSet<Integer> hs = new HashSet<>();
    private static TreeSet<String> store = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        str = br.readLine().toCharArray();
        pcode = pcode();
        
        recur(0);
        for (String str : store) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();
    }
    private static void recur(int depth) throws IOException {
        if (depth >= str.length) {
            if (isFirst) {
                isFirst = false;
                return;
            }
            store();
            return;
        }
        if (str[depth] == '(') {
            hs.add(pcode[depth]);
            recur(depth+1);
            hs.remove(pcode[depth]);
            recur(depth+1);
        } else {
            recur(depth+1);
        }
    }
    private static int[] pcode() {
        Stack<Integer> nstk = new Stack<>();
        int[] pcode = new int[str.length];
        int numP = 1;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                nstk.push(numP);
                pcode[i] = numP++;
            } else if (str[i] == ')') {
                pcode[i] = nstk.pop();
            }
        }
        return pcode;
    }

    private static void store() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            if ((str[i] == '(' || str[i] == ')')) {
                if (hs.contains(pcode[i])) sb.append(str[i]);
            } else {
                sb.append(str[i]);
            }
        }
        store.add(sb.toString());
    }
}