import java.io.*;
import java.util.*;

public class BOJ14500 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] paper = new int[N][M];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        paper[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    // block
    boolean[][][] blocks = {
      {{true, true, true, true}},
      {{true}, {true}, {true}, {true}},

      {{true, true}, {true, true}},

      {{true, false}, {true, false}, {true, true}},
      {{true, true, true}, {true, false, false}},
      {{true, true}, {false, true}, {false, true}},
      {{false, false, true}, {true, true, true}},

      {{false, true}, {false, true}, {true, true}},
      {{true, false, false}, {true, true, true}},
      {{true, true}, {true, false}, {true, false}},
      {{true, true, true}, {false, false, true}},

      {{true, false}, {true, true}, {false, true}},
      {{false, true, true}, {true, true, false}},
      
      {{false, true}, {true, true}, {true, false}},
      {{true, true, false}, {false, true, true}},

      {{true, true, true}, {false, true, false}},
      {{false, true}, {true, true}, {false, true}},
      {{false, true, false}, {true, true, true}},
      {{true, false}, {true, true}, {true, false}}
    };

    int max = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        for (boolean[][] block : blocks) {
          int sum = 0;
          if (i + block.length <= N && j + block[0].length <= M) {
            for (int k = 0; k < block.length; k++) {
              for (int l = 0; l < block[0].length; l++) {
                if (block[k][l]) {
                  sum += paper[i + k][j + l];
                }
              }
            }
            if (max < sum) {
              max = sum;
            }
          }
        }
      }
    }
    bw.write(max + "\n");
    bw.flush();
  }
}
