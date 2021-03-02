
public class Exercise01_01_17 {
	// 1.1.17
	// 아래 재귀 함수의 문제점을 찾아보라
	// sol) 재귀 종료 조건이 재귀호출 다음에 나오기 때문에 종료될 수 없다.
	public static String exR2(int n) {
		String s = exR2(n-3) + n + exR2(n-2) + n;
		if (n <= 0) return "";
		return s;
	}
}
