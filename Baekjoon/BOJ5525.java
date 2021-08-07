import java.io.*;

public class BOJ5525 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    char[] S = br.readLine().toCharArray();

    int ptr1 = 0;
    int ptr2 = 0;
    long count = 0;
    boolean prevSuccess = false;
    for (ptr1 = 0; ptr1 < M; ptr1++) {
      if (S[ptr1] != 'I') {
        continue;
      }
      boolean check = true;
      if (!prevSuccess) {
        ptr2 = ptr1;        
        for (int i = 0; i < 2 * N + 1 && ptr2 < M; i++) {
          if (check) {
            if (S[ptr2] == 'I') {
              ptr2++;
              check = false;
            } else {
              ptr1 = ptr2 - 1;
              prevSuccess = false;
              break;
            }
          } else {
            if (S[ptr2] == 'O') {
              ptr2++;
              check = true;
            } else {
              ptr1 = ptr2 - 1;
              prevSuccess = false;
              break;
            }
          }
        }
      } else {
        check = false;
        for (int i = 0; i < 2 && ptr2 < M; i++) {
          if (check) {
            if (S[ptr2] == 'I') {
              ptr2++;
              check = false;
            } else {
              ptr1 = ptr2 - 1;
              prevSuccess = false;
              break;
            }
          } else {
            if (S[ptr2] == 'O') {
              ptr2++;
              check = true;
            } else {
              ptr1 = ptr2 - 1;
              prevSuccess = false;
              break;
            }
          }
        }
      }
      if (ptr2 - ptr1 == 2 * N + 1) {
        count++;
        ptr1++;
        prevSuccess = true;
        continue;
      }
    }
    bw.write(count  + "\n");
    bw.flush();
  }
}
