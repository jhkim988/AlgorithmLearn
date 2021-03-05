import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_06 {
	// 1.2.6
	// 어떤 문자열 s가 다른 문자열 a를 원형 순환한(위치를 몇 번 순환하든 상관없이) 결과와 같을 때
	// 문자열 s를 문자열 a의 원형 시프트라고 한다. 예를 들어 ACTGACG와 TGACGAC는 서로 원형 시프트 관계이다.
	// 유전자 연구에서는 이러한 조건을 찾는 것이 중요하다.
	// 문자열 두 개가 주어졌을 때 둘이 원형 시프트 관계인지 찾는 프로그램을 작성하라.
	// indexOf()와 length() 그리고 문자열 병합을 이용하면 한 줄로 작성할 수 있다.

	static boolean isCircularShift(String a, String b) {
		// how to complete with only one line???
		int len = a.length();
		if (len != b.length())
			return false;
		String material = "";
		for (int i = 0; i < len - 1; i++) {
//			material = b[i] ~ b[length - 1] + b[0] ~ b[i - 1]
			material = b.substring(i) + b.substring(0, i);
			if (material.equals(a))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String str1 = "ACTGACG";
		String str2 = "TGACGAC";
		String str3 = "TGACAAC";
		StdOut.println(str1 + " and " + str2 + " are"
				+ (isCircularShift(str1, str2) ? " circular shift" : " not circular shift"));
		StdOut.println(str1 + " and " + str3 + " are"
				+ (isCircularShift(str1, str3) ? " circular shift" : " not circular shift"));
	}
}
