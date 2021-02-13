import java.util.Scanner;

class Exercise08_01{
	// Q1. 브루트포스법의 검색 과정을 자세히 출력하는 프로그램을 작성하세요
	static String printSpace(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}
	static int bfMatch(String txt, String pat) {
		int pt = 0;
		int pp = 0;
		
		int count = 0;
		boolean flag = true;
		
		
		while(pt != txt.length() && pp != pat.length()) {			
			if (flag)
				System.out.println(count + " " + txt);
			else
				System.out.println("  " + txt);
			
			if (txt.charAt(pt) == pat.charAt(pp)) {
				flag = false;
				System.out.println(printSpace(2 + count + pp) + "+");
				System.out.println(printSpace(2 + count) + pat);
				pt++;
				pp++;
			} else {
				flag = true;
				System.out.println(printSpace(2 + count + pp) + "|");
				System.out.println(printSpace(2 + count) + pat);
				count++;

				pt = pt - pp + 1;
				pp = 0;
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
		int idx = bfMatch(s1, s2);
		
		if (idx == -1)
			System.out.println("텍스트에 패턴이 없습니다.");
		else {
			int len = 0;
			for(int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();
			
			System.out.println((idx + 1) + "번째 문자부터 일치합니다.");
			System.out.println("텍스트 : " + s1);
			System.out.printf(String.format("패턴  : %%%ds\n", len), s2);
		}
	}
}