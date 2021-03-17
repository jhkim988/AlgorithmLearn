
public class EventMyTry implements Comparable<EventMyTry> {
	private double time;
	private ParticleMyTry a, b;
	private int countA, countB;

	public EventMyTry(double t, ParticleMyTry a, ParticleMyTry b) {
		// If either a or b is null, Event about wall
		// If both a and b are null, re-draw

		this.time = t;
		this.a = a;
		this.b = b;
		if (a != null)
			countA = a.count();
		else
			countA = -1;
		
		if (b != null)
			countB = b.count();
		else
			countB = -1;
	}

	public int compareTo(EventMyTry that) {
		return Double.compare(this.time, that.time);
	}

	public boolean isValid() {
		if (a != null && a.count() != countA)
			return false;
		if (b != null && b.count() != countB)
			return false;
		return true;
	}

	public double time() {
		return time;
	}

	public ParticleMyTry a() {
		return a;
	}

	public ParticleMyTry b() {
		return b;
	}
}
