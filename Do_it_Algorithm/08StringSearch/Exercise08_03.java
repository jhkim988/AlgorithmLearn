import java.util.Scanner;

class Exercise08_03 {
	// Q3. �˻������� ����ϴ� ���α׷� �ۼ�
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
		
		// �ǳʶٱ� ǥ
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp))
				skip[++pt] = ++pp;
			else if (pp == 0)
				skip[++pt] = pp;
			else
				pp = skip[pp];
		}

		// �˻�
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

		System.out.print("�ؽ�Ʈ : ");
		String s1 = stdIn.next();

		System.out.print("���� : ");
		String s2 = stdIn.next();

		stdIn.close();
		int idx = kmpMatch(s1, s2);

		if (idx == -1)
			System.out.println("�ؽ�Ʈ�� ������ �����ϴ�.");
		else {
			// ��ġ�ϴ� ���� �ٷ� �ձ����� ���̸� ���Ѵ�.
			int len = 0;
			for (int i = 0; i < idx; i++)
				len += s1.substring(i, i + 1).getBytes().length;
			len += s2.length();

			// substring(a, b): �ε��� a ~ b������ substring�� ��ȯ�Ѵ�.
			// getBytes(): ����Ʈ�� ��ȯ�Ѵ�. (���ڵ�/���ڵ��� ����..?)

			System.out.println((idx + 1) + "��° ���ں��� ��ġ�մϴ�.");
			System.out.println("�ؽ�Ʈ : " + s1);
			System.out.printf(String.format("����  : %%%ds\n", len), s2);
		}
	}
}