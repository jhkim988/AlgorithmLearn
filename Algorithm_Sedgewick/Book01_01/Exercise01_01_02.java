
public class Exercise01_01_02 {
	// 1.1.2 아래 각 표현식의 결과 데이터 타입은 무엇인가?
	// a. (1 + 2.236) / 2
	// b. 1 + 2 + 3 + 4.0
	// c. 4.1 >= 4
	// d. 1 + 2 + "3"
	
	// sol)
	// a - double, double로 변환돼 계산된다.
	// b - double
	// c - boolean
	// d - String
	public static void main(String[] args) {
		System.out.println((1 + 2.236) / 2);
		System.out.println(1 + 2 + 3 + 4.0);
		System.out.println(4.1 >= 4);
		System.out.println(1 + 2 + "3");
	}	
}
