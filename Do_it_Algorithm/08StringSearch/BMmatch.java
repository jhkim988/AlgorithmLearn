import java.util.Scanner;

class BMmatch {
	static int bmMatch(String txt, String pat) {
		int pt;
		int pp;
		int txtLen = txt.length();
		int patLen = pat.length();
		int[] skip = new int[Character.MAX_VALUE + 1];
		
		for(pt = 0; pt <= Character.MAX_VALUE; pt++)
			skip[pt] = patLen; // 기본적으로 패턴의 길이로 초기화한다.
		for(pt = 0; pt < patLen - 1; pt++) {
			// 패턴의 글자를 순회한다.
			// n글자 패턴에서 어떤 문자가 마지막에 나오는 인덱스가 k라면 n - k - 1만큼 움직여야한다.
			// 즉 앞부터 순회하면서 덮어 쓰면 맨 마지막에 나오는 것으로 최종적으로 덮어씌워지게 된다.
			// 순회가 끝나면 pt == patLen - 1이 된다.
			skip[pat.charAt(pt)] = patLen- pt - 1;
		}
		
		while(pt < txtLen) {
			pp = patLen - 1; // 마지막 글자부터
			
			while (txt.charAt(pt) == pat.charAt(pp)) {
				if (pp == 0)
					return pt;
				pp--;
				pt--;
			}
			// 커서가 몇 칸 뒤로? patLen - 1 - pp
			// 적어도 한 칸은 앞으로 가야함... 즉 patlen - pp만큼은 가야한다.
			pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
		}
		return -1;
		
	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("텍스트 : ");
		String s1 = stdIn.next();

		System.out.print("패턴 : ");
		String s2 = stdIn.next();

		stdIn.close();
		int idx = bmMatch(s1, s2);

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