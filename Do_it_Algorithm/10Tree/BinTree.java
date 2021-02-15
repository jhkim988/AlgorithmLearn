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

	// empty tree 라면 루트도 없으므로 root는 null이다.
	public BinTree() {
		// 자연순서에 따라 수행한다. K의 형이 Comparable 인터페이스를 구현하고 있는 Integer, String 클래스 등에 알맞다.
		root = null;
	}

	public BinTree(Comparator<? super K> c) {
		this(); // 위에 만든 생성자를 호출한다.
		comparator = c; // 전달받은 비교자에 의해 대소관계 판단
	}

	private int comp(K key1, K key2) {
		return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
		// null인 경우, Comparable<K>로 형변환하고 compareTo메서드를 호출하여 비교한다.
		// null이 아닌 경우, 생성자에서 인자로 받은 비교자로 비교한다.
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

	// node를 루트로 하는 서브트리에 노드 <K, V>를 삽입
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

	// 노드를 삽입
	public void add(K key, V data) {
		if (root == null)
			root = new Node<K, V>(key, data, null, null);
		else
			addNode(root, key, data);
	}

	// 키 값이 key인 노드를 삭제
	public boolean remove(K key) {
		Node<K, V> p = root;
		Node<K, V> parent = null;
		boolean isLeftChild = true; // p는 부모의 왼쪽 자식 노드인가?

		// 키 값이 key인 노드 찾기
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

		if (p.left == null) {// p에는 왼쪽 자식이 없음
			if (p == root)
				root = p.right;
			else if (isLeftChild) // p가 부모의 왼쪽 자식이라면
				parent.left = p.right;
			else // p가 부모의 오른쪽 자식이라면
				parent.right = p.right;
		} else if (p.right == null) { // p는 오른쪽 자식이 없음
			if (p == root)
				root = p.left;
			else if (isLeftChild)
				parent.left = p.left;
			else
				parent.right = p.left;
		} else { // p에는 왼쪽 자식, 오른쪽 자식이 둘 다 있음
			// p의 왼쪽 서브트리 중에서 가장 큰 값을 찾는다.
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
				parent.left = left.left; // left를 삭제하는 과정
			else
				parent.right = left.left; // left를 삭제하는 과정
		}
		return true;
	}

	// node를 루트로 하는 서브트리의 노드를 키값의 오름차순으로 출력
	private void printSubTree(Node node) {
		// 이진탐색트리를 오름차순으로 -> 중위 순회
		if (node != null) {
			printSubTree(node.left); // node.left를 루트로 해서 오름차순으로 출력..
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