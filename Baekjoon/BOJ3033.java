import java.io.*;
import java.util.*;

public class BOJ3033 {
  static long mod = 1_000_000_007;
  static long key = 999_983;
  static long rev = pow(key, mod-2);
  static long key1 = 31;
  static long rev1 = pow(key1, mod-2);
  static int n;
  static char[] str;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    str = br.readLine().toCharArray();
    int lo = 0, hi = n;
    while (lo+1 < hi) {
      int mid = (lo+hi) >> 1;
      if (doubleHashing(mid)) {
        lo = mid;
      } else {
        hi = mid;
      }
    }
    bw.write(Integer.toString(lo));
    bw.flush();
  }
  static boolean possible(int len) {
    HashMap<Long, Integer> hm = new HashMap<>();
    long hashValue = 0;
    long exp = 1;
    for (int i = 0; i < len; i++) {
      hashValue += str[i]*exp%mod; hashValue %= mod;
      exp = exp*key%mod;
    }
    hm.put(hashValue, 0);
    next: for (int i = 1; i-1+len < str.length; i++) {
      hashValue += (mod-str[i-1])%mod; hashValue %= mod;
      hashValue += str[i-1+len]*exp%mod; hashValue %= mod;
      hashValue = hashValue*rev%mod;
      if (hm.containsKey(hashValue)) {
        int j = hm.get(hashValue);
        for (int k = 0; k < len; k++) {
          if (str[i+k] != str[j+k]) continue next;
        }
        return true;
      }
      hm.put(hashValue, i);
    }
    return false;
  }
  static long pow(long base, long exp) {
    if (exp == 0) return 1;
    if (exp == 1) return base%mod;
    long half = pow(base, exp/2);
    if (exp % 2 == 0) return half*half%mod;
    return half*half%mod*base%mod;
  }
  static boolean doubleHashing(int len) {
    HashSet<Long> hm0 = new HashSet<>();
    HashSet<Long> hm1 = new HashSet<>();
    long hashValue0= 0, hashValue1 = 0, exp0 = 1, exp1 = 1;
    for (int i = 0; i < len; i++) {
      hashValue0 += str[i]*exp0%mod; hashValue0 %= mod;
      hashValue1 += str[i]*exp1%mod; hashValue1 %= mod;
      exp0 = exp0*key%mod;
      exp1 = exp1*key1%mod;
    }
    hm0.add(hashValue0);
    hm1.add(hashValue1);
    for (int i = 1; i-1+len < str.length; i++) {
      hashValue0 += (mod-str[i-1])%mod; hashValue0 %= mod;
      hashValue0 += str[i-1+len]*exp0%mod; hashValue0 %= mod;
      hashValue0 = hashValue0*rev%mod;
      hashValue1 += (mod-str[i-1])%mod; hashValue1 %= mod;
      hashValue1 += str[i-1+len]*exp1%mod; hashValue1 %= mod;
      hashValue1 = hashValue1*rev1%mod;
      if (hm0.contains(hashValue0) && hm1.contains(hashValue1)) return true;
      hm0.add(hashValue0);
      hm1.add(hashValue1);
    }
    return false;
  }
}
