import java.io.*;

public class BOJ14444 {
  static long mod = 1_000_000_007;
  static long key = 31;
  static long rev = 129_032_259;
  static char[] str;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    str = br.readLine().toCharArray();
    bw.write(Integer.toString(Integer.max(binarySearch(true), binarySearch(false))));
    bw.flush();
  }
  static boolean palindromeSubstring(int len) {
    if (len <= 1) return true;
    if (len > str.length) return false;
    long lhash = 0, rhash = 0;
    long exp1 = 1, exp2 = 1;
    int left = 0, lmid = len/2-1, rmid = len-len/2, right = len-1;
    for (int i = lmid; i >= left; i--) {
      lhash += str[i]*exp1%mod; lhash %= mod;
      exp1 = exp1*key%mod;
    }
    for (int i = rmid; i <= right; i++) {
      rhash += str[i]*exp2%mod; rhash %= mod;
      exp2 = exp2*key%mod;
    }
    if (lhash == rhash) {
      boolean flag = false;;
      for (int i = 0; i <= lmid-left; i++) {
        if (str[left+i] != str[right-i]) {
          flag = true;
          break;
        }
      }
      if (!flag) return true;
    }
    next: while (right+1 < str.length) {
      lhash = lhash*key%mod;
      lhash += (mod-str[left++])*exp1%mod; lhash %= mod;
      lhash += str[++lmid]%mod; lhash %= mod;
      rhash += (mod-str[rmid++])%mod; rhash %= mod;
      rhash += str[++right]*exp2%mod; rhash %= mod;
      rhash = rhash*rev%mod;
      if (lhash == rhash) {
        for (int i = 0; i <= lmid-left; i++) {
          if (str[left+i] != str[right-i]) continue next;
        }
        return true;
      }
    }
    return false;
  }
  static int binarySearch(boolean isOdd) {
    int lo = 0, hi = str.length;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (palindromeSubstring(2*mid + (isOdd ? 1 : 0))) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    return 2*lo + (isOdd ? 1 : 0);
  }
}
