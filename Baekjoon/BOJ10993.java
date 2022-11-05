import java.io.*;

public class BOJ10993 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String[] answer = recur(n);
        for (String str : answer) {
            bw.write(str);
            bw.newLine();
        }
        bw.flush();

    }
    private static String[] recur(int n) {
        if (n == 1) {
            return new String[] { "*" };
        }
        if (n == 2) {
            return new String[] {fullChar(5, '*'), " " + fullChar(3, '*'), "  *"};
        }
        String[] prev = recur(n-1);
        int prevHeight = prev.length;
        int prevWidth = prev[(n&1) == 0 ? prev.length-1 : 0].length();
        int height = (prevWidth+1) / 2 * 2 + 1;
        int width = prevWidth + prevHeight*2 + 2;
        String[] ret = new String[height];
        if ((n&1) == 0) {
            ret[0] = fullChar(width, '*');
            int firstEmpty = 1;
            int inLeftEmptyLen = (prevWidth-1)/2;
            int inRightEmptyLen = prevWidth-1;
            for (int i = 0; i < prevHeight; i++, inLeftEmptyLen--, inRightEmptyLen -= 2, firstEmpty++) {
                ret[i+1] = new StringBuilder()
                    .append(fullChar(firstEmpty, ' '))
                    .append('*')
                    .append(fullChar(inLeftEmptyLen, ' '))
                    .append(prev[i])
                    .append(fullChar(inRightEmptyLen, ' '))
                    .append('*')
                    .toString();
            }

            int emptyLen = prevWidth-2;
            for (int i = 0; i+prevHeight+1 < height-1; i++, firstEmpty++, emptyLen -= 2) {
                ret[prevHeight+i+1] = new StringBuilder()
                    .append(fullChar(firstEmpty, ' '))
                    .append('*')
                    .append(fullChar(emptyLen, ' '))
                    .append('*')
                    .toString();
            }
            ret[ret.length-1] = new StringBuilder().append(fullChar(firstEmpty++, ' ')).append('*').toString();
        } else {
            int firstEmpty = width/2;
            ret[0] = new StringBuilder().append(fullChar(firstEmpty--, ' ')).append('*').toString();
            int inEmptyLen = 1;
            for (int i = 1; i < prevHeight; i++, inEmptyLen += 2, firstEmpty--) {
                ret[i] = new StringBuilder()
                    .append(fullChar(firstEmpty, ' '))
                    .append('*')
                    .append(fullChar(inEmptyLen, ' '))
                    .append('*')
                    .toString();
            }

            int inLeftEmptyLen = 0;
            int inRightEmptyLen = 0;
            for (int i = 0; i < prevHeight; i++, inLeftEmptyLen++, inRightEmptyLen += 2, firstEmpty--) {
                ret[prevHeight+i] = new StringBuilder()
                    .append(fullChar(firstEmpty, ' '))
                    .append('*')
                    .append(fullChar(inLeftEmptyLen, ' '))
                    .append(prev[i])
                    .append(fullChar(inRightEmptyLen, ' '))
                    .append('*')
                    .toString();
            }
            ret[height-1] = fullChar(width, '*');
        }

        return ret;
    }

    private static String fullChar(int x, char ch) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < x; i++) {
            ret.append(ch);
        }
        return ret.toString();
    }
}
