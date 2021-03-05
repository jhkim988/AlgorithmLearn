import edu.princeton.cs.algs4.StdOut;

public class Exercise01_02_06 {
	// 1.2.6
	// � ���ڿ� s�� �ٸ� ���ڿ� a�� ���� ��ȯ��(��ġ�� �� �� ��ȯ�ϵ� �������) ����� ���� ��
	// ���ڿ� s�� ���ڿ� a�� ���� ����Ʈ��� �Ѵ�. ���� ��� ACTGACG�� TGACGAC�� ���� ���� ����Ʈ �����̴�.
	// ������ ���������� �̷��� ������ ã�� ���� �߿��ϴ�.
	// ���ڿ� �� ���� �־����� �� ���� ���� ����Ʈ �������� ã�� ���α׷��� �ۼ��϶�.
	// indexOf()�� length() �׸��� ���ڿ� ������ �̿��ϸ� �� �ٷ� �ۼ��� �� �ִ�.

	static boolean isCircularShift(String a, String b) {
		// how to complete with only one line???
		int len = a.length();
		if (len != b.length())
			return false;
		String material = "";
		for (int i = 0; i < len - 1; i++) {
//			material = b[i] ~ b[length - 1] + b[0] ~ b[i - 1]
			material = b.substring(i) + b.substring(0, i);
			if (material.equals(a))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String str1 = "ACTGACG";
		String str2 = "TGACGAC";
		String str3 = "TGACAAC";
		StdOut.println(str1 + " and " + str2 + " are"
				+ (isCircularShift(str1, str2) ? " circular shift" : " not circular shift"));
		StdOut.println(str1 + " and " + str3 + " are"
				+ (isCircularShift(str1, str3) ? " circular shift" : " not circular shift"));
	}
}
