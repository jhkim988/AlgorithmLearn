import java.util.Scanner;

class Recur{
	static void recur(int n) { // 메서드 안에서 재귀 호출을 여러 번 = genuinely recursive
		if (n > 0) {
			recur(n - 1);
			System.out.println(n);
			recur(n - 2);
		}
	}
	// 하향식 분석 - recur(1)과 recur(2)의 호출이 여러 번 있다. 반드시 효율적인 것은 아니다.
	// 4를 전달하면 recur(3) 실행, 4 출력, recur(2) 실행
	// 3을 전달하면 recur(2) 실행, 3 출력, recur(1) 실행
	// 2를 전달하면 recur(1) 실행, 2 출력, recur(0) 실행
	// 1을 전달하면 recur(0) 실행, 1 출력, recur(-1) 실행
	
	// 상향식 분석
	// n이 양수일 때만 실행하므로 recur(1)을 생각해본다.
	// 1을 전달하면 recur(0) 실행, 1 출력, recur(-1) 실행 - 1만 출력한다.
	// 2를 전달하면 recur(1) 실행, 2 출력, recur(0) 실행 - 1과 2를 출력한다.
	// 3을 전달하면 recur(2) 실행, 3 출력, recur(1) 실행 - 1 2 3 1을 출력한다.
	// 4를 전달하면 recur(3) 실행, 4 출력, recur(2) 실행 - 1 2 3 1 4 1 2를 출력한다.
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요. : ");
		int x = stdIn.nextInt();
		stdIn.close();
		
		recur(x);
	}
}