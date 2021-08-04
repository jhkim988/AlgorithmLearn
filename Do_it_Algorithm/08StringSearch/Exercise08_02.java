import java.util.Scanner;

class Exercise08_02{
	static int bfMatchLast(String txt, String pat) {
		int pt = 0;
		int pp = 0;
		
		while(pt != txt.length() && pp != pat.length()) {
			if (txt.charAt(txt.length() - 1 - pt) == pat.charAt(pat.length() - 1 - pp)) {
				pt++;
				pp++;
			} else {
				pt = pt - pp + 1; // 한 칸 전진
				pp = 0;
			}
		}
		if (pp == pat.length())
			return txt.length() - pt + pat.length() - pp;
		return -1;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("텍스트 : ");
		String s1 = stdIn.next();
		
		System.out.print("패턴 : ");
		String s2 = stdIn.next();
		
		stdIn.close();
		int idx = bfMatchLast(s1, s2);
		
		if (idx == -1)
			System.out.println("텍스트에 패턴이 없습니다.");
		else {
			// 일치하는 문자 바로 앞까지의 길이를 구한다.
			int len = 0;
			for(int i = 0; i < idx; i++)
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