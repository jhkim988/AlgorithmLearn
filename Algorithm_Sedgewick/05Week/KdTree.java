import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	private class Node {
		Point2D point;
		boolean isVertical;
		Node left;
		Node right;
		RectHV rect;

		Node(Point2D point, boolean isVertical, RectHV rect) {
			this.point = point;
			this.isVertical = isVertical;
			this.rect = rect;
		}
	}

	private Node root;
	private int size;

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
		if (p == null)
			throw new IllegalArgumentException();
//		size++; // duplicate key -> Do Not Increase
		root = insert(root, p, true, new RectHV(0.0, 0.0, 1.0, 1.0));
	}

	private Node insert(Node x, Point2D p, boolean isVert, RectHV rect) {
		if (x == null) {
			size++;
			return new Node(p, isVert, rect);
		}
		if (x.point.equals(p))
			return x;

		boolean cmp;
		if (isVert)
			cmp = x.point.x() > p.x();
		else
			cmp = x.point.y() > p.y();

		RectHV nextRect;
		if (cmp) {
			if (x.left == null) {
				double x1 = rect.xmin();
				double y1 = rect.ymin();
				double x2, y2;
				if (isVert) {
					x2 = x.point.x();
					y2 = rect.ymax();
				} else {
					x2 = rect.xmax();
					y2 = x.point.y();
				}
				nextRect = new RectHV(x1, y1, x2, y2);
			} else {
				nextRect = x.left.rect;
			}
			x.left = insert(x.left, p, !isVert, nextRect);
		} else {
			if (x.right == null) {
				double x1, y1;
				if (isVert) {
					x1 = x.point.x();
					y1 = rect.ymin();
				} else {
					x1 = rect.xmin();
					y1 = x.point.y();
				}
				double x2 = rect.xmax();
				double y2 = rect.ymax();
				nextRect = new RectHV(x1, y1, x2, y2);
			} else {
				nextRect = x.right.rect;
			}
			x.right = insert(x.right, p, !isVert, nextRect);
		}
		return x;

//		if (x.isVertical) {
//			// determine left or right of x
//			if (x.point.x() > p.x()) { // p is in left of x.point
//				x.left = insert(x.left, p, x);
//			} else if (x.point.x() < p.x()) { // p is in right of x.point
//				x.right = insert(x.right, p, x);
//			} else {
//				// same x-coordinate occurs
//				// left child
//			}
//		} else {
//			// determine bottom or above of x
//			if (x.point.y() >= p.y())
//				x.left = insert(x.left, p, x);
//			else if (x.point.x() < p.y()) {
//				x.right = insert(x.right, p, x);
//			} else {
//				// same y-coordinate occurs
//				// left child
//			}
//		}
//		return x;
	}

	public boolean contains(Point2D p) {
		// does the set contain point p?
		if (p == null)
			throw new IllegalArgumentException();
		if (isEmpty())
			return false;
		else
			return contains(root, p);
	}

	private boolean contains(Node x, Point2D p) {
		if (x == null)
			return false;
		if (x.point.equals(p))
			return true;

		boolean cmp;
		if (x.isVertical)
			cmp = x.point.x() > p.x();
		else
			cmp = x.point.y() > p.y();

		if (cmp)
			return contains(x.left, p);
		else
			return contains(x.right, p);

//		if (x.isVertical) {
//			if (x.point.x() >= p.x()) {
//				return contains(x.left, p);
//			} else {
//				return contains(x.right, p);
//			}
//		} else {
//			if (x.point.y() >= p.y()) {
//				return contains(x.left, p);
//			} else {
//				return contains(x.right, p);
//			}
//		}
	}

	public void draw() {
		// draw all points to standard draw
		if (isEmpty())
			return;
		draw(root, true);
	}

	private void draw(Node x, boolean isVert) {
		if (x != null) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.01);
			x.point.draw();
			if (isVert) {
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.line(x.point.x(), x.rect.ymin(), x.point.x(), x.rect.ymax());
			} else {
				StdDraw.setPenColor(StdDraw.BLUE);
				StdDraw.line(x.rect.xmin(), x.point.y(), x.rect.xmax(), x.point.y());
			}
			if (x.left != null) {
				draw(x.left, !isVert);
			}
			if (x.right != null) {
				draw(x.right, !isVert);
			}
		}
	}

	private void enqueue(Node x, Queue<Point2D> q, RectHV rect) {
		if (x != null) {
			if (rect.contains(x.point))
				q.enqueue(x.point);
			if ((x.isVertical && x.point.x() >= rect.xmin()) || (!x.isVertical && x.point.y() >= rect.ymin())) {
				enqueue(x.left, q, rect);
			}
			if ((x.isVertical && x.point.x() <= rect.xmax()) || (!x.isVertical && x.point.y() <= rect.ymax())) {
				enqueue(x.right, q, rect);
			}
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle (or on the boundary)
		if (rect == null) {
			throw new IllegalArgumentException();
//			return new Iterable<Point2D>() {
//				@Override
//				public Iterator<Point2D> iterator() {
//					return q.iterator();
//				};
//			};
		}
		if (isEmpty())
			return null;
		Queue<Point2D> q = new Queue<Point2D>();
		enqueue(root, q, rect); // N log N
		return q;

//		return new Iterable<Point2D>() {
//			@Override
//			public Iterator<Point2D> iterator() {
//				if (q.isEmpty())
//					return null;
//				else {
//					return new Iterator<Point2D>() {
//						@Override
//						public boolean hasNext() {
//							while (!q.isEmpty() && !rect.contains(q.peek())) {
//								q.dequeue();
//							}
//							return !q.isEmpty();
//						}
//
//						@Override
//						public Point2D next() {
//							return hasNext() ? q.dequeue() : null;
//						}
//					};
//				}
//			}
//		};

	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty
//		if (p == null)
//			throw new IllegalArgumentException();
//		if (isEmpty())
//			return null;
//		return nearest(root, p, root).point;
       if (p == null) {
    	   throw new IllegalArgumentException();
        }
        Point2D champion = null;
        double championDistance = Double.MAX_VALUE;
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) {
                continue;
            }
            double distance = p.distanceTo(x.point);
            if (distance < championDistance) {
                champion = x.point;
                championDistance = distance;
            }
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return champion;
	}

