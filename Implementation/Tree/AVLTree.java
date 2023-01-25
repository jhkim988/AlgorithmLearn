import java.util.*;

public class AVLTree {
	private static class Node {
		int key, height;
		Node left, right;

		Node(int key) {
			this.key = key;
			this.height = 1;
		}
	}

	Node root = null;

	Node insert(Node node, int key) {
		if (node == null)
			return new Node(key);
		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else
			return node;

		node.height = Integer.max(height(node.left), height(node.right)) + 1;
		int diff = height(node.left) - height(node.right);

		// Left Left
		if (diff > 1 && key < node.left.key)
			return rotateRight(node);
		// Left Right
		if (diff > 1 && key > node.left.key) {
			node.left = rotateLeft(node.left);
			return rotateRight(node);
		}
		// Right Left
		if (diff < -1 && key < node.right.key) {
			node.right = rotateRight(node.right);
			return rotateLeft(node);
		}
		// Right Right
		if (diff < -1 && key > node.right.key) {
			return rotateLeft(node);
		}
		return node;
	}

	Node rotateRight(Node node) {
		Node left = node.left;
		Node tmp = left.right;
		left.right = node;
		node.left = tmp;
		return left;
	}

	Node rotateLeft(Node node) {
		Node right = node.right;
		Node tmp = right.left;
		right.left = node;
		node.right = tmp;
		return right;
	}

	Queue<Integer> inorder() {
		Queue<Integer> que = new LinkedList<>();
		dfs(root, que);
		return que;
	}

	void dfs(Node node, Queue<Integer> que) {
		if (node == null)
			return;
		dfs(node.left, que);
		que.add(node.key);
		dfs(node.right, que);
	}

	int height(Node node) {
		return node == null ? 0 : node.height;
	}

	public static void main(String[] args) {

	}
}
