import java.util.Scanner;

class Max3{
	public static void  main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("�� ������ �ִ��� ���մϴ�.");
		System.out.print("a�� �� : "); int a = stdIn.nextInt();
		System.out.print("b�� �� : "); int b = stdIn.nextInt();
		System.out.print("c�� �� : "); int c = stdIn.nextInt();
		stdIn.close();
		
		int max = a;
		if (max < b) max = b;
		if (max < c) max = c;
		
		System.out.println("�ִ��� " + max + " �Դϴ�.");
		
		// nextInt(), nextBoolean, nextByte(), nextSort(), nextLong(), nextFloat(), nextDouble(), next(): ���ڿ�, nextLine()
		
	}
}