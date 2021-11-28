import java.io.*;
import java.util.*;

public class BOJ3019 {
  static int C;
  static int P;
  private static class Block {
    int type;
    int rot;
    int[] height;
    Block(int type, int rot, int[] height) {
      this.type = type;
      this.rot = rot;
      this.height = height;
    }
    boolean possible(int idx, int[] stat) {
      System.out.println("idx: " + idx + ", rot: " + rot);
      if (idx + height.length - 1 >= C) return false;
      System.out.println("idx: " + idx + ", rot: " + rot);

      boolean flag = true;
      for (int i = 0; i < height.length - 1; i++) {
        flag = flag && (stat[i] - height[i] == stat[i + 1] - height[i + 1]);
      }
      return flag;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    C = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    int[] stat = new int[C];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < C; i++) {
      stat[i] = Integer.parseInt(st.nextToken());
    }
    ArrayList<ArrayList<Block>> blocks = new ArrayList<>();
    init(blocks);
    
    int count = 0;
    ArrayList<Block> find = blocks.get(P - 1);
    for (Block bl : find) {
      for (int idx = 0; idx < C; idx++) {
        // System.out.println("idx: " + idx + ", type: " + bl.type + ", rot: " + bl.rot);

        if (bl.possible(idx, stat)) {
          count++;
        }
      }
    }
    
    bw.write(count + "\n");
    bw.flush();
  }

  static void init(ArrayList<ArrayList<Block>> blocks) {
    for (int i = 0; i < 7; i++) {
      blocks.add(new ArrayList<Block>());
    }
    for (int i = 0; i < 7; i++) {
      switch (i) {
        case 0:
          blocks.get(i).add(new Block(1, 0, new int[] {0}));
          blocks.get(i).add(new Block(1, 1, new int[] {0, 0, 0, 0}));
          break;
        case 1:
          blocks.get(i).add(new Block(2, 0, new int[] {0, 0}));
          break;
        case 2:
          blocks.get(i).add(new Block(3, 0, new int[] {0, 0, 1}));
          blocks.get(i).add(new Block(3, 1, new int[] {1, 0}));
          break;
        case 3:
          blocks.get(i).add(new Block(4, 0, new int[] {1, 0, 0}));
          blocks.get(i).add(new Block(4, 1, new int[] {0, 1}));
          break;
        case 4:
          blocks.get(i).add(new Block(5, 0, new int[] {0, 0, 0}));
          blocks.get(i).add(new Block(5, 1, new int[] {0, 1}));
          blocks.get(i).add(new Block(5, 2, new int[] {1, 0, 1}));
          blocks.get(i).add(new Block(5, 3, new int[] {1, 0}));
          break;
        case 5:
          blocks.get(i).add(new Block(6, 0, new int[] {0, 0, 0}));
          blocks.get(i).add(new Block(6, 1, new int[] {0, 0}));
          blocks.get(i).add(new Block(6, 2, new int[] {0, 1, 1}));
          blocks.get(i).add(new Block(6, 3, new int[] {2, 0}));
          break;
        case 6:
          blocks.get(i).add(new Block(7, 0, new int[] {0, 0, 0}));
          blocks.get(i).add(new Block(7, 1, new int[] {0, 2}));
          blocks.get(i).add(new Block(7, 2, new int[] {1, 1, 0}));
          blocks.get(i).add(new Block(7, 3, new int[] {0, 0}));
          break;
        } 
    }
  }
}