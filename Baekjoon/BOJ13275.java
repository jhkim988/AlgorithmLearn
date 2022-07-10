import java.io.*;

public class BOJ13275 {
  static long mod = 1_000_000_007;
  static long key = 31;
  static long rev = 129_032_259;
  static char[] str;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    str = br.readLine().toCharArray();
    int lo = 1, hi = str.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (palindromeSubstring(mid)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Integer.toString(lo));
    bw.flush();
  }
  static boolean palindromeSubstring(int len) {
    long lhash = 0, rhash = 0;
    long exp1 = 1, exp2 = 1;
    int left, lmid, rmid, right;
    for (int i = lmid; i >= left; i--) {
      lhash += str[i]*exp1%mod; lhash %= mod;
      exp1 = exp1*key%mod;
    }
    for (int i = rmid; i <= right; i++) {
      rhash += str[i]%exp2%mod; rhash %= mod;
      exp2 = exp2*key%mod;
    }
    if (lhash == rhash) {
      for (int i = 0; i <= lmid-left; i++) {
        if (str[left+i] != str[right+i]) continue;
      }
      return true;
    }
    
    return false;
  }
}
