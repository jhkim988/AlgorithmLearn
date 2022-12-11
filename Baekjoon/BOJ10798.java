import java.io.*;

public class BOJ10798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = 0;
        char[][] inputs = new char[5][];
        for (int i = 0; i < 5; i++) {
            inputs[i] = br.readLine().toCharArray();
            if (len < inputs[i].length) len = inputs[i].length;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < inputs[j].length) {
                    sb.append(inputs[j][i]);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}

/*
 * 행복하여라, 슬퍼하는 사람들!
 * 그들은 위로를 받을 것이다.
 */
