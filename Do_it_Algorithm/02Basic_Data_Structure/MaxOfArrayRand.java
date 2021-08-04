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
		
		System.out.println("키의 최댓값을 구합니다.");
		System.out.println("사람 수 : " );
		int num = stdIn.nextInt();
		stdIn.close();
		
		int[] height = new int[num];
		
		for (int i = 0; i < num; ++i) {
			height[i] = 100 + rand.nextInt(90); // rand.nextInt(num); // 0 ~ num - 1까지의 정수를 랜덤하게 반환한다.
			System.out.println("height[" + i + "] : " + height[i]);
		}
		System.out.println("최댓값은 " + maxOf(height) + " 입니다.");
		
		// Random class의 인스턴스는 의사 난수를 생성한다.
		// Random rand = new Random(seed); // 선형합동법이라는 계산법에 의해 난수가 생성된다.
		// java.lang.Math 클래스에서도 난수를 생성하는 라이브러리를 제공
	}
}