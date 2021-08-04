class Exercise03_03{
	// Q3 요솟수가 n인 배열 a에서 key와 일치하는 모든 요소의 인덱스를 배열 idx의 맨 앞부터 순서대로 저장하고
	// 일치한 요솟수를 반환하는 메서드를 작성하세요.
	static int searchIdx(int[] a, int n, int key, int[] idx) {
		int count = 0;
		for (int i = 0; i < n; ++i) {
			if (a[i] == key) {
				idx[count++] = i;
			}
		}
		return count;
	}
	public static void main(String[] args) {
		int[] x = {1, 9, 2, 9, 4, 6, 7, 9};
		int num = 8;
		int key = 9;
		int[] idx = new int[num];
		
		System.out.println(searchIdx(x, num, key, idx));
		for (int i = 0; i < num; ++i)
			System.out.print(idx[i]+ " ");
	}
}