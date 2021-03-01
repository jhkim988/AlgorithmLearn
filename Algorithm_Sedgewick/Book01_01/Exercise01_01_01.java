class Exercise01_01_01 {
	public static void main(String[] args) {
		// 1.1.1 아래 각 표현식의 결괏값은 무엇인가?
		// a. (0 + 15) / 2
		// b. 2.0e-6 * 100000000.1
		// c. true && false || true && true

		// sol)
		// a. 7 - int 타입이기 때문에 소숫점이 절삭된다.
		// b. 200.0000002
		// c. true
		System.out.println((0 + 15) / 2);
		System.out.println(2.0e-6 * 100000000.1);
		System.out.println(true && false || true && true);
	}
}