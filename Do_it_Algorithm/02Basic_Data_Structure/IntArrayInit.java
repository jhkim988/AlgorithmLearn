class IntArrayInit{
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5};
//		int[] a = new int[] {1, 2, 3, 4, 5}; // ��������� new int[]�� �տ� �ٿ��� ���� �ִ�.
		for (int i = 0; i < a.length; ++i)
			System.out.println("a[" + i + "] = " + a[i]);
	}
}