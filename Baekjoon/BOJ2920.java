import java.util.Scanner;

public class BOJ2920 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] input = new int[8];
        for (int i = 0; i < 8; i++) {
            input[i] = scn.nextInt();
        }
        scn.close();
        System.out.print(deter(input) == 1 ? "ascending" : (deter(input) == 0 ? "mixed" : "descending"));
    }

    static int deter(int[] data) {
        int ptr1 = 0;
        int ptr2 = 1;
        int sign = sign(data, ptr2, ptr1);
        for (ptr1 = 1, ptr2 = 2 ;ptr2 < 8; ptr1++, ptr2++) {
            if (sign(data, ptr2, ptr1) != sign) {
                return 0;
            }
        }
        return sign > 0 ? 1 : -1;
    }
    static int sign(int[] data, int ptr2, int ptr1) {
        return  data[ptr2] > data[ptr1] ? 1 : (data[ptr2] == data[ptr1] ? 0 : -1);
    }
}
