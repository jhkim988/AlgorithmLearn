import java.util.Scanner;

class Digits{
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int no;
		
		System.out.println("2�ڸ� ������ �Է��ϼ���.");
		
		do {
			System.out.println("�Է� : ");
			no = stdIn.nextInt();
		} while (no < 10 || no > 99);
		stdIn.close();
		
		System.out.println("���� no�� ���� " + no + "��(��) �Ǿ����ϴ�.");
		
		// ������
		// no > 10 || no < 99 : ���� no > 10�� true ��� ���� no < 99�� ������� �ʾƵ� true �̹Ƿ� ������� �ʴ´�.
		// no > 10 && no < 99 : ���� no > 10�� false ��� ���� no < 99�� ������� �ʾƵ� false �̹Ƿ� ������� �ʴ´�.
	}
}