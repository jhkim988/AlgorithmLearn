import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ14897 {
	private static int nDiff = 0;
	private static int[] comp;
	private static class Query {
		int lo, hi, id;
		Query(int lo, int hi, int id) {
			this.lo = lo;
			this.hi = hi;
			this.id = id;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		List<Integer> sorted = IntStream.of(arr).sorted().distinct().boxed().collect(Collectors.toList());
		comp = new int[n+1];
		for (int i = 1; i <= n; i++) {
			comp[i] = binSearch(sorted, arr[i]);
		}
		int numQuery = Integer.parseInt(br.readLine());
		ArrayList<Query> query = new ArrayList<>();
		for (int i = 0; i < numQuery; i++) {
			st = new StringTokenizer(br.readLine());
			int lo = Integer.parseInt(st.nextToken());
			int hi = Integer.parseInt(st.nextToken());
			query.add(new Query(lo, hi, i));
		}
		int k = (int) Math.sqrt(n);
		Collections.sort(query, (q1, q2) -> q1.lo / k != q2.lo / k ? Integer.compare(q1.lo / k, q2.lo / k) : Integer.compare(q1.hi, q2.hi));

		int[] answer = new int[numQuery];
		int[] count = new int[sorted.size()];
		Query crnt = query.get(0);
		for (int j = crnt.lo; j <= crnt.hi; j++) {
			put(count, comp[j]);
		}
		answer[crnt.id] = nDiff;
		int lo = crnt.lo, hi = crnt.hi;
		for (int i = 1; i < query.size(); i++) {
			crnt = query.get(i);
			while(lo < crnt.lo) remove(count, comp[lo++]);
			while(lo > crnt.lo) put(count, comp[--lo]);
			while(hi < crnt.hi) put(count, comp[++hi]);
			while(hi > crnt.hi) remove(count, comp[hi--]);
			answer[crnt.id] = nDiff;
		}

		for (int a : answer) {
			bw.write(Integer.toString(a));
			bw.newLine();
		}
		bw.flush();
	}
	private static int binSearch(List<Integer> arr, int key) {
		int lo = -1, hi = arr.size();
		while (lo + 1 < hi) {
			int mid = (lo+hi) >> 1;
			if (arr.get(mid) <= key) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return lo;
	}
	private static void put(int[] map, int key) {
		if (map[key] == 0) nDiff++;
		map[key]++;
	}
	private static void remove(int[] map, int key) {
		map[key]--;
		if (map[key] == 0) nDiff--;
	}
}
