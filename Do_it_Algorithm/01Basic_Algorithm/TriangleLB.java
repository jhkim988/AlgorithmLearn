import java.util.Scanner;

class TriangleLB{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int n;
		
		System.out.println("왼쪽 아래가 직각인 이등변 삼각형을 출력합니다.");
		do {
			System.out.println("몇 단 삼각형입니까? : ");
			n = stdIn.nextInt();
		} while (n <= 0);
		stdIn.close();
		
		for (int i = 0; i < n; ++i) {
			for(int j = 0; j <= i; ++j)
				System.out.print('*');
			System.out.println();
		}
	}
}