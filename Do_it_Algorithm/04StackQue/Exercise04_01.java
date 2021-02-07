import java.util.Scanner;

class Exercise04_01 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntStack s = new IntStack(64);

		while (true) {
			System.out.println("현재 데이터 수 : " + s.size() + " / " + s.capacity());
			System.out.print("(1) 푸시 (2) 팝 (3) 피크" + "(4) 덤프 (5) 검색 (6) 초기화 (7) 전체용량 (8) 현재용량"
					+ "(9) 스택이 비었습니까? (10) 스택이 가득찼습니까? (0) 종료 : ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			int x;
			switch (menu) {
			case 1:
				System.out.print("데이터 : ");
				x = stdIn.nextInt();
				try {
					s.push(x);
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("스택이 가득찼습니다.");
				}
				break;
			case 2:
				try {
					x = s.pop();
					System.out.println("팝한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("피크한 데이터는 " + x + "입니다.");
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("스택이 비어있습니다.");
				}
				break;
			case 4:
				s.dump();
				break;
			case 5:
				if (s.isEmpty()) {
					System.out.println("스택이 비어있습니다.");
					break;
				}
				System.out.print("검색할 데이터 : ");
				int key = stdIn.nextInt();
				int idx = s.indexOf(key);
				System.out.println(key + "은(는) x[" + idx + "]에 있습니다.");
				break;
			case 6:
				s.clear();
				break;
			case 7:
				System.out.println("전체 용량은 " + s.capacity() + "입니다.");
				break;
			case 8:
				System.out.println("현재 용량은 " + s.size() + "입니다.");
				break;
			case 9:
				if (s.isEmpty())
					System.out.println("스택이 비어있습니다.");
				else
					System.out.println("스택이 비어있지 않습니다.");
				break;
			case 10:
				if (s.isFull())
					System.out.println("스택이 가득찼습니다.");
				else
					System.out.println("스택이 가득차지 않았습니다.");
				break;
			}
		}
		stdIn.close();
	}
}