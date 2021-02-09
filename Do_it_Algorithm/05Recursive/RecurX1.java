import java.util.Scanner;

class RecurX1 {
//	static void recur(int n) {
//		while (n > 0) {
//			recur(n - 1);
//			System.out.println(n);
//			n = n - 2;
//		}
//	}
	// ������� ����
	// recur(n - 2); ��� ���� ���ڷ� n - 2�� �����Ͽ� recur�� ȣ���Ѵ�.
	// ��, n�� n - 2�� ������Ʈ �ϰ� �޼����� ������������ ���ư���.
	// �޼��� ��ü�� while������ ���ΰ� �������� n = n - 2�� ������Ʈ �ϰ� �Ѵ�.
	
	// ������Ͱ� �ƴ� ��ʹ� ���� �����ϱ� ��ƴ�.
	// n�� ����ϱ� ���� recur(n - 1)�� �����ؾ� �ϱ� �����̴�.
	// n = 4�� ��� recur(3)�� ó���� �Ǵ� ���� 4�� �����ϰ� �־�� �Ѵ�.
	// ���� recur(n - 1)�� "n = n - 1�� ������Ʈ �� �� �޼����� ó������ ���ư���"�� ���Ѵ�. ������ n���� ��� �����ؾ��ϱ� ����
	// recur(n - 1)�� ó���� ������ �ٽ� �����ߴ� n�� ������ �� ���� ����ؾ� �Ѵ�.
	// ������ �̿��Ͽ� ����������� ������ �� �ִ�.
	
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
	// - pop() - 1 ��� - pop() - 2 ��� - pop() - 3 ���
	// - push(1) - pop() - 1 ��� - pop() - 4 ��� - push(2) - push(1)
	// - pop() - 1 ��� - pop() - 2��� 
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);

		System.out.print("������ �Է��ϼ���. : ");
		int x = stdIn.nextInt();
		stdIn.close();

		recur(x);
	}
}