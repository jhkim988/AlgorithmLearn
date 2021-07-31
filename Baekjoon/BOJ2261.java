import java.io.*;
import java.util.*;

public class BOJ2261 {
  static Comparator<Point2D> xSort = new Comparator<Point2D>(){
    @Override
    public int compare(Point2D o1, Point2D o2) {
      if (o1.x > o2.x) {
        return 1;
      } else if (o1.x < o2.x) {
        return -1;
      } else {
        if (o1.y > o2.y) {
          return 1;
        } else if (o1.y < o2.y) {
          return -1;
        } else {
          return 0;
        }
      }
    }      
  };
  static Comparator<Point2D> ySort = new Comparator<Point2D>(){
    @Override
    public int compare(Point2D o1, Point2D o2) {
      if (o1.y > o2.y) {
        return 1;
      } else if (o1.y < o2.y) {
        return -1;
      } else {
        if (o1.x > o2.x) {
          return 1;
        } else if (o1.x < o2.x) {
          return -1;
        } else {
          return 0;
        }
      }
    }
  };
  
  private static class Point2D {
    int x;
    int y;
    Point2D(int x, int y) {
      this.x = x;
      this.y = y;
    }
    int distSqaure(Point2D other) {
      return (this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y);
    }
  }
  static int minDist(Point2D[] data) {
    // 1. Sort data by x-coordinate
    Arrays.sort(data, xSort);
    return recur(data, 0, data.length - 1);
  }
  static int recur(Point2D[] data, int start, int end) {
    // Partitioned Area has only two or three points.
    if (end - start == 1) {
      // has two points
      return data[end].distSqaure(data[start]);
    }

    if (end - start == 2) {
      int min = Integer.MAX_VALUE;
      for (int i = 0; i < 2; i++) {
        for (int j = i + 1; j <= 2; j++) {
          min = Math.min(min, data[i].distSqaure(data[j]));
        }
      }
      return min;
    }
    // 2. Partition by center of points 
    int cntIdx = (start + end) / 2;
    int min1 = recur(data, start, cntIdx);
    int min2 = recur(data, cntIdx + 1, end);
    int min = Math.min(min1, min2);

    // 3. In [cntIdx - min, cntIdx + min], Sort data by y-coordinate
    ArrayList<Point2D> list = new ArrayList<>();
    for (int i = start; i <= end; i++) {
      int dist = data[i].x - data[cntIdx].x;
      if (dist * dist < min) {
        list.add(data[i]);
      }
    }
    Collections.sort(list, ySort);
    
    // 4. Find minimum distance in [cntIdx - min, cntIdx + min]
    for (int i = 0; i < list.size() - 1; i++) {
      for (int j = i + 1; j < list.size(); j++) {
        int ydist = list.get(j).y - list.get(i).y;
        if (ydist * ydist < min) {
          min = Math.min(list.get(i).distSqaure(list.get(j)), min);
        } else {
          break;
        }
      }
    }
    return min;
  }
  static int sweep(Point2D[] data) {
    Arrays.sort(data, xSort);
    int min = data[0].distSqaure(data[1]);
    if (data.length < 3) {
      return min;
    }
    TreeSet<Point2D> ts = new TreeSet<>(ySort);
    ts.add(data[0]);
    ts.add(data[1]);
    int lowIdx = 0; 
    for (int i = 2; i < data.length; i++) {
      Point2D crnt = data[i];
      while (lowIdx < i) {
        Point2D target = data[lowIdx];
        int xDist = crnt.x - target.x;
        if (xDist * xDist > min) {
          ts.remove(target);
          lowIdx++;
        } else {
          break;
        }
      }

      int sqrtMinDist = (int) Math.sqrt(min) + 1;
      TreeSet<Point2D> ysub = (TreeSet<Point2D>) ts.subSet(new Point2D(-10000, crnt.y - sqrtMinDist), new Point2D(10000, crnt.y + sqrtMinDist));
      for (Point2D point : ysub) {
        min = Math.min(min, point.distSqaure(crnt));
      }
      ts.add(crnt);
    }
    return min;
  }
  public static void main(String[] ags) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    Point2D[] data = new Point2D[N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      data[i] = new Point2D(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }
    // bw.write(minDist(data) + "\n");
    bw.write(sweep(data) + "\n");
    bw.flush();
  }
}
