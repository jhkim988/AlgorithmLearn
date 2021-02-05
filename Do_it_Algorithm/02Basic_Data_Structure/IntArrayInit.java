class IntArrayInit{
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
//		int[] a = new int[] {1, 2, 3, 4, 5}; // 명시적으로 new int[]를 앞에 붙여줄 수도 있다.
		for (int i = 0; i < a.length; ++i)
			System.out.println("a[" + i + "] = " + a[i]);
	}
}