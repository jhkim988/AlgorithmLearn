import java.io.*;
import java.util.*;

public class BOJ25206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Double> map = new HashMap<>();
        map.put("A+", 4.5);
        map.put("A0", 4.0);
        map.put("B+", 3.5);
        map.put("B0", 3.0);
        map.put("C+", 2.5);
        map.put("C0", 2.0);
        map.put("D+", 1.5);
        map.put("D0", 1.0);
        map.put("F", 0.0);

        String input = null;
        double totalScore = 0;
        int totalUnit = 0;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            st.nextToken();
            int unit = (int) Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if (grade.charAt(0) == 'P') continue;
            totalScore += unit * map.get(grade).doubleValue();
            totalUnit += unit;
        }

        bw.write(Double.toString(totalScore / totalUnit));
        bw.flush();
    }
}