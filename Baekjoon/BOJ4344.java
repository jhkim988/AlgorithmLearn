import java.io.*;

public class BOJ4344 {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            OutputStreamWriter osw = new OutputStreamWriter(System.out);
            BufferedWriter bw = new BufferedWriter(osw);

            int C = Integer.parseInt(br.readLine());
            String[] input;
            double avg = 0;
            int count = 0;
            for (int i = 0; i < C; i++) {
                input = br.readLine().split(" ");
                int num = Integer.parseInt(input[0]);
                for (int j = 1; j <= num; j++) {
                    avg += Double.parseDouble(input[j]);
                }
                avg /= num;
                for (int j = 1; j <= num; j++) {
                    if (Double.parseDouble(input[j]) > avg) {
                        count++;
                    }
                }
                bw.write(String.format("%.3f", ((double) count) * 100/ num) + "%\n");

                avg = 0;
                count = 0;
            }
            br.close();
            isr.close();

            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }
}
