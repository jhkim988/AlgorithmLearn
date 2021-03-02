
public class Exercise01_01_26 {
	// 1.1.26
	// 아래 코드에서 변수 a, b, c, t는 모두 같은 숫자 타입이라고 하자.
	// 아래의 코드가 어떻게 해서 a, b, c에 내림차순으로 숫자를 정렬하여 바꿔넣는지 설명하라
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
