class PrimeNumber3 {
	public static void main(String[] args) {
		int counter = 0;
		int ptr = 0;
		int[] prime = new int[500];

		prime[ptr++] = 2;
		prime[ptr++] = 3;

		for (int n = 5; n <= 1000; n += 2) { // 홀수부터 2씩 증가시켜 짝수를 걸렀기 때문에 2로 나눌 필요가 없다.
			boolean flag = false;
			for (int i = 1; prime[i] * prime[i] <= n; ++i) {
				counter += 2;
				if (n % prime[i] == 0) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				prime[ptr++] = n;
				counter++; // prime[i] * prime[i] <= n이 성립하지 않는다면, 판별하기 위한 곱하기 연산의 횟수를 더할 수 없기 때문에 여기서 더해준다.
			}
		}
		for (int i = 0; i < ptr; ++i)
			System.out.println(prime[i]);
		System.out.println("곱셈과 나눗셈 횟수 : " + counter);
	}
}