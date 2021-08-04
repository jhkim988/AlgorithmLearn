import java.util.Scanner;

class Recur{
	static void recur(int n) { // �޼��� �ȿ��� ��� ȣ���� ���� �� = genuinely recursive
		if (n > 0) {
			recur(n - 1);
			System.out.println(n);
			recur(n - 2);
		}
	}
	// ����� �м� - recur(1)�� recur(2)�� ȣ���� ���� �� �ִ�. �ݵ�� ȿ������ ���� �ƴϴ�.
	// 4�� �����ϸ� recur(3) ����, 4 ���, recur(2) ����
	// 3�� �����ϸ� recur(2) ����, 3 ���, recur(1) ����
	// 2�� �����ϸ� recur(1) ����, 2 ���, recur(0) ����
	// 1�� �����ϸ� recur(0) ����, 1 ���, recur(-1) ����
	
	// ����� �м�
	// n�� ����� ���� �����ϹǷ� recur(1)�� �����غ���.
	// 1�� �����ϸ� recur(0) ����, 1 ���, recur(-1) ���� - 1�� ����Ѵ�.
	// 2�� �����ϸ� recur(1) ����, 2 ���, recur(0) ���� - 1�� 2�� ����Ѵ�.
	// 3�� �����ϸ� recur(2) ����, 3 ���, recur(1) ���� - 1 2 3 1�� ����Ѵ�.
	// 4�� �����ϸ� recur(3) ����, 4 ���, recur(2) ���� - 1 2 3 1 4 1 2�� ����Ѵ�.
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.print("������ �Է��ϼ���. : ");
		int x = stdIn.nextInt();
		stdIn.close();
		
		recur(x);
	}
}