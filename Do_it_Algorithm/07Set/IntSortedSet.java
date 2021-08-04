public class IntSortedSet {
	// ������ ��Ұ� �׻� ������������ ���ĵǵ��� Ŭ������ �����غ�����.
	// ������������ ��Ҹ� �����صθ� ���� �˻��� ������ �� �־� ���մϴ�.
	private int max; // ������ �ִ� ũ��
	private int num; // ������ ��� ����
	private int[] set; // ������ ������ �迭

	IntSortedSet(int capacity) {
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

	// �����˻����� ����
	public int indexOf(int n) {
		int pl = 0;
		int pr = num - 1;

		do {
			int pc = (pl + pr) / 2;
			if (set[pc] < n)
				pl = pc + 1;
			else if (n < set[pc])
				pr = pc - 1;
			else
				return pc;
		} while (pl <= pr);

		return -pl - 1; // ���� ����Ʈ
	}

	public boolean contains(int n) {
		return indexOf(n) == -1 ? false : true;
	}

	public boolean add(int n) {
		int idx;
		if (num >= max || (idx = indexOf(n)) >= 0)
			return false;
		else {
			idx = -(idx + 1);
			num++;
			for (int i = num - 1; i > idx; --i)
				set[i] = set[i - 1];
			set[idx] = n;
			return true;
		}
	}

	public boolean remove(int n) {
		int idx;

		if (num <= 0 || (idx = indexOf(n)) < 0)
			return false;
		else {
			num--;
			for (int i = idx; i <num; ++i)
				set[i] = set[i + 1];
			return true;
		}
	}

	public void copyTo(IntSortedSet s) {
		int n = (s.max < num) ? s.max : num;
		for (int i = 0; i < n; ++i)
			s.set[i] = set[i];
		s.num = n;
	}

	public void copyFrom(IntSortedSet s) {
		int n = (max < s.num) ? max : s.num;
		for (int i = 0; i < n; ++i)
			set[i] = s.set[i];
		num = n;
	}

	public boolean equalTo(IntSortedSet s) {
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
	public void unionOf(IntSortedSet s1, IntSortedSet s2) {
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
	public boolean add(IntSortedSet s) {
		// �������� ��ġ�� false�̴�.
		boolean flag = false;
		for (int i = 0; i < s.num; ++i)
			if (add(s.set[i]))
				flag = true;
		return flag;
	}

	public boolean retain(IntSortedSet s) {
		boolean flag = false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i])) {
				remove(set[i]);
				flag = true;
			}
		return flag;
	}

	public boolean remove(IntSortedSet s) {
		boolean flag = false;
		for (int i = 0; i < num; ++i)
			if (s.contains(set[i]))
				if (remove(set[i]))
					flag = true;
		return flag;
	}

	// Q3 ���� s�� �κ��������� �Ǵ��ϴ� �޼���, ���� s�� ���κ� �������� �Ǵ��ϴ� �޼��带 �ۼ��ϼ���
	public boolean isSubsetOf(IntSortedSet s) {
		// num <= s.num
		if (num > s.num)
			return false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i]))
				return false;
		return true;
	}

	public boolean isProperSubsetOf(IntSortedSet s) {
		// num < s.num
		if (num >= s.num)
			return false;
		for (int i = 0; i < num; ++i)
			if (!s.contains(set[i]))
				return false;
		return true;
	}

	// Q4 s1�� s2�� ������, �������� �����ϴ� �޼���
	public void intersectionOf(IntSortedSet s1, IntSortedSet s2) {
		clear();
		s1.retain(s2);
		copyTo(s1);
	}

	public void differenceOf(IntSortedSet s1, IntSortedSet s2) {
		clear();
		s1.remove(s2);
		copyTo(s1);
	}

	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}