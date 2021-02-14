class AryLinkedList<E>{
	// �����ͷ� ���Ḯ��Ʈ -> ��� ���� ������ ������ �̵� ���� ����,
	// �׷��� ���� ������ ������ ������ ���� ��ü�� ���� �޸� ������ ����� �����ϴ� ������ �ʿ��ߴ�.
	// ������ ���� ũ�� �ٲ��� �ʰ�, ������ ���� �ִ��� �̸� �� �� �ִٸ� �迭�� ����� ȿ�������� ���� ����Ʈ�� ����� �� �ִ�.
	
	// �迭 �ε��� 0 1 2 3 4 5 6 7
	// �迭 ��Ұ� D A E C B F - -
	// Ŀ��      2 4 5 0 3 -1
	
	// ���� ����� �̿��ϸ� ����� ����, ���� �� ��Ҹ� �ű� �ʿ䰡 ����.

	class Node<E> {
		private E data;
		private int next; // ����Ʈ�� ���� ������
		private int dnext; // free ����Ʈ�� ���� ������
		
		void set(E data, int next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private Node<E>[] n;
	private int size; // ����Ʈ�� �뷮(���� ū ������ ��)
	private int max; // ������� ���� record
	private int head;
	private int crnt;
	private int deleted; // free ����Ʈ�� �Ӹ� ���
	private static final int NULL = -1; // ���� ��� ���� / ����Ʈ�� ���� ��

	public AryLinkedList(int capacity) {
		head = crnt = max = deleted = NULL;
		try {
			n = new Node[capacity];
			for(int i = 0; i < capacity; ++i)
				n[i] = new Node<E>();
			size = capacity;
		} catch(OutOfMemoryError e) {
			size = 0;
		}
	}
	
	// ������ �����ϴ� record�� �ε����� ����
	private int getInsertIndex() {
		if (deleted == NULL) {
			if (max < size)
				return max++;
			else 
				return NULL;
		} else {
			int rec = deleted;
			deleted = n[rec].dnext;
			return rec;
		}
	}
}