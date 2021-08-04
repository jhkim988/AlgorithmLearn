class OpenHash<K, V> {
	enum Status {
		OCCUPIED, EMPTY, DELETED
	};

	static class Bucket<K, V> {
		private K key;
		private V data;
		private Status stat;

		Bucket() {
			stat = Status.EMPTY;
		}

		void set(K key, V data, Status stat) {
			this.key = key;
			this.data = data;
			this.stat = stat;
		}

		void setStat(Status stat) {
			this.stat = stat;
		}

		K getKey() {
			return key;
		}

		V getValue() {
			return data;
		}

		public int hashCode() {
			return key.hashCode();
		}
	}

	private int size;
	private Bucket<K, V>[] table;

	public OpenHash(int size) {
		try {
			table = new Bucket[size];
			for (int i = 0; i < size; ++i)
				table[i] = new Bucket<K, V>(); // 초기화
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}
	}

	public int hashValue(Object key) {
		return key.hashCode() % size;
	}

	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	private Bucket<K, V> searchNode(K key) {
		int hash = hashValue(key);
		Bucket<K, V> p = table[hash];

		for (int i = 0; p.stat != Status.EMPTY && i < size; ++i) {
			if (p.stat == Status.OCCUPIED && p.getKey().equals(key))
				return p;
			hash = rehashValue(hash);
			p = table[hash];
		}
		return null;
	}

	public V search(K key) {
		Bucket<K, V> p = searchNode(key);
		if (p != null)
			return p.getValue();
		return null;
	}

	public int add(K key, V data) {
		if (search(key) != null)
			return 1;

		int hash = hashValue(key);
		Bucket<K, V> p = table[hash];
		for (int i = 0; i < size; ++i) {
			if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
				p.set(key, data, Status.OCCUPIED);
				return 0;
			}
			hash = rehashValue(hash);
			p = table[hash];
		}
		return 1;
	}
	
	public int remove(K key) {
		Bucket<K, V> p = searchNode(key);
		if (p == null)
			return 1;
		
		p.setStat(Status.DELETED);
		return 0;
	}
	
	public void dump() {
		for (int i = 0; i < size; ++i) {
			System.out.printf("%02d ", i);
			switch (table[i].stat) {
			case OCCUPIED:
				System.out.printf("%s (%s)\n", table[i].getKey(), table[i].getValue());
				break;
			case EMPTY:
				System.out.println("-- 미등록 --");
				break;
			case DELETED:
				System.out.println("-- 삭제 마침 --");
				break;
			}
		}
	}
}