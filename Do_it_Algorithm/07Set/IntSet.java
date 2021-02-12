public class IntSet {
	private int max; // ������ �ִ� ũ��
	private int num; // ������ ��� ����
	private int[] set; // ������ ������ �迭

	IntSet(int capacity) {
		num = 0;
		max = capacity;
		try {
			set = new int[max];
		} catch (OutOfMemoryError e) {
			max = 0;
		}
	}

	public int capacity() {
		return max;
	}

	public int size() {
		return num;
	}

	public int indexOf(int n) {
		for (int i = 0; i < num; ++i)
			if (set[i] == n)
				return i;
		return -1;
	}

	public boolean contains(int n) {
		return indexOf(n) == -1 ? false : true;
	}

	public boolean add(int n) {
		if (num >= max || contains(n) == true)
			return false;
		else {
			set[num++] = n;
			return true;
		}
	}

	public boolean remove(int n) {
		int idx;

		if (num <= 0 || (idx = indexOf(n)) == -1)
			return false;
		else {
			set[idx] = set[--num]; // ������ ��Ҹ� ������ ������ �ű��.
			return true;
		}
	}

	public void copyTo(IntSet s) {
		int n = (s.max < num) ? s.max : num;
		for (int i = 0; i < n; ++i)
			s.set[i] = set[i];
		s.num = n;
	}

	public void copyFrom(IntSet s) {
		int n = (max < s.num) ? max : s.num;
		for (int i = 0; i < n; ++i)
			set[i] = s.set[i];
		num = n;
	}

	public boolean equalTo(IntSet s) {
		if (num != s.num)
			return false;

		for (int i = 0; i < num; ++i) { // set[i] ����
			int j = 0;
			for (; j < s.num; ++j)
				if (set[i] == s.set[j]) // set[i]�� s�� ���ԵǴ���? (�� s.set[j]�� ���� �� �ִ���?)
					break;
			if (j == s.num) // s.set�� ��� ��ȸ�ߴµ��� set[i]�� ���ٸ�
				return false;
		}
		return true;
	}

	// ���� s1�� s2�� �������� �����մϴ�.
	public void unionOf(IntSet s1, IntSet s2) {
		copyFrom(s1);
		for (int i = 0; i < s2.num; ++i)
			add(s2.set[i]);
	}

	// "{a, b, c}" ������ ���ڿ��� ǥ���� �ٲߴϴ�.
	public String toString() {
		StringBuffer temp = new StringBuffer("{");
		for (int i = 0; i < num; ++i)
			temp.append(set[i] + " ");
		temp.append("}");
		return temp.toString();
	}

	// Q1 ���������� �Ǵ��ϴ� �޼���, ���� á���� �Ǵ��ϴ� �޼���, ��� ��Ҹ� �����ϴ� �޼��带 �߰��ϼ���
	public boolean isEmpty() {
		return num == 0;
	}

	public boolean isFull() {
		return num == max;
	}

	public void clear() {
		num = 0;
	}

	// Q2 ���� s�� ��� ��Ҹ� �߰��ϴ� �޼���, ���� s�� ����ִ� ��Ҹ� ����� ������� ���� ��Ҵ� �����ϴ� �޼���
	// ���� s�� ����ִ� ��Ҹ� �����ϴ� �޼��带 �߰��ϼ���.
	// �޼����� ���࿡ ���� ������ "����"�Ǵ� ��쿡�� true��, �׷��� ���� ��쿡�� false�� ��ȯ�ϵ��� �ϼ���
	public boolean add(IntSet s) {
		// �������� ��ġ�� false�̴�.
		boolean flag = false;
		for (int i = 0; i < s.num; ++i)
			if (add(s.set[i]))
				flag = true;
		return flag;
	}

	public boolean retain(IntSet s) {
		boolean flag = false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i])) {
				remove(set[i]);
				flag = true;
			}
		return flag;
	}

	public boolean remove(IntSet s) {
		boolean flag = false;
		for (int i = 0; i < num; ++i)
			if (s.contains(set[i]))
				if (remove(set[i]))
					flag = true;
		return flag;
	}

	// Q3 ���� s�� �κ��������� �Ǵ��ϴ� �޼���, ���� s�� ���κ� �������� �Ǵ��ϴ� �޼��带 �ۼ��ϼ���
	public boolean isSubsetOf(IntSet s) {
		// num <= s.num
		if (num > s.num)
			return false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i]))
				return false;
		return true;
	}

	public boolean isProperSubsetOf(IntSet s) {
		// num < s.num
		if (num >= s.num)
			return false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i]))
				return false;
		return true;
	}

	// Q4 s1�� s2�� ������, �������� �����ϴ� �޼���
	public void intersectionOf(IntSet s1, IntSet s2) {
		clear();
		s1.retain(s2);
		copyTo(s1);
	}
	public void differenceOf(IntSet s1, IntSet s2) {
		clear();
		s1.remove(s2);
		copyTo(s1);
	}
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}