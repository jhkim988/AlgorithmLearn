import java.io.*;
import java.util.*;

public class BOJ1327 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(Integer.toString(minSwap(arr, k)));
        bw.flush();
    }
    private static void swap(int[] arr, int idx, int k) {
        for (int i = 0; i < k/2; i++) {
            int tmp = arr[idx + i];
            arr[idx + i] = arr[idx + k - i - 1];
            arr[idx + k - i - 1] = tmp;
        }
    }

    private static boolean isSorted(int[] arr) {
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (prev > arr[i]) return false;
            prev = arr[i];
        }
        return true;
    }

    private static int minSwap(int[] arr, int k) {
        if (isSorted(arr)) {
            return 0;
        }

        Map<String, Integer> visit = new HashMap<>();
        Queue<int[]> que = new ArrayDeque<>();
        que.add(arr);
        visit.put(Arrays.toString(arr), 0);

        while (!que.isEmpty()) {
            int[] crnt = que.poll();
            for (int i = 0; i + k <= arr.length; i++) {
                int[] next = Arrays.copyOf(crnt, arr.length);
                swap(next, i, k);
                if (visit.containsKey(Arrays.toString(next))) continue;
                if (isSorted(next)) {
                    return visit.get(Arrays.toString(crnt)) + 1;
                }
                visit.put(Arrays.toString(next), visit.get(Arrays.toString(crnt)) + 1);
                que.add(next);
            }
        }
        return -1;
    }
}
