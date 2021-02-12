public class IntSet {
	private int max; // 집합의 최대 크기
	private int num; // 집합의 요소 개수
	private int[] set; // 집합을 저장할 배열

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
			set[idx] = set[--num]; // 마지막 요소를 삭제한 곳으로 옮긴다.
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

		for (int i = 0; i < num; ++i) { // set[i] 고정
			int j = 0;
			for (; j < s.num; ++j)
				if (set[i] == s.set[j]) // set[i]가 s에 포함되는지? (즉 s.set[j]와 같은 게 있는지?)
					break;
			if (j == s.num) // s.set을 모두 순회했는데도 set[i]가 없다면
				return false;
		}
		return true;
	}

	// 집합 s1과 s2의 합집합을 복사합니다.
	public void unionOf(IntSet s1, IntSet s2) {
		copyFrom(s1);
		for (int i = 0; i < s2.num; ++i)
			add(s2.set[i]);
	}

	// "{a, b, c}" 형식의 문자열로 표현을 바꿉니다.
	public String toString() {
		StringBuffer temp = new StringBuffer("{");
		for (int i = 0; i < num; ++i)
			temp.append(set[i] + " ");
		temp.append("}");
		return temp.toString();
	}

	// Q1 공집합인지 판단하는 메서드, 가득 찼는지 판단하는 메서드, 모든 요소를 삭제하는 메서드를 추가하세요
	public boolean isEmpty() {
		return num == 0;
	}

	public boolean isFull() {
		return num == max;
	}

	public void clear() {
		num = 0;
	}

	// Q2 집합 s의 모든 요소를 추가하는 메서드, 집합 s에 들어있는 요소만 남기고 들어있지 않은 요소는 삭제하는 메서드
	// 집합 s에 들어있는 요소를 삭제하는 메서드를 추가하세요.
	// 메서드의 실행에 따라 집합이 "변경"되는 경우에는 true를, 그렇지 않은 경우에는 false를 반환하도록 하세요
	public boolean add(IntSet s) {
		// 공집합을 합치면 false이다.
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

	// Q3 집합 s의 부분집합인지 판단하는 메서드, 집합 s의 진부분 집합인지 판단하는 메서드를 작성하세요
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

	// Q4 s1과 s2의 교집합, 차집합을 복사하는 메서드
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