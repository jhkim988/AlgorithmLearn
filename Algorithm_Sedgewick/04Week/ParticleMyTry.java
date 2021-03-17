import java.awt.Color;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ParticleMyTry {
	private static final double INFINITY = Double.POSITIVE_INFINITY;
	double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
    private final Color color; // color
    
	public ParticleMyTry(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
		// initialize position and velocity, radius, mass
		this.vx = vx;
		this.vy = vy;
		this.rx = rx;
		this.ry = ry;
		this.radius = radius;
		this.mass = mass;
		this.color = color;
	}
	
	public ParticleMyTry() {
        rx     = StdRandom.uniform(0.0, 1.0);
        ry     = StdRandom.uniform(0.0, 1.0);
        vx     = StdRandom.uniform(-0.005, 0.005);
        vy     = StdRandom.uniform(-0.005, 0.005);
        radius = StdRandom.uniform(0.01, 0.04);
        mass   = 0.5;
        color = new Color(StdRandom.uniform(0,256), StdRandom.uniform(0,256), StdRandom.uniform(0,256));
	}
	
	public void move(double dt) {
//		if((rx + vx*dt) < radius || (rx + vx*dt > 1.0 - radius)) {vx = -vx;}
//		if((ry + vy*dt) < radius || (ry + vy*dt > 1.0 - radius)) {vy = -vy;}
		rx += vx * dt;
		ry += vy * dt;
	}
	
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
	}
	
	public int count() {
		return count;
	}
	
	// time-driven simulation
	// update dt seconds, if overlap, roll back to the time of the collision
	// ~ N^2/2 overlap checks per time quantum
	// if dt is very small, simulation is too small
	// May miss collision if dt is too large(지나쳐버린다.)
	
	// event-driven simulation
	// prediction
	public double timeToHit(ParticleMyTry that) {
		if (this == that) return INFINITY;
		double dx  = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx*dvx +dy*dvy;
		if(dvdr > 0) return INFINITY;
		double dvdv = dvx * dvx + dvy*dvy;
		if (dvdv == 0) return INFINITY;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma*sigma);
		if (d < 0) return INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}
	public double timeToHitVerticalWall() {
		if (vx > 0) return (1.0 - radius - rx) / vx;	
		else if (vx < 0) return (radius - rx) / vx;
		else return Double.POSITIVE_INFINITY;
	}
	public double timeToHitHorizontalWall() {
		if (vy > 0) return (1.0 - radius - ry) / vy;	
		else if (vy < 0) return (radius - ry) / vy;
		else return Double.POSITIVE_INFINITY;
	}
	
	// resolution
	public void bounceOff(ParticleMyTry that) {
		double dx = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx*dvx +dy*dvy;
		double dist = this.radius + that.radius;
		double J = 2*this.mass*that.mass*dvdr/((this.mass + that.mass) * dist);
		double Jx = J * dx / dist;
		double Jy = J * dy / dist;
		this.vx += Jx/this.mass;
		this.vy += Jy/this.mass;
		that.vx -= Jx/that.mass;
		that.vy -= Jy/that.mass;
		this.count++;
		that.count++;
	}
	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}
	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;
	}
	
	public double kineticEnergy() {
		return 0.5*mass*(vx*vx+vy*vy);
	}
}
