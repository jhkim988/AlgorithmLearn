import java.io.*;

public class BOJ1259 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String input = br.readLine();
			while (Integer.parseInt(input) != 0) {
				solve(input);
				input = br.readLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// do nothing
		}
	}
	static void solve(String str) {
		try {
			int len = str.length();
			for(int i = 0; i < len / 2; i++) {
				if (str.charAt(i) != str.charAt(len - i - 1)) {
					bw.write("no\n");
					return;
				}
			}
			bw.write("yes\n");
		} catch (IOException e) {

		}
	}
}
