import java.io.*;
import java.util.*;

public class BOJ4256 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  public static void main(String[] args) throws IOException {
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      int n = Integer.parseInt(br.readLine());
      int[] preorder = new int[n];
      int[] inorder = new int[n];
      input(preorder);
      input(inorder);
      int[] postorder = new int[n];
      postorder(preorder, inorder, postorder, 0, n-1, 0, n-1, 0, n-1);
      print(postorder);
    }
    bw.flush();
  }
  static void input(int[] arr) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < arr.length; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
  }
  static void postorder(int[] preorder, int[] inorder, int[] postorder, int prestart, int preend, int instart, int inend, int poststart, int postend) {
    if (preend < prestart || inend < instart || postend < poststart) return;
    int root = preorder[prestart];
    postorder[postend] = root;
    int rootIdx = instart;
    for (int i = instart; i <= inend; i++) {
      if (inorder[i] == root) {
        rootIdx = i;
        break;
      }
    }

    HashSet<Integer> leftSet = new HashSet<>();
    for (int i = instart; i < rootIdx; i++) {
      leftSet.add(inorder[i]);
    }
    int mid = preend+1;
    for (int i = prestart+1; i <= preend; i++) {
      if (!leftSet.contains(preorder[i])) {
        mid = i;
        break;
      }
    }
    leftSet = null;
    int numLeft = rootIdx - instart;
    // leftChild:
    postorder(preorder, inorder, postorder, prestart+1, mid-1, instart, rootIdx-1, poststart, poststart+numLeft-1);
    // rightChild:
    postorder(preorder, inorder, postorder, mid, preend, rootIdx+1, inend, poststart+numLeft,  postend-1);
  }
  static void print(int[] arr) throws IOException {
    bw.write(Integer.toString(arr[0]));
    for (int i = 1; i < arr.length; i++) {
      bw.write(' ');
      bw.write(Integer.toString(arr[i]));
    }
    bw.newLine();
  }
}
