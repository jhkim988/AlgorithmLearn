import java.util.Iterator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	private class Node {
		Point2D point;
		boolean isVertical;
		Node left;
		Node right;

		Node(Point2D point, boolean isVertical) {
			this.point = point;
			this.isVertical = isVertical;
		}
	}

	Node root;
	int size;

	public KdTree() {
		// construct an empty set of points
	}

	public boolean isEmpty() {
		// is the set empty?
		return size == 0;
	}

	public int size() {
		// number of points in the set
		return size;
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		size++;
		root = insert(root, p, null);
	}

	private Node insert(Node x, Point2D p, Node parent) {
		if (x == null) {
			if (parent == null)
				return new Node(p, true);
			else
				return new Node(p, !parent.isVertical);
		}
		if (x.isVertical) {
			// determine left or right of x
			if (x.point.x() > p.x()) { // p is in left of x.point
				x.left = insert(x.left, p, x);
			} else if (x.point.x() < p.x()) { // p is in right of x.point
				x.right = insert(x.right, p, x);
			} else {
				// same x-coordinate occurs
				return null;
			}
		} else {
			// determine bottom or above of x
			if (x.point.y() > p.y())
				x.left = insert(x.left, p, x);
			else if (x.point.x() < p.y()) {
				x.right = insert(x.right, p, x);
			} else {
				// same y-coordinate occurs
				return null;
			}
		}
		return null;
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		return contains(root, p);
	}

	private boolean contains(Node x, Point2D p) {
		if (x == null)
			return false;
		if (x.point.equals(p))
			return true;
		if (x.isVertical) {
			if (x.point.x() > p.x()) {
				return contains(x.left, p);
			} else if (x.point.x() < p.x()) {
				return contains(x.right, p);
			} else {
				// same x-coordinate occurs
			}
		} else {
			if (x.point.y() > p.y()) {
				return contains(x.left, p);
			} else if (x.point.y() < p.y()) {
				return contains(x.right, p);
			} else {
				// same y-coordinate occurs
			}
		}
		return false;
	}

	public void draw() {
		// draw all points to standard draw
		draw(root);
	}

	private void draw(Node x) {
		x.point.draw();
		if (x.left != null)
			draw(x.left);
		if (x.right != null)
			draw(x.right);
	}

	private void enqueue(Node x, Queue<Point2D> q) {
		q.enqueue(x.point);
		if (x.left != null)
			enqueue(x.left, q);
		if (x.right != null)
			enqueue(x.right, q);
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		Queue<Point2D> q = new Queue<Point2D>();
		enqueue(root, q); // N log N
		return new Iterable<Point2D>() {
			@Override
			public Iterator<Point2D> iterator() {
				if (q.isEmpty())
					return null;
				return new Iterator<Point2D>() {
					@Override
					public boolean hasNext() {
						while (!q.isEmpty() && !rect.contains(q.peek())) {
							q.dequeue();
						}
						return !q.isEmpty();
					}

					@Override
					public Point2D next() {
						return hasNext() ? q.dequeue() : null;
					}
				};
			}
		};
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
		return nearest(root, p, root).point;
	}

	private Node nearest(Node x, Point2D p, Node near) {
		if (x.isVertical) {
			// compare x-coordinate
			if (x.point.x() > p.x()) {
				// check left first, then determine that need to check right
				near = (x.left != null) ? nearest(x.left, p, near) : near;
				if ((x.point.x() - p.x()) < p.distanceTo(near.point)) {
					near = (x.right != null) ? nearest(x.right, p, near) : near;
				}
			} else if (x.point.x() < p.x()) {
				// check right first, then determine that need to check left
				near = (x.right != null) ? nearest(x.right, p, near) : near;
				if ((p.x() - x.point.x()) < p.distanceTo(near.point)) {
					near = (x.left != null) ? nearest(x.left, p, near) : near;
				}
			} else {
				// same x-coordinates
			}
		} else {
			// compare y-coordinate
			if (x.point.y() > p.y()) {
				// check bottom first, then determine that need to check above
				near = (x.left != null) ? nearest(x.left, p, near) : near;
				if ((x.point.y() - p.y()) < p.distanceTo(near.point)) {
					near = (x.right != null) ? nearest(x.right, p, near) : near;
				}
			} else if (x.point.y() < p.y()) {
				// check above first, then determine that need to check bottom
				near = (x.right != null) ? nearest(x.right, p, near) : near;
				if ((p.y() - x.point.y()) < p.distanceTo(near.point)) {
					near = (x.left != null) ? nearest(x.left, p, near) : near;
				}
			} else {
				// same y-coordinates
			}
		}

		if (p.distanceTo(x.point) < p.distanceTo(near.point))
			return x;
		else
			return near;

	}

	public static void main(String[] args) {
		// unit testing of the methods (optional)
		int N = 10;
		KdTree kdt = new KdTree();
		StdOut.println("Before Insert - isEmpty() = " + kdt.isEmpty());
		for (Double i = 0.0; i < N; i++) {
			kdt.insert(new Point2D(i, i));
		}
		StdOut.println("After Insert - isEmpty() = " + kdt.isEmpty());
		StdOut.println("Size of PointSet = " + kdt.size());
		StdOut.println("contains() method test:\n - ps.contains(new Point2D(2.0, 2.0)) = "
				+ kdt.contains(new Point2D(2.0, 2.0)));
		StdOut.println("contains() method test:\n - ps.contains(new Point2D(1.0, 2.0)) = "
				+ kdt.contains(new Point2D(1.0, 2.0)));
		
		RectHV rect = new RectHV(0.5, 0.5, 5.5, 5.5);
		Iterable<Point2D> iter = kdt.range(rect);
		StdOut.println("Iterator Test");
		for (Point2D p : iter) {
			StdOut.println(p);
		}
		StdOut.println(
				"nearest() method test:\n - ps.nearest(new Point2D(0.5, 1.5)) = " + kdt.nearest(new Point2D(0.5, 1.5)));
		kdt.draw();
	}
}
