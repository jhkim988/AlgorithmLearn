
public class Exercise01_01_08 {
	// 1.1.8 아래의 각 코드가 출력하는 값을 구하고 그 이유를 설명하라.
	public static void main(String[] args) {
		// a - char를 출력한다.
		System.out.println('b');
		
		// b - ASCII 코드의 숫자로 변환하여 숫자의 합을 출력한다.
		System.out.println('b' + 'c');
	
		// c - ASCII 코드의 a를 숫자로 변환하여 정수와 더한 후 char로 캐스팅한다. 따라서 e가 출력된다.
		System.out.println((char) ('a' + 4));
	}
}