//	private Node nearest(Node x, Point2D p, Node near) {
//		if (x == null) return near;
//		boolean cmp;
//		if (x.isVertical) cmp = x.point.x() > p.x();
//		else cmp = x.point.y() > p.y();
//
//		if (cmp) near = nearest(x.left, p, near);
//		else near = nearest(x.right, p, near);
//		
//		if (x.isVertical && Math.abs(p.x() - x.point.x()) < p.distanceTo(near.point))
//			near = cmp ? nearest(x.right, p, near) : nearest(x.left, p, near);
//		if (!x.isVertical && Math.abs(p.y() - x.point.y()) < p.distanceTo(near.point))
//			near = cmp ? nearest(x.left, p, near) : nearest(x.right, p, near);
//		
//		if (p.distanceTo(x.point) < p.distanceTo(near.point)) return x;
//		else return near;
		
//		if (x.isVertical) {
//			// compare x-coordinate
//			if (x.point.x() >= p.x()) {
//				// check left first, then determine that need to check right
//				near = (x.left != null) ? nearest(x.left, p, near) : near;
//				if ((x.point.x() - p.x()) < p.distanceTo(near.point)) {
//					near = (x.right != null) ? nearest(x.right, p, near) : near;
//				}
//			} else if (x.point.x() < p.x()) {
//				// check right first, then determine that need to check left
//				near = (x.right != null) ? nearest(x.right, p, near) : near;
//				if ((p.x() - x.point.x()) < p.distanceTo(near.point)) {
//					near = (x.left != null) ? nearest(x.left, p, near) : near;
//				}
//			} else {
//				// same x-coordinates
//				// left node
//			}
//		} else {
//			// compare y-coordinate
//			if (x.point.y() >= p.y()) {
//				// check bottom first, then determine that need to check above
//				near = (x.left != null) ? nearest(x.left, p, near) : near;
//				if ((x.point.y() - p.y()) < p.distanceTo(near.point)) {
//					near = (x.right != null) ? nearest(x.right, p, near) : near;
//				}
//			} else if (x.point.y() < p.y()) {
//				// check above first, then determine that need to check bottom
//				near = (x.right != null) ? nearest(x.right, p, near) : near;
//				if ((p.y() - x.point.y()) < p.distanceTo(near.point)) {
//					near = (x.left != null) ? nearest(x.left, p, near) : near;
//				}
//			} else {
//				// same y-coordinates
//				// left node
//			}
//		}
//	}
	
	public static void main(String[] args) {
		// unit testing of the methods (optional)
//		int N = 10;
//		KdTree kdt = new KdTree();
//		StdOut.println("Before Insert - isEmpty() = " + kdt.isEmpty());
//		for (double i = 0.0; i < N; i++) {
//			kdt.insert(new Point2D(i * 0.1, i * 0.1));
//		}
//		StdOut.println("After Insert - isEmpty() = " + kdt.isEmpty());
//		StdOut.println("Size of PointSet = " + kdt.size());
//		StdOut.println("contains() method test:\n - ps.contains(new Point2D(2.0, 2.0)) = "
//				+ kdt.contains(new Point2D(2.0*0.1, 2.0*0.1)));
//		StdOut.println("contains() method test:\n - ps.contains(new Point2D(1.0, 2.0)) = "
//				+ kdt.contains(new Point2D(1.0*0.1, 2.0*0.1)));
//
//		RectHV rect = new RectHV(0.05, 0.05, 0.55, 0.55);
//		Iterable<Point2D> iter = kdt.range(rect);
//		StdOut.println("Iterator Test");
//		for (Point2D p : iter) {
//			StdOut.println(p);
//		}
//		StdOut.println(
//				"nearest() method test:\n - ps.nearest(new Point2D(0.05, 0.15)) = " + kdt.nearest(new Point2D(0.05, 0.15)));
//		kdt.draw();

		// Auto Grader - test 3
//		KdTree kdt2 = new KdTree();
//		Point2D A = new Point2D(0.25, 0.25);
//		Point2D B = new Point2D(1.0, 0.0);
//		Point2D C = new Point2D(0.75, 0.25);
//		Point2D D = new Point2D(0.25, 1.0);
//		Point2D E = new Point2D(0.75, 1.0);
//		Point2D F = new Point2D(0.75, 0.25);
//		Point2D G = new Point2D(0.0, 0.75);
//		Point2D H = new Point2D(0.25, 0.25);
//		Point2D I = new Point2D(0.75, 0.5);
//		Point2D J = new Point2D(0.5, 0.25);
//		Point2D query = new Point2D(0.5, 0.25);
//		kdt2.insert(A);
//		kdt2.insert(B);
//		kdt2.insert(C);
//		kdt2.insert(D);
//		kdt2.insert(E);
//		kdt2.insert(F);
//		kdt2.insert(G);
//		kdt2.insert(H);
//		kdt2.insert(I);
//		kdt2.insert(J);
//		StdOut.println("near : " + kdt2.nearest(query));

		// Auto Grader - test 2a
//		KdTree kdt3 = new KdTree();
//		Point2D A = new Point2D(0.7, 0.2);
//		Point2D B = new Point2D(0.5, 0.4);
//		Point2D C = new Point2D(0.2, 0.3);
//		Point2D D = new Point2D(0.4, 0.7);
//		Point2D E = new Point2D(0.9, 0.6);
//		kdt3.insert(A);
//		kdt3.insert(B);
//		kdt3.insert(C);
//		kdt3.insert(D);
//		kdt3.insert(E);
//		StdOut.println("contains(0.2, 0.3) - " + kdt3.contains(new Point2D(0.2, 0.3)));
//		Iterable<Point2D> iter3 = kdt3.range(new RectHV(0.0, 0.0, 1.0, 1.0));
//		for (Point2D p : iter3)
//			StdOut.println(p);
//		kdt3.draw();

		// Auto Grader - test 4a
//		KdTree kdt4 = new KdTree();
//		Point2D A = new Point2D(0.7, 0.2);
//		Point2D B = new Point2D(0.5, 0.4);
//		Point2D C = new Point2D(0.2, 0.3);
//		Point2D D = new Point2D(0.4, 0.7);
//		Point2D E = new Point2D(0.9, 0.6);
//		RectHV query = new RectHV(0.36, 0.46, 0.37, 0.83);
//		kdt4.insert(A);
//		kdt4.insert(B);
//		kdt4.insert(C);
//		kdt4.insert(D);
//		kdt4.insert(E);
//		Iterable<Point2D> iter4 = kdt4.range(query);
//		for(Point2D p : iter4)
//			StdOut.println(p);

		// Auto Grader - test 5a
		KdTree kdt5 = new KdTree();
		Point2D A = new Point2D(0.7, 0.2);
		Point2D B = new Point2D(0.5, 0.4);
		Point2D C = new Point2D(0.2, 0.3);
		Point2D D = new Point2D(0.4, 0.7);
		Point2D E = new Point2D(0.9, 0.6);
		Point2D query = new Point2D(0.32, 0.423);
		kdt5.insert(A);
		kdt5.insert(B);
		kdt5.insert(C);
		kdt5.insert(D);
		kdt5.insert(E);
		StdOut.println("nearest - " + kdt5.nearest(query));
	}
}
