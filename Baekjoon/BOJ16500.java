import java.io.*;

public class BOJ16500 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] str = br.readLine().toCharArray();
        
        int n = Integer.parseInt(br.readLine());
        char[][] dictionary = new char[n][];
        for (int i = 0; i < n; i++) {
            dictionary[i] = br.readLine().toCharArray();
        }

        int[] dp = new int[str.length+1];
        dp[str.length] = 1;
        for (int i = str.length-1; i >= 0; i--) {
            for (int x = 0; x < n; x++) {
                if (str.length-i < dictionary[x].length) continue;
                boolean allMatch = true;
                for (int j = 0; j < dictionary[x].length; j++) {
                    allMatch = allMatch && dictionary[x][j] == str[j+i];
                }
                if (allMatch && (dp[i+dictionary[x].length] == 1)) dp[i] = 1;
            }
        }

        bw.write(Integer.toString(dp[0]));
        bw.flush();
    }
}