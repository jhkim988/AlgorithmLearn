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
				pt = pt - pp + 1; // �� ĭ ����
				pp = 0;
			}
		}
		if (pp == pat.length())
			return txt.length() - pt + pat.length() - pp;
		return -1;
	}
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("�ؽ�Ʈ : ");
		String s1 = stdIn.next();
		
		System.out.print("���� : ");
		String s2 = stdIn.next();
		
		stdIn.close();
		int idx = bfMatchLast(s1, s2);
		
		if (idx == -1)
			System.out.println("�ؽ�Ʈ�� ������ �����ϴ�.");
		else {
			// ��ġ�ϴ� ���� �ٷ� �ձ����� ���̸� ���Ѵ�.
			int len = 0;
			for(int i = 0; i < idx; i++)
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