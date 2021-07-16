import java.util.Scanner;

public class BOJ10250 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int numTest = scn.nextInt();

        for (int i = 0; i < numTest; i++) {
            int height = scn.nextInt();
            int width = scn.nextInt();
            int nthGuest = scn.nextInt();
        
            System.out.println(likeRoomNumber(height, width, nthGuest));
        }

        scn.close();
    }

    static String likeRoomNumber(int height, int width, int nthGuest) {
        int room = (nthGuest - 1) / height + 1;
        int floor = (nthGuest - 1) % height + 1;
        
        String result = floor + "";
        if (room < 10) {
            result += "0" + room;
        } else {
            result += room;
        }

        return result;
    }
}
