
public class LineSegment {
	private final Point p;
	private final Point q;
	public LineSegment(Point p, Point q) {
		// constructs the line segment between points p and q
		if(p == null || q == null)
			throw new NullPointerException("argument is null");
		this.p = p;
		this.q = q;
	}

	public void draw() {
		// draws this line segment
		p.drawTo(q);
	}

	public String toString() {
		// string representation
		return p + " -> " + q;
	}
    public int hashCode() {
        throw new UnsupportedOperationException();
    }
}
