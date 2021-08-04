import java.util.Scanner;

class Exercise08_04 {
	// Q4 Boyer-Moore�� �˻� ������ �ڼ��� ����ϴ� ���α׷��� �ۼ��ϼ���
	static String printSpace(int n) {
		String result = "";
		for (int i = 0; i < n; ++i)
			result += " ";
		return result;
	}

	static int bmMatch(String txt, String pat) {
		int pt;
		int pp;
		int txtLen = txt.length();
		int patLen = pat.length();
		int[] skip = new int[Character.MAX_VALUE + 1];

		int count = 0;
		int patMove = 0;
		
		for (pt = 0; pt <= Character.MAX_VALUE; pt++) {
			skip[pt] = patLen;
		}
		for (pt = 0; pt < patLen - 1; pt++) {
			skip[pat.charAt(pt)] = patLen - pt - 1;
		}

		while (pt < txtLen) {
			boolean flag = false;
			System.out.printf("%02d %s\n", count++, txt);

			pp = patLen - 1;

			while (txt.charAt(pt) == pat.charAt(pp)) {
				if (flag)
					System.out.printf("%02d %s\n", count++, txt);
				System.out.println(printSpace(3 + patMove + pp) + "+");
				System.out.println(printSpace(3 + patMove) + pat);
				if (pp == 0)
					return pt;
				pp--;
				pt--;
				flag = true;
			}
			if (flag)
				System.out.printf("%02d %s\n", count++, txt);
			System.out.println(printSpace(3 + patMove + pp) + "|");
			System.out.println(printSpace(3 + patMove) + pat);
			
			pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
			patMove = pt - patLen + 1;
		}
		return -1;

	}

	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("�ؽ�Ʈ : ");
		String s1 = stdIn.next();

		System.out.print("���� : ");
		String s2 = stdIn.next();

		stdIn.close();
		int idx = bmMatch(s1, s2);

		if (idx == -1)
			System.out.println("�ؽ�Ʈ�� ������ �����ϴ�.");
		else {
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			System.out.println((idx + 1) + "��° ���ں��� ��ġ�մϴ�.");
			System.out.println("�ؽ�Ʈ : " + s1);
			System.out.printf(String.format("����  : %%%ds\n", len), s2);
		}
	}
}