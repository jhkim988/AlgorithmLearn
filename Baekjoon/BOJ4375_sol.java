import java.io.*;

public class BOJ4375_sol {
	public static void main(String[] args) throws IOException {
		Mathematics_4375 p4375 = new Mathematics_4375();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = "";
		while ((input = br.readLine()) != null) {
			bw.write(p4375.process(Integer.parseInt(input)) + "\n");
			bw.flush();
		}

	}

	public static class Mathematics_4375 {
		public int process(int n) {
			int num = 0;
			for (int i = 1; i <= n; i++) {
				num = num * 10 + 1;
				num %= n;
				if (num == 0) {
					return i;
				}
			}
			return -1;
		}
	}
}