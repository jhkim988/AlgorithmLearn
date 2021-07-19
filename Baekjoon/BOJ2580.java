import java.io.*;

public class BOJ2580 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean flag = false;
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			

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
						empty[ptrEmpty++] = 9 * i + j;
					}
				}
			}

				solve(data, empty);
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// do nothing
			}

	}
	static void solve(int[][] data, int[] empty) {
		int ldpt = empty.length;
		solve(data, empty, 0, ldpt);
	}
	static void solve(int[][] data, int[] empty, int cdpt, int ldpt) {
		if (flag) {
			return;
		}
		if (cdpt < ldpt) {
			boolean[] cand = candidate(data, empty[cdpt]);
			for (int i = 0 ; i < 9; i++) {
				if (!cand[i]) {
					continue;
				}
				data[empty[cdpt]/9][empty[cdpt]%9] = (i + 1);
				solve(data, empty, cdpt + 1, ldpt);
				data[empty[cdpt]/9][empty[cdpt]%9] = 0;				
			}
		} else {
			try {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						bw.write(data[i][j] + " ");
					}
					bw.write("\n");
				}
			} catch (IOException e) {
				// do nothing
			}
			flag = true;
		}
	}
	static boolean[] candidate(int[][] data, int cordi) {
		boolean[] cand = new boolean[9];
		for (int i = 0; i < 9; i++) {
			cand[i] = true;
		}
		int x = cordi / 9;
		int y = cordi % 9;
		for (int i = 0; i < 9; i++) {
			if (data[x][i] != 0) {
				cand[data[x][i] - 1] = false;
			}
			if (data[i][y] != 0) {
				cand[data[i][y] - 1] = false;
			}
		}
		int xtmp = (x / 3) * 3;
		int ytmp = (y / 3) * 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (data[i + xtmp][j + ytmp] != 0) {
					cand[data[i + xtmp][j + ytmp] - 1] = false;
				}				
			}
		}
		return cand;
	}
}
