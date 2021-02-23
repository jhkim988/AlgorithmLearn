
public class One_Two_ThreeSum {
	public static int count1() {
		int N = 10;
		int[] a = new int[N];
		
		int count = 0; 
		for(int i = 0; i < N; ++i)
			if (a[i] == 0)
				count++;
		
		return count;
	
		// i, count�� ����� �־���Ѵ�.
		// i, count�� 0���� �ʱ�ȭ�ž� �Ѵ�.
		// for loop�� ���鼭 i�� N�� ���ϴ°� N + 1�� �ִ�.
		// a[i] == 0�� �����ϴ� ������ Nȸ �ִ�. �迭�� �����ϴ� �͵� Nȸ �ִ�.
		// count++�� �迭���ҿ� ���� �ٸ���.(�����Ϳ� ������)
	}
	
	public static int count2() {
		int N = 10;
		int[] a = new int[N];
		
		int count = 0;
		for(int i = 0; i < N; ++i)
			for(int j = i + 1; j < N; ++j)
				if (a[i] + a[j] == 0)
					count++;
		
		return count;
		
		// a[i] + a[j] == 0�� �����ϴ� Ƚ��: 1 + 2 + ... + (N-1) = N(N-1)/2
		// �迭�� ������ �� ���� �ϱ� ������ N(N-1)
		// ��� ���� Ƚ���� ���� ��븸ŭ�� ������ ���ؼ� �� ����� ����ص� ������, ���� ��� ���� ���� ���� ������ �ȴ�.
		// �������� a[i] + a[j] == 0�� ���� ��� ����
		
		// ������ ����(n�� ���� ��)���� ���� ���� ���� �����Ѵ�.
		// f(N) ~ g(N) <=> lim f(N)/g(N) = 1
		// N�� ����� ������ ���� ���� ���� ������ �� ����. �׷��� ū N�� ���� ����ð��� �����Ϸ��� ���̴�.
	}
	
	public static int count3(int[] a) {
		int N = a.length;
		int count = 0;
		for(int i = 0; i < N; ++i)
			for(int j = i + 1; j < N; ++j)
				for (int k = j + 1; k < N; ++k) // for�� ���� Ƚ��: (N 3) = N(N-1)(N-2)/6
					if (a[i] + a[j] + a[k] == 0) // array ������ 3ȸ �ϱ� ������ ~ N^3/2
						count++;
		return count;
		
		// 1 + 2 + ... + N = sum(i = 1 to N) i ~ integral(1 to N) x dx ~ N^2/2
		// 1/1 + 1/2 + ... + 1/N = sum(i = 1 to N) 1/i ~ integral(1 to N) 1/x dx = lnN
		// sum sum sum(1) ~ integral integral integral 1 dxdydz ~ N^/6
	}
	
	public static void main(String[] args) {

	}
}
