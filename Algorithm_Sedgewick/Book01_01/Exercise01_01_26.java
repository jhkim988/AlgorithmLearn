
public class Exercise01_01_26 {
	// 1.1.26
	// �Ʒ� �ڵ忡�� ���� a, b, c, t�� ��� ���� ���� Ÿ���̶�� ����.
	// �Ʒ��� �ڵ尡 ��� �ؼ� a, b, c�� ������������ ���ڸ� �����Ͽ� �ٲ�ִ��� �����϶�
	public static void main(String[] args) {
		int a = 0;
		int b = 1;
		int c = 2;
		int t = 4;

		if (a > b) { 
			// swap a and b
			t = a;
			a = b;
			b = t;
		} // a <= b
		if (a > c) {
			t = a;
			a = c;
			c = t;
		} // a <= c
		if (b > c) {
			t = b;
			b = c;
			c = t;
		} // b <= c
		// a <= b <= c
	}
}
