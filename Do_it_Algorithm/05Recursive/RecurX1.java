import java.util.Scanner;

class RecurX1 {
//	static void recur(int n) {
//		while (n > 0) {
//			recur(n - 1);
//			System.out.println(n);
//			n = n - 2;
//		}
//	}
	// 꼬리재귀 제거
	// recur(n - 2); 라는 것은 인자로 n - 2를 전달하여 recur를 호출한다.
	// 즉, n을 n - 2로 업데이트 하고 메서드의 시작지점으로 돌아간다.
	// 메서드 전체를 while문으로 감싸고 마지막에 n = n - 2로 업데이트 하게 한다.
	
	// 꼬리재귀가 아닌 재귀는 쉽게 제거하기 어렵다.
	// n을 출력하기 전에 recur(n - 1)을 수행해야 하기 때문이다.
	// n = 4인 경우 recur(3)의 처리가 되는 동안 4를 저장하고 있어야 한다.
	// 따라서 recur(n - 1)을 "n = n - 1로 업데이트 한 후 메서드의 처음으로 돌아가기"를 못한다. 현재의 n값을 잠시 저장해야하기 때문
	// recur(n - 1)의 처리가 끝나면 다시 저장했던 n을 꺼내서 그 값을 출력해야 한다.
	// 스택을 이용하여 비재귀적으로 구현할 수 있다.
	
	static void recur(int n) {
		IntStack s = new IntStack(n);
		
		while (true) {
			if (n > 0) {
				s.push(n);
				n = n - 1;
				continue;
			}
			if (s.isEmpty() != true) {
				n = s.pop();
				System.out.println(n);
				n = n - 2;
				continue;
			}
			break;
		}
	}
	
	// recur(4) - push(4) - push(3) - push(2) - push(1)
	// - pop() - 1 출력 - pop() - 2 출력 - pop() - 3 출력
	// - push(1) - pop() - 1 출력 - pop() - 4 출력 - push(2) - push(1)
	// - pop() - 1 출력 - pop() - 2출력 
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("정수를 입력하세요. : ");
		int x = stdIn.nextInt();
		stdIn.close();

		recur(x);
	}
}