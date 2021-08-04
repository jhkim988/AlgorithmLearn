import java.util.Scanner;

class JudgeSign{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요.");
		int n = stdIn.nextInt();
		
		if (n > 0) {
			System.out.println("이 수는 양수입니다."); // 1
		} else if (n < 0) {
			System.out.println("이 수는 음수입니다."); // 2
		} else {
			System.out.println("이 수는 0 입니다."); // 3
		}
		
		// 1, 2, 3 중에 실행되는 부분은 단 한 가지다.
		// 프로그램이 세 가지로 분기되기 때문이다.
		
		// 1, 2, 3 숫자 중 하나를 입력받아, 입력 받은 숫자가 무엇인지 출력하는 프로그램을 생각해보자.
		int m = stdIn.nextInt();
		stdIn.close();
		
		if (m == 1)
			System.out.println("이 수는 1입니다.");
		else if (m == 2)
			System.out.println("이 수는 2입니다.");
		else if (m == 3)
			System.out.println("이 수는 3입니다.");
//		else
//			;
		// 실제로는 4가지로 분기한다.
	}
}