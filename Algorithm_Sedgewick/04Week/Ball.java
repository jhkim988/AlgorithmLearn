import edu.princeton.cs.algs4.StdDraw;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	
	public Ball() {
		// initialize position and velocity
		
	}
	
	public void move(double dt) {
		if((rx + vx*dt) < radius || (rx + vx*dt > 1.0 - radius)) {vx = -vx;}
		if((ry + vy*dt) < radius || (ry + vy*dt > 1.0 - radius)) {vy = -vy;}
		rx += vx * dt;
		ry += vy * dt;
	}
	
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
	
	// time-driven simulation
	// update dt seconds, if overlap, roll back to the time of the collision
	// ~ N^2/2 overlap checks per time quantum
	// if dt is very small, simulation is too small
	// May miss collision if dt is too large(지나쳐버린다.)
	
	// event-driven simulation
	
}
