import java.util.Comparator;

class BinTree<K, V> {
	static class Node<K, V> {
		private K key;
		private V data;
		private Node<K, V> left;
		private Node<K, V> right;

		Node(K key, V data, Node<K, V> left, Node<K, V> right) {
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}

		K getKey() {
			return key;
		}

		V getValue() {
			return data;
		}

		void print() {
			System.out.println(data);
		}
	}

	private Node<K, V> root;
	private Comparator<? super K> comparator = null;

	// empty tree ��� ��Ʈ�� �����Ƿ� root�� null�̴�.
	public BinTree() {
		// �ڿ������� ���� �����Ѵ�. K�� ���� Comparable �������̽��� �����ϰ� �ִ� Integer, String Ŭ���� � �˸´�.
		root = null;
	}

	public BinTree(Comparator<? super K> c) {
		this(); // ���� ���� �����ڸ� ȣ���Ѵ�.
		comparator = c; // ���޹��� ���ڿ� ���� ��Ұ��� �Ǵ�
	}

	private int comp(K key1, K key2) {
		return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
		// null�� ���, Comparable<K>�� ����ȯ�ϰ� compareTo�޼��带 ȣ���Ͽ� ���Ѵ�.
		// null�� �ƴ� ���, �����ڿ��� ���ڷ� ���� ���ڷ� ���Ѵ�.
	}

	public V search(K key) {
		Node<K, V> p = root;
		while (true) {
			if (p == null)
				return null;
			int cond = comp(key, p.getKey());
			if (cond == 0) {
				return p.getValue();
			} else if (cond < 0) {
				p = p.left;
			} else
				p = p.right;
		}
	}

	// node�� ��Ʈ�� �ϴ� ����Ʈ���� ��� <K, V>�� ����
	public void addNode(Node<K, V> node, K key, V data) {
		int cond = comp(key, node.getKey());
		if (cond == 0)
			return;
		else if (cond < 0) {
			if (node.left == null)
				node.left = new Node<K, V>(key, data, null, null);
			else
				addNode(node.left, key, data);
		} else {
			if (node.right == null)
				node.right = new Node<K, V>(key, data, null, null);
			else
				addNode(node.right, key, data);
		}
	}

	// ��带 ����
	public void add(K key, V data) {
		if (root == null)
			root = new Node<K, V>(key, data, null, null);
		else
			addNode(root, key, data);
	}

	// Ű ���� key�� ��带 ����
	public boolean remove(K key) {
		Node<K, V> p = root;
		Node<K, V> parent = null;
		boolean isLeftChild = true; // p�� �θ��� ���� �ڽ� ����ΰ�?

		// Ű ���� key�� ��� ã��
		while (true) {
			if (p == null)
				return false;
			int cond = comp(key, p.getKey());
			if (cond == 0)
				break;
			else {
				parent = p;
				if (cond < 0) {
					isLeftChild = true;
					p = p.left;
				} else {
					isLeftChild = false;
					p = p.right;
				}
			}
		}

		if (p.left == null) {// p���� ���� �ڽ��� ����
			if (p == root)
				root = p.right;
			else if (isLeftChild) // p�� �θ��� ���� �ڽ��̶��
				parent.left = p.right;
			else // p�� �θ��� ������ �ڽ��̶��
				parent.right = p.right;
		} else if (p.right == null) { // p�� ������ �ڽ��� ����
			if (p == root)
				root = p.left;
			else if (isLeftChild)
				parent.left = p.left;
			else
				parent.right = p.left;
		} else { // p���� ���� �ڽ�, ������ �ڽ��� �� �� ����
			// p�� ���� ����Ʈ�� �߿��� ���� ū ���� ã�´�.
			Node<K, V> left = p.left;
			isLeftChild = true;
			while (left.right != null) {
				parent = left;
				left = left.right;
				isLeftChild = false;
			}
			p.key = left.key;
			p.data = left.data;
			if (isLeftChild)
				parent.left = left.left; // left�� �����ϴ� ����
			else
				parent.right = left.left; // left�� �����ϴ� ����
		}
		return true;
	}

	// node�� ��Ʈ�� �ϴ� ����Ʈ���� ��带 Ű���� ������������ ���
	private void printSubTree(Node node) {
		// ����Ž��Ʈ���� ������������ -> ���� ��ȸ
		if (node != null) {
			printSubTree(node.left); // node.left�� ��Ʈ�� �ؼ� ������������ ���..
			System.out.println(node.key + " " + node.data);
			printSubTree(node.right);
		}
	}

	public void print() {
		printSubTree(root);
	}

	// Q1 printReverse
	private void printReverseSubTree(Node node) {
		if (node != null) {
			printReverseSubTree(node.right);
			System.out.println(node.key + " " + node.data);
			printReverseSubTree(node.left);
		}
	}

	public void printReverse() {
		printReverseSubTree(root);
	}

	// Q2 min, max, key, data
	public K getMinKey() {
		Node<K, V> ptr = root;
		while(ptr.left != null) {
			ptr = ptr.left;
		}
		return ptr.getKey();
	}
	
	public V getDataWithMinKey() {
		Node<K, V> ptr = root;
		while(ptr.left != null) {
			ptr = ptr.left;
		}
		return ptr.getValue();
	}
	
	public K getMaxKey() {
		Node<K, V> ptr = root;
		while(ptr.right != null) {
			ptr = ptr.right;
		}
		return ptr.getKey();
	}
	
	public V getDataWithMaxKey() {
		Node<K, V> ptr = root;
		while(ptr.right != null) {
			ptr = ptr.right;
		}
		return ptr.getValue();
	}
}