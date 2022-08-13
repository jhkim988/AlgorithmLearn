import java.io.*;
import java.util.*;

public class BOJ15961 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int max = 0;
    int[] numEat = new int[d+1];
    
    int ptr1 = 0, ptr2 = 0;
    int num = 0;
    for (; ptr2 < k; ptr2++) {
      if (numEat[arr[ptr2]] == 0) num++;
      numEat[arr[ptr2]]++;
    }
    max = num;
    if (numEat[c] == 0 && max < num+1) max = num+1; 
    while (ptr1 < n) {
      numEat[arr[ptr1]]--;
      if (numEat[arr[ptr1]] == 0) num--;
      if (numEat[arr[ptr2]] == 0) num++;
      numEat[arr[ptr2]]++;
      ptr1++;
      ptr2++;
      ptr2 %= n;
      if (numEat[c] == 0 && max < num+1) max = num+1; 
      if (max < num) max = num;
    }

    bw.write(Integer.toString(max));
    bw.flush();
  }
}
