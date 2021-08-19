import java.io.*;
import java.util.*;

public class BOJ7869 {
  private static class Point {
    double x;
    double y;
    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
    static double dist(Point p1, Point p2) {
      return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
    }
    static double cosSecond(double a, double b, double c) {
      return (a*a + b*b - c*c) / (2 * a * b);
    }
  }
  private static class Circle {
    Point center;
    double radius;
    Circle(Point center, double radius) {
      this.center = center;
      this.radius = radius;
    }
    double area() {
      return radius * radius * Math.PI;
    }
    double sectorArea(double angle) {
      return 0.5 * radius * radius * angle;
    }
    double triangleArea(double angle) {
      return 0.5 * radius * radius * Math.sin(angle);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    Circle[] circle = new Circle[2];
    for (int i = 0; i < 2; i++) {
      circle[i] = new Circle(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())), Double.parseDouble(st.nextToken()));
    }
    if (circle[0].radius > circle[1].radius) {
      Circle tmp = circle[0];
      circle[0] = circle[1];
      circle[1] = tmp;
    }
    // circle relation
    double dist = Point.dist(circle[0].center, circle[1].center);
    double standard1 = circle[0].radius + circle[1].radius;
    double standard2 = Math.abs(circle[0].radius - circle[1].radius);
    double result = 0.0;
    if (dist <= standard2) {
      result = circle[0].area();
    } else if (dist < circle[1].radius) {
      double cosTheta1Half = Point.cosSecond((double) circle[1].radius, dist, (double) circle[0].radius);
      double cosTheta0Half = (circle[1].radius * cosTheta1Half - dist) / circle[0].radius;
      double theta0 = 2 * Math.acos(cosTheta0Half);
      double theta1 = 2 * Math.acos(cosTheta1Half);
      result = circle[0].sectorArea(2 * Math.PI - theta0) + circle[0].triangleArea(theta0) + circle[1].sectorArea(theta1) - circle[1].triangleArea(theta1);
    } else if (dist < standard1) {
      double cosTheta0Half = Point.cosSecond(dist, circle[0].radius, circle[1].radius);
      double cosTheta1Half = Point.cosSecond(dist, circle[1].radius, circle[0].radius);
      double theta0 = 2 * Math.acos(cosTheta0Half);
      double theta1 = 2 * Math.acos(cosTheta1Half);
      double area0 = circle[0].sectorArea(theta0) - circle[0].triangleArea(theta0);
      double area1 = circle[1].sectorArea(theta1) - circle[1].triangleArea(theta1);
      result = area0 + area1;
    } else {
      result = 0.0;
    }
    result = Math.round(result * 1000.0) / 1000.0;
    System.out.printf("%.3f", result);
  }
}
