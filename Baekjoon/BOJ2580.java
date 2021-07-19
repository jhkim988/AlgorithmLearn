import java.io.*;

public class BOJ2580 {
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			int[][] data = new int[9][9];
			int numEmpty = 0;
			String[] tmp;
			for (int i = 0; i < 9; i++) {
				tmp = br.readLine().split(" ");
				for(int j = 0; j < 9; j++) {
					data[i][j] = Integer.parseInt(tmp[j]);
					if (data[i][j] == 0) {
						numEmpty++;
					}
				}
			}
			int[] empty = new int[numEmpty];
			int ptrEmpty = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (data[i][j] == 0) {
						empty[ptrEmpty++] = 10 * i + j;
					}
				}
			}

				solve(data, empty);

			} catch (IOException e) {
				// do nothing
			}

	}
	static void solve(int[][] data, int[] empty) {
		int ldpt = empty.length;
		solve(data, 0, ldpt);
	}
	static void solve(int[][] data, int cdpt, int ldpt) {
		
	}
}
