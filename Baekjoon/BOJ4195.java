import java.io.*;
import java.util.*;

public class BOJ4195 {
  private static class UnionFind {
    ArrayList<Integer> id;
    ArrayList<Integer> sz;
    UnionFind() {
      id = new ArrayList<>();
      sz = new ArrayList<>();
    }
    private int root(int i) {
      while(i != id.get(i)) {
        i = id.get(i);
      }
      return i;
    }
    void add() {
      id.add(id.size(), id.size());
      sz.add(sz.size(), 1);
    }
    void union(int p, int q) {
      int prt = root(p);
      int qrt = root(q);
      if (prt == qrt) return;
      if (sz.get(prt) < sz.get(qrt)) {
        id.set(prt, id.get(qrt));
        sz.set(qrt, sz.get(qrt) + sz.get(prt));
      } else {
        id.set(qrt, id.get(prt));
        sz.set(prt, sz.get(prt) + sz.get(qrt));
      }
    }
    // boolean connected(int p, int q) {
    //   return root(p) == root(q);
    // }
    int size(int p) {
      return sz.get(root(p));
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int numTest = Integer.parseInt(br.readLine());
    while (numTest-- > 0) {
      HashMap<String, Integer> hm = new HashMap<>();
      UnionFind uf = new UnionFind();
      int numFriendship = Integer.parseInt(br.readLine());
      int index = 0;
      for (int i = 0; i < numFriendship; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String first = st.nextToken();
        String second = st.nextToken();
        if (!hm.containsKey(first)) {
          hm.put(first, index);
          uf.add();
          index++;        
        }
        if (!hm.containsKey(second)) {
          hm.put(second, index);
          index++;
          uf.add();
        }
        uf.union(hm.get(first), hm.get(second));
        bw.write(uf.size(hm.get(first)) + "\n");
      }
    }
    bw.flush();
  }
}
