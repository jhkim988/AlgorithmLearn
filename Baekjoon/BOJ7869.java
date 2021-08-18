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
      result = circle[0].radius < circle[1].radius ? circle[0].area() : circle[1].area();
    } else if (dist < circle[1].radius) {
      double cosTheta2 = (circle[0].radius * circle[0].radius - circle[1].radius * circle[1].radius - dist * dist) / (2 * circle[1].radius * dist);
      double cosTheta1 = (circle[1].radius * cosTheta2 - dist) / circle[0].radius;
      double theta1 = Math.acos(cosTheta1);
      result = circle[0].area() - circle[0].sectorArea(theta1) + circle[1].triangleArea(theta1);
      System.out.println(cosTheta2+ " " + cosTheta1 + " " + theta1 + " " + result);
    } else if (dist < standard1) {
      double theta1 = 2 * Math.acos(circle[0].radius / dist);
      double theta2 = 2 * Math.acos(circle[1].radius / dist);
      double area1 = circle[0].sectorArea(theta1) - circle[0].triangleArea(theta1);
      double area2 = circle[1].sectorArea(theta2) - circle[1].triangleArea(theta2);
      result = area1 + area2;
      System.out.println(dist + " " + circle[1].radius);
      System.out.println(theta1 + " " + theta2 + " " + area1 + " " + area2);
    } else {
      result = 0.0;
    }
    System.out.printf("%.3f\n", result);
  }
}
