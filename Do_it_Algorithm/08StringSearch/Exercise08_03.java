import java.util.Scanner;

class Exercise08_03 {
	// Q3. 검색과정을 출력하는 프로그램 작성
	static String printSpace(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}

	static int kmpMatch(String txt, String pat) {
		int pt = 1;
		int pp = 0;
		int[] skip = new int[pat.length() + 1];

		int count = 0;
		int patMove = 0;
		
		// 건너뛰기 표
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if (pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}

		// 검색
		pt = pp = 0;
		while (pt != txt.length() && pp != pat.length()) {
			System.out.printf("%02d %s\n" , count++, txt);

			if (txt.charAt(pt) == pat.charAt(pp)) {
				System.out.println(printSpace(3 + pp + patMove) + "+");
				System.out.println(printSpace(3 + patMove) + pat);
				pt++;
				pp++;
			} else if (pp == 0) {
				System.out.println(printSpace(3 + pp+ patMove) + "|");
				System.out.println(printSpace(3 + patMove) + pat);
				patMove++;
				pt++;
			} else {
				System.out.println(printSpace(3 + pp + patMove) + "|");
				System.out.println(printSpace(3 + patMove) + pat);
				patMove += pp;
				pp = skip[pp];
			}
		}
		if (pp == pat.length())
			return pt - pp;
		return -1;
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("텍스트 : ");
		String s1 = stdIn.next();

		System.out.print("패턴 : ");
		String s2 = stdIn.next();

		stdIn.close();
		int idx = kmpMatch(s1, s2);

		if (idx == -1)
			System.out.println("텍스트에 패턴이 없습니다.");
		else {
			// 일치하는 문자 바로 앞까지의 길이를 구한다.
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			// substring(a, b): 인덱스 a ~ b까지의 substring을 반환한다.
			// getBytes(): 바이트로 변환한다. (인코딩/디코딩의 문제..?)

			System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
			System.out.println("텍스트 : " + s1);
			System.out.printf(String.format("패턴  : %%%ds\n", len), s2);
		}
	}
}