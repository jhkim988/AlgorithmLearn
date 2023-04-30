import java.io.*;
import java.util.*;

public class BOJ1331 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] position = new String[36];
        for (int i = 0; i < position.length; i++) {
            position[i] = br.readLine();
        }

        bw.write(isValid(position) ? "Valid" : "Invalid");
        bw.flush();
    }

    private static boolean isValid(String[] position) {
        Set<String> visit = new HashSet<>();
        String prev = position[0];
        visit.add(prev);
        for (int i = 1; i < position.length; i++) {
            String crnt = position[i];
            if (visit.contains(crnt)) return false;
            if (!isValidMove(prev, crnt)) return false; 
            visit.add(crnt);
            prev = crnt;
        }
        return isValidMove(position[position.length-1], position[0]);
    }

    private static boolean isValidMove(String prev, String next) {
        int prevX = prev.charAt(0) - 'A';
        int prevY = prev.charAt(1) - '1';
        int nextX = next.charAt(0) - 'A';
        int nextY = next.charAt(1) - '1';
        int diffX = Math.abs(prevX - nextX);
        int diffY = Math.abs(prevY - nextY);
        if (diffX == 1 && diffY == 2) return true;
        if (diffX == 2 && diffY == 1) return true;
        return false;
    }
}
