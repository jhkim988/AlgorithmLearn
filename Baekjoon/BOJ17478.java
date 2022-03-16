import java.io.*;
import java.util.*;

public class BOJ17478 {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static int n;
  static String init = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n";
  static String question = "\"재귀함수가 뭔가요?\"\n";
  static String story1 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
  static String story2 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
  static String story3 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
  static String answer = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
  static String end = "라고 답변하였지.\n";
  static ArrayList<String> indents = new ArrayList<>();
  public static void main(String[] args) throws IOException {
    n = Integer.parseInt(br.readLine());
    indents.add("");
    String indent = "____";
    StringBuilder sb = new StringBuilder(indent);
    for (int i = 0; i <= n; i++) {
      indents.add(sb.toString());
      sb.append(indent);
    }
    bw.write(init);
    recur(0);
    bw.write(end);
    bw.flush();
  }
  static void recur(int depth) throws IOException {
    if (depth >= n) {
      bw.write(indents.get(depth));
      bw.write(question);
      bw.write(indents.get(depth));
      bw.write(answer);
      return;      
    }
    bw.write(indents.get(depth));
    bw.write(question);
    bw.write(indents.get(depth));
    bw.write(story1);
    bw.write(indents.get(depth));
    bw.write(story2);
    bw.write(indents.get(depth));
    bw.write(story3);
    recur(depth + 1);
    bw.write(indents.get(depth+1));
    bw.write(end);
  }
}
