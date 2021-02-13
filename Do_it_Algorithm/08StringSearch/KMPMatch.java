import java.util.Scanner;

class KMPMatch {
	static int kmpMatch(String txt, String pat) {
		int pt = 1;
		int pp = 0;
		int[] skip = new int[pat.length() + 1];
		// �� �� ĭ ��? �� �� ĭ�� ������� �ʴ´�.

		// �ǳʶٱ� ǥ�� ����ϴ�.
		skip[pt] = 0;
		while (pt != pat.length()) {
			if (pat.charAt(pt) == pat.charAt(pp)) // ���ڰ� ��ġ�ϴ� ���
				skip[++pt] = ++pp;
			else if (pp == 0) // ù���ں��� ����ġ�ϴ� ���
				skip[++pt] = pp; // pp == 0 �̴ϱ� �׳� 0...
			else // ��ġ�ϴٰ� ����ġ�ϴ� ���
				pp = skip[pp]; // skipǥ�� �ۼ��� index���� ���ϱ� ���� Ŀ���� �̵��Ѵ�.
		}

		// �˻�
		pt = pp = 0;
		while (pt != txt.length() && pp != pat.length()) {
			if (txt.charAt(pt) == pat.charAt(pp)) { // ���ڰ� ��ġ�Ѵٸ�
				// Ŀ���� �� �� �� ĭ�� �ű��.
				pt++;
				pp++;
			} else if (pp == 0) { // ���ڰ� �� ���ڵ� ��ġ���� �ʴ´ٸ�
				// txt Ŀ���� �̵��ؼ� ������ �˻��� ó������ �ٽ� �˻��Ѵ�. (������ pp == 0)
				pt++;
			} else { // ��ġ�ϴٰ� ����ġ�Ѵٸ�
				// ������ �˻��� skip[pp]���� �Ѵ�.
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