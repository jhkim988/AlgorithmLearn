
public class Exercise01_01_27 {
	// 1.1.27 ���׺���
	// �Ʒ��� �ڵ忡�� binomial(100, 50, 0.25)�� ����� �� ���ȣ���� �� �� �߻��ϴ��� �߻��϶�
	// �߰� ��갪���� �迭�� �����Ͽ� ��Ȱ���ϴ� ������� ���� �ð��� �����غ���.
	public static double binomial(int N, int k, double p) {
		if (N == 0 && k == 0)
			return 1.0;
		if (N < 0 || k < 0)
			return 0.0;
		return (1 - p) * binomial(N - 1, k, p) + p * binomial(N - 1, k - 1, p);
	}

	public static void main(String[] args) {

	}
}
