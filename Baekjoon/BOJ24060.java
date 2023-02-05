import java.io.*;
import java.util.*;

public class BOJ24060 {
    private static int nChange = 0;
    private static int val = -1;
    private static int[] aux;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        aux = new int[n];
        mergeSort(arr, 0, n-1, k);
    
        bw.write(Integer.toString(val));
        bw.flush();
    }

    private static void mergeSort(int[] arr, int start, int end, int k) {
        if (start == end) {
            return;
        }
        int mid = (start + end) >> 1;
        mergeSort(arr, start, mid, k);
        mergeSort(arr, mid + 1, end, k);
        System.arraycopy(arr, start, aux, 0, mid-start+1);
        int ptr = start, ptrAux = 0, ptrAfter = mid+1;
        while (ptrAux <= mid-start && ptrAfter <= end) {
            if (aux[ptrAux] <= arr[ptrAfter]) {
                arr[ptr] = aux[ptrAux++];
            } else {
                arr[ptr] = arr[ptrAfter++];
            }
            nChange++;
            if (nChange == k) val = arr[ptr];
            ptr++;
        }

        while (ptrAux <= mid-start) {
            arr[ptr] = aux[ptrAux++];
            nChange++;
            if (nChange == k) val = arr[ptr];
            ptr++;
        }

        while (ptrAfter <= end) {
            arr[ptr] = arr[ptrAfter++];
            nChange++;
            if (nChange == k) val = arr[ptr];
            ptr++;
        }
    }
}
