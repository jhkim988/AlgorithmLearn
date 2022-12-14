import java.io.*;

public class BOJ10170 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("NFC West       W   L  T\n-----------------------\nSeattle        13  3  0\nSan Francisco  12  4  0\nArizona        10  6  0\nSt. Louis      7   9  0\n\nNFC North      W   L  T\n-----------------------\nGreen Bay      8   7  1\nChicago        8   8  0\nDetroit        7   9  0\nMinnesota      5  10  1");
        bw.flush();
    }
}