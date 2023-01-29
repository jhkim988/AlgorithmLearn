import java.io.*;
import java.util.*;

public class BOJ2336 {
	private static final int nTest = 3;
	private static class Pair {
		int id;
		int[] test = new int[nTest];
		Pair(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return id + ":" + Arrays.toString(test);
		}
	}
	private static class SegTree {
		private static final int INF = Integer.MAX_VALUE >> 2;
		private int n;
		private int[] tree;
		SegTree(int n) {
			this.n = n;
			int treeSize = 1;
			while (treeSize < n) treeSize <<= 1;
			treeSize <<= 1;
			tree = new int[treeSize];
			init(1, 0, n-1);
		}

		void init(int node, int start, int end) {
			if (start == end) {
				tree[node] = INF;
				return;
			}
			int mid = (start + end) >> 1;
			init(node<<1, start, mid);
			init(node<<1|1, mid+1, end);
			tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
		}

		void update(int idx, int val) {
			update(1, 0, n-1, idx, val);
		}

		int get(int left, int right) {
			return get(1, 0, n-1, left, right);
		}

		private void update(int node, int start, int end, int idx, int val) {
			if (idx < start || end < idx) return;
			if (start == end) {
				tree[node] = val;
				return;
			}
			int mid = (start + end) >> 1;
			update(node<<1, start, mid, idx, val);
			update(node<<1|1, mid+1, end, idx, val);
			tree[node] = Integer.min(tree[node<<1], tree[node<<1|1]);
		}

		private int get(int node, int start, int end, int left, int right) {
			if (right < start || end < left) return INF;
			if (left <= start && end <= right) return tree[node];
			int mid = (start + end) >> 1;
			return Integer.min(get(node<<1, start, mid, left, right), get(node<<1|1, mid+1, end, left, right));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		List<Pair> test = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			test.add(new Pair(i));
		}
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int student = Integer.parseInt(st.nextToken());
				test.get(student-1).test[i] = j;
			}
		}
		Collections.sort(test, (p1, p2) -> Integer.compare(p1.test[0], p2.test[0]));

		int nAmazing = 0;
		SegTree seg = new SegTree(n+1);
		for (Pair p : test) {
			if (seg.get(1, p.test[1]) > p.test[2]) {
				nAmazing++;
			}
			seg.update(p.test[1], p.test[2]);
		}
		bw.write(Integer.toString(nAmazing));
		bw.flush();
	}
}