import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

class Point2D {
	// random coordinates [0, 1] * [0, 1]
	private double x;
	private double y;

	Point2D() {
		x = StdRandom.uniform();
		y = StdRandom.uniform();
	}

	double distance(Point2D another) {
		return Math.sqrt((x - another.x) * (x - another.x) + (y - another.y) * (y - another.y));
	}
}

public class Exercise01_02_01 {
	// 1.2.1
	// 명령어 인수로 정수 N을 입력받아 N개의 랜덤 좌표를 1*1 사각형 영역 안에 생성하는 Point2D 클라이언트 프로그램을 작성하라
	// 그리고 가장 가까이 있는 두 점의 거리를 계산하라

	
	static double distance(Point2D p, Point2D q) {
		return p.distance(q);
	}

	static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	static Point2D[] generator(int N) {
		Point2D[] points = new Point2D[N];
		for (int i = 0; i < N; i++)
			points[i] = new Point2D();
		return points;
	}

	static double minDistance(Point2D[] points) {
		double min = points[0].distance(points[1]);
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double distance = points[i].distance(points[j]);
				if (min > distance)
					min = distance;
			}
		}
		return min;
	}

	public static void main(String[] args) {
//		int N = Integer.parseInt(args[0]); 
		int N = 20;
		Point2D[] points = generator(N);
		double min = minDistance(points);
		StdOut.println("Minimum Distance : " + min);
	}
}
