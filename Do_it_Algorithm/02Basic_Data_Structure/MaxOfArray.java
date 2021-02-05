import java.util.Scanner;

class MaxOfArray{
	static int maxOf(int[] a) {
		int max = a[0];
		// traverse: �迭�� ��Ҹ� �ϳ��� ���ʷ� ���캸�� ����
		for (int i = 1; i < a.length; ++i) {
			if (a[i] > max) max = a[i];
		}
		return max;
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("Ű�� �ִ��� ���մϴ�.");
		
		System.out.println("��� �� : " );
		int num = stdIn.nextInt();
		
		int[] height = new int[num];
		
		for (int i = 0; i < num; ++i) {
			System.out.print("height[" + i + "] : ");
			height[i] = stdIn.nextInt();
		}
		stdIn.close();
		System.out.println("�ִ��� " + maxOf(height) + " �Դϴ�.");
		// main()���� maxOf() �Լ��� ȣ���Ѵ�. ���ڷ� height �迭�� �Ѱ��ش�.
		// maxOf()�Լ� ���������� int[] a = height; �� ����ȴ�. �� a�� height�� ���� �迭�� �����ϰ� �ȴ�.
		// �� maxOf()�� ������ ���� �迭 ��ü�� ���� '����'�̴�.
		// ���� maxOf()���ο��� a.length, a[i] ���� ���� height�� ���� ������ ������ �� �ִ�.
		
		// ���� ������ ����
		// public: ��� ���� ���
		// protected: ���� ��Ű��(����)�� ��ü, ��� ������ ��ü ���
		// default: ���� ��Ű��(����)�� ��ü ���
		// private: ������ ��ü �ȿ����� ���
		
		// ���� �������� ���
		// Ŭ���� - public default
		// ������ - public, protected, default, private
		// ��� ���� - public, protected, default, private
		// ���� ���� - ���� ������ ��� ����
	}
}