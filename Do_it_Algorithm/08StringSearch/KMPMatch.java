import java.util.Scanner;

class KMPMatch {
	static int kmpMatch(String txt, String pat) {
		int pt = 1;
		int pp = 0;
		int[] skip = new int[pat.length() + 1];
		// 왜 한 칸 더? 맨 앞 칸은 사용하지 않는다.

		// 건너뛰기 표를 만듭니다.
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp)) // 글자가 일치하는 경우
				skip[++pt] = ++pp;
			else if (pp == 0) // 첫글자부터 불일치하는 경우
				skip[++pt] = pp; // pp == 0 이니까 그냥 0...
			else // 일치하다가 불일치하는 경우
				pp = skip[pp]; // skip표에 작성된 index부터 비교하기 위해 커서를 이동한다.
		}

		// 검색
		pt = pp = 0;
		while (pt != txt.length() && pp != pat.length()) {
			if (txt.charAt(pt) == pat.charAt(pp)) { // 글자가 일치한다면
				// 커서를 둘 다 한 칸씩 옮긴다.
				pt++;
				pp++;
			} else if (pp == 0) { // 글자가 한 글자도 일치하지 않는다면
				// txt 커서만 이동해서 패턴의 검색을 처음부터 다시 검색한다. (어차피 pp == 0)
				pt++;
			} else { // 일치하다가 불일치한다면
				// 패턴의 검색을 skip[pp]부터 한다.
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
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
			System.out.println("텍스트 : " + s1);
			System.out.printf(String.format("패턴  : %%%ds\n", len), s2);
		}
	}
}