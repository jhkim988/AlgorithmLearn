import java.util.Scanner;

class BMmatch {
	static int bmMatch(String txt, String pat) {
		int pt;
		int pp;
		int txtLen = txt.length();
		int patLen = pat.length();
		int[] skip = new int[Character.MAX_VALUE + 1];
		
		for(pt = 0; pt <= Character.MAX_VALUE; pt++)
			skip[pt] = patLen; // �⺻������ ������ ���̷� �ʱ�ȭ�Ѵ�.
		for(pt = 0; pt < patLen - 1; pt++) {
			// ������ ���ڸ� ��ȸ�Ѵ�.
			// n���� ���Ͽ��� � ���ڰ� �������� ������ �ε����� k��� n - k - 1��ŭ ���������Ѵ�.
			// �� �պ��� ��ȸ�ϸ鼭 ���� ���� �� �������� ������ ������ ���������� ��������� �ȴ�.
			// ��ȸ�� ������ pt == patLen - 1�� �ȴ�.
			skip[pat.charAt(pt)] = patLen- pt - 1;
		}
		
		while(pt < txtLen) {
			pp = patLen - 1; // ������ ���ں���
			
			while (txt.charAt(pt) == pat.charAt(pp)) {
				if (pp == 0)
					return pt;
				pp--;
				pt--;
			}
			// Ŀ���� �� ĭ �ڷ�? patLen - 1 - pp
			// ��� �� ĭ�� ������ ������... �� patlen - pp��ŭ�� �����Ѵ�.
			pt += (skip[txt.charAt(pt)] > patLen - pp) ? skip[txt.charAt(pt)] : patLen - pp;
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