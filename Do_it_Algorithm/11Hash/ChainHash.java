class ChainHash<K, V> {
	class Node<K, V> {
		private K key;
		private V data;
		private Node<K, V> next;

		Node(K key, V data, Node<K, V> next) {
			this.key = key;
			this.data = data;
			this.next = next;
		}

		K getKey() {
			return key;
		}

		V getValue() {
			return data;
		}

		public int hashCode() {
			return key.hashCode(); // 키의 해시값을 반환
		}
	}

	private int size;
	private Node<K, V>[] table;

	public ChainHash(int capacity) {
		try {
			table = new Node[capacity]; // null로 초기화 돼있다.
			this.size = capacity;
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}
	}

	// 해시값 구함
	public int hashValue(Object key) {
		return key.hashCode() % size;
	}

	public V search(K key) {
		int hash = hashValue(key);
		Node<K, V> p = table[hash];

		while (p != null) {
			if (p.getKey().equals(key)) {
				return p.getValue();
			}
			p = p.next;
		}
		return null;
	}

	public int add(K key, V data) {
		int hash = hashValue(key);
		Node<K, V> p = table[hash];
		while (p != null) {
			if (p.getKey().equals(key)) // 키 값이 이미 등록됨
				return 1;
			p = p.next;
		}
		Node<K, V> temp = new Node<K, V>(key, data, table[hash]); // 연결리스트의 머리노드에 추가한다.
		table[hash] = temp;
		return 0;
	}

	public int remove(K key) {
		int hash = hashValue(key);
		Node<K, V> p = table[hash]; // 선택 노드
		Node<K, V> pp = null; // 바로 앞의 선택노드
		while (p != null) {
			if (p.getKey().equals(key)) {
				if (pp == null)
					table[hash] = p.next;
				else
					pp.next = p.next;
				return 0;
			}
			pp = p;
			p = p.next;
		}
		return 1;
	}
	
	public void dump() {
		for(int i = 0; i < size; ++i) {
			Node<K, V> p = table[i];
			System.out.printf("%02d ", i);
			while (p != null) {
				System.out.printf("→ %s (%s) ", p.getKey(), p.getValue());
				p = p.next;
			}
			System.out.println();
		}
	}
}