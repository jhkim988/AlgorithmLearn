public class UnionFind {
  int[] id;
  int[] sz;
  UnionFind(int size) {
    id = new int[size];
    sz = new int[size];
    for (int i = 0; i < size; i++) {
      id[i] = i;
      sz[i] = 1;
    }
  }
  int root (int i) {
    while (i != id[i]) {
      id[i] = id[id[i]]; // path compression
      i = id[i];
    }
    return i;
  }

  void union(int p, int q) {
    int prt = root(p);
    int qrt = root(q);
    if (prt == qrt) return; // do nothing
    if (sz[prt] < sz[qrt]) {
      id[prt] = qrt;
      sz[qrt] += sz[prt];
    } else {
      id[qrt] = prt;
      sz[prt] += sz[qrt];
    }
  }

  boolean isConnected(int p, int q) {
    return root(p) == root(q);
  }
}