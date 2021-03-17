import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

public class CollisionSystemMyTry {
	private static final double HZ = 0.5;
	private MinPQ<EventMyTry> pq;
	private double t = 0.0;
	private ParticleMyTry[] particles;

	public CollisionSystemMyTry(ParticleMyTry[] particles) {
		this.particles = particles.clone();
	}

	private void predict(ParticleMyTry a) {
		if (a == null)
			return;
		int N = particles.length;
		for (int i = 0; i < N; i++) {
			double dt = a.timeToHit(particles[i]);
			if (dt < 10000)
				pq.insert(new EventMyTry(t + dt, a, particles[i]));
		}
		double dtX = a.timeToHitVerticalWall();
		double dtY = a.timeToHitHorizontalWall();
		if(dtX < 10000)
			pq.insert(new EventMyTry(t + dtX, a, null));
		if(dtY < 10000)
			pq.insert(new EventMyTry(t + dtY, null, a));
	}

	private void redraw() {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
		StdDraw.show();
		StdDraw.pause(20);
		pq.insert(new EventMyTry(t + 1.0 / HZ, null, null));
	}

	public void simulate() {
		int N = particles.length;
		pq = new MinPQ<EventMyTry>();
		for (int i = 0; i < N; i++)
			predict(particles[i]);
		pq.insert(new EventMyTry(0, null, null));

		while (!pq.isEmpty()) {
			StdOut.println("queue size : " + pq.size());
			EventMyTry event = pq.delMin();
			if (!event.isValid())
				continue;
			ParticleMyTry a = event.a();
			ParticleMyTry b = event.b();

			for (int i = 0; i < N; i++)
				particles[i].move(event.time() - t);
			t = event.time();

			if (a != null && b != null) {
				a.bounceOff(b);
			} else if (a != null && b == null) {
				a.bounceOffVerticalWall();
			} else if (a == null && b != null) {
				b.bounceOffHorizontalWall();
			} else if (a == null && b == null) {
				redraw();
			}
			predict(a);
			predict(b);
		}
		StdOut.println("End");
	}

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering(); // 화면 끊기는 것 방지
		int N = 50;
		ParticleMyTry[] particles = new ParticleMyTry[N];
		for (int i = 0; i < N; i++) {
			particles[i] = new ParticleMyTry();
		}

		CollisionSystemMyTry cs = new CollisionSystemMyTry(particles);
		cs.simulate();
	}
}
