
public class Exercise01_01_17 {
	// 1.1.17
	// �Ʒ� ��� �Լ��� �������� ã�ƺ���
	// sol) ��� ���� ������ ���ȣ�� ������ ������ ������ ����� �� ����.
	public static String exR2(int n) {
		String s = exR2(n-3) + n + exR2(n-2) + n;
		if (n <= 0) return "";
		return s;
	}
}
