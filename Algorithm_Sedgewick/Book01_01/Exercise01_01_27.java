
public class Exercise01_01_27 {
	// 1.1.27 이항분포
	// 아래의 코드에서 binomial(100, 50, 0.25)를 계산할 때 재귀호출이 몇 번 발생하는지 추산하라
	// 중간 계산값들을 배열에 저장하여 재활용하는 방식으로 연산 시간을 개선해보라.
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
