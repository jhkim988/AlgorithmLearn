import java.util.Scanner;
import java.util.Random;

class MaxOfArrayRand{
	static int maxOf(int[] a) {
		int max = a[0];
		for (int i = 1; i < a.length; ++i)
			if (a[i] > max) max = a[i];
		return max;
	}
	
	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("Ű�� �ִ��� ���մϴ�.");
		System.out.println("��� �� : " );
		int num = stdIn.nextInt();
		stdIn.close();
		
		int[] height = new int[num];
		
		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1������ ������ �����ϰ� ��ȯ�Ѵ�.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("�ִ��� " + maxOf(height) + " �Դϴ�.");
		
		// Random class�� �ν��Ͻ��� �ǻ� ������ �����Ѵ�.
		// Random rand = new Random(seed); // �����յ����̶�� ������ ���� ������ �����ȴ�.
		// java.lang.Math Ŭ���������� ������ �����ϴ� ���̺귯���� ����
	}
}