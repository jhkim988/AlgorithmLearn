class IntTwoSideStack {
	int max; // 최대 용량
	int ptrA; // index 0과 가까운 스택의 포인터
	int ptrB; // index max-1과 가까운 스택의 포인터
	int[] stk;
	
	// 예외처리
	public static class EmptyIntTwoSideStackException extends RuntimeException {
		public EmptyIntTwoSideStackException() {
			
		}
	}
	
	public static class OverflowIntTwoSideStackException extends RuntimeException {
		public OverflowIntTwoSideStackException () {
			
		}
	}
	
	IntTwoSideStack (int capacity) {
		max = capacity;
		ptrA = 0;
		ptrB = max - 1;
		stk = new int[max];
	}
	
	public int pushA (int x) throws OverflowIntTwoSideStackException {
		if (ptrA > ptrB - 1)
			throw new OverflowIntTwoSideStackException();
		return stk[ptrA++] = x;
	}
	
	public int pushB (int x) throws OverflowIntTwoSideStackException {
		if (ptrA > ptrB - 1)
			throw new OverflowIntTwoSideStackException();
		return stk[ptrB--] = x;
	}
	
	public int popA() throws EmptyIntTwoSideStackException {
		if (ptrA <= 0)
			throw new EmptyIntTwoSideStackException();
		return stk[--ptrA];
	}
	
	public int popB() throws EmptyIntTwoSideStackException {
		if (ptrB >= max)
			throw new EmptyIntTwoSideStackException();
		return stk[++ptrB];
	}
	
	public int peekA() throws EmptyIntTwoSideStackException {
		if (ptrA <= 0)
			throw new EmptyIntTwoSideStackException();
		return stk[ptrA - 1];
	}
	
	public int peekB() throws EmptyIntTwoSideStackException {
		if (ptrB >= max)
			throw new EmptyIntTwoSideStackException();
		return stk[ptrB + 1];
	}
	
	public int indexAOf(int key) {
		for(int i = ptrA - 1; i >=0; --i)
			if (stk[i] == key)
				return i;
		return -1;
	}
	
	public int indexBOf(int key) {
		for(int i = ptrB + 1; i <= max; ++i)
			if (stk[i] == key)
				return i;
		return -1;
	}
	
	public void clear() {
		ptrA = 0;
		ptrB = max;
	}
	
	public int capacityA() {
		return ptrB + 1;
	}
	
	public int capacityB() {
		return ptrA - 1;
	}
	
	public int sizeA() {
		return ptrA;
	}
	
	public int sizeB() {
		return max - ptrB;
	}
	
	public boolean isAEmpty() {
		return ptrA <= 0;
	}
	
	public boolean isBEmpty() {
		return ptrB >= max;
	}
	
	public boolean isFull() {
		return ptrA == ptrB - 1;
	}
	
	public void dumpA() {
		if (ptrA == 0)
			System.out.println("스택 A가 비어있습니다.");
		else
			for (int i = 0; i < ptrA; ++i)
				System.out.print(stk[i] + " ");
		System.out.println();
	}
	
	public void dumpB() {
		if (ptrB == max)
			System.out.println("스택 B가 비어있습니다.");
		else
			for (int i = ptrB + 1; i <= max; ++i)
				System.out.print(stk[i] + " ");
		System.out.println();
	}
}

class Exercise04_03{
	public static void main(String[] args) {
		System.out.println("Hello, World!");
	}
}