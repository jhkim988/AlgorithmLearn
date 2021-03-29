
public class One_Two_ThreeSum {
	public static int count1() {
		int N = 10;
		int[] a = new int[N];
		
		int count = 0; 
		for(int i = 0; i < N; ++i)
			if (a[i] == 0)
				count++;
		
		return count;
	
		// i, count가 선언돼 있어야한다.
		// i, count가 0으로 초기화돼야 한다.
		// for loop를 돌면서 i와 N을 비교하는게 N + 1번 있다.
		// a[i] == 0을 수행하는 연산이 N회 있다. 배열에 접근하는 것도 N회 있다.
		// count++는 배열원소에 따라 다르다.(데이터에 의존적)
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
		
		// a[i] + a[j] == 0을 수행하는 횟수: 1 + 2 + ... + (N-1) = N(N-1)/2
		// 배열에 접근을 두 번씩 하기 때문에 N(N-1)
		// 모든 연산 횟수를 세서 비용만큼의 가중을 곱해서 총 비용을 계산해도 되지만, 가장 비싼 연산 세도 많은 도움이 된다.
		// 위에서는 a[i] + a[j] == 0이 가장 비싼 연산
		
		// 유도한 공식(n에 대한 식)에서 낮은 차수 항을 무시한다.
		// f(N) ~ g(N) <=> lim f(N)/g(N) = 1
		// N이 충분히 작으면 낮은 차수 항을 무시할 수 없다. 그러나 큰 N에 대한 수행시간을 예측하려는 것이다.
	}
	
	public static int count3(int[] a) {
		int N = a.length;
		int count = 0;
		for(int i = 0; i < N; ++i)
			for(int j = i + 1; j < N; ++j)
				for (int k = j + 1; k < N; ++k) // for문 진입 횟수: (N 3) = N(N-1)(N-2)/6
					if (a[i] + a[j] + a[k] == 0) // array 접근을 3회 하기 때문에 ~ N^3/2
						count++;
		return count;
		
		// 1 + 2 + ... + N = sum(i = 1 to N) i ~ integral(1 to N) x dx ~ N^2/2
		// 1/1 + 1/2 + ... + 1/N = sum(i = 1 to N) 1/i ~ integral(1 to N) 1/x dx = lnN
		// sum sum sum(1) ~ integral integral integral 1 dxdydz ~ N^/6
	}
	
	public static void main(String[] args) {

	}
}
