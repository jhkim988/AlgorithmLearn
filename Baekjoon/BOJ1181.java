import java.io.*;
import java.util.*;

public class BOJ1181 {
  private static class WordData {
    String word;
    int length;
    WordData(String word) {
      this.word = word;
      this.length = word.length();
    }
  }
  public static void main(String[] args) {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      int numData = Integer.parseInt(br.readLine());
      WordData[] data = new WordData[numData];
      
      for (int i = 0; i < numData; i++) {
        data[i] = new WordData(br.readLine());
      }
      br.close();

      Arrays.sort(data, new Comparator<WordData>() {
        @Override
        public int compare(WordData wd1, WordData wd2) {
          if (wd1.length > wd2.length) {
            return 1;
          } else if (wd1.length < wd2.length) {
            return -1;
          } else {
            return wd1.word.compareTo(wd2.word);
          }
        }
      });
      String prev = "";
      for (int i = 0; i < numData; i++) {
        if (prev.compareTo(data[i].word) == 0) {
          continue;
        }
        bw.write((data[i].word + "\n"));
        prev = data[i].word;
      }
      bw.flush();
      bw.close();
    } catch (IOException e) {
      // do nothing
    }
  }
}
