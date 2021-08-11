import java.io.*;
import java.util.*;

public class BOJ2263 {
  static int[] inorder;
  static int[] postorder;
  static Queue<Integer> preorder;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    inorder = new int[n];
    postorder = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      inorder[i] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      postorder[i] = Integer.parseInt(st.nextToken());
    }
    preorder = new LinkedList<>();
    recur(0, n - 1, 0, n - 1);
    while(!preorder.isEmpty()) {
      bw.write(preorder.poll() + " ");
    }
    bw.flush();
  }
  static void recur(int inorderStart, int inorderEnd, int postStart, int postEnd) {
    if (inorderStart > inorderEnd || postStart > postEnd) {
      return;
    }
    int root = postorder[postEnd];
    preorder.add(root);
    // find index of root in inorder
    int idx = inorderStart;
    for (idx = inorderStart; idx <= inorderEnd; idx++) {
      if (inorder[idx] == root) {
        break;
      }
    }
    int leftlen = idx - inorderStart;
    int rightlen = inorderEnd - idx;
    recur(inorderStart, inorderStart + leftlen - 1, postStart, postStart + leftlen - 1);
    recur(idx + 1, inorderEnd, postEnd - rightlen, postEnd - 1);
  }
}
