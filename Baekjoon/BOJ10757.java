import java.util.Scanner;

public class BOJ10757 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String A = scn.next();
        String B = scn.next();
        scn.close();

        int bufferSize = 5;
        int[] sumbuffer = new int[bufferSize];
        boolean overDigit = false;
        String result = "";

        while (A.length() > 0 && B.length() > 0) {
            String subA = substrGen(A, bufferSize);
            String subB = substrGen(B, bufferSize);

            // System.out.println("A: " + A);
            // System.out.println("B: " + B);
            // System.out.println("subA: " + subA);
            // System.out.println("subB: " + subB);

            boolean tmpOverDigit = overDigit;
            for (int i = bufferSize - 1; i >= 0; i--) {
                if (tmpOverDigit) {
                    sumbuffer[i] = prepSubstr(subA, i) + prepSubstr(subB, i) + 1;
                } else {
                    sumbuffer[i] = prepSubstr(subA, i) + prepSubstr(subB, i);
                }

                if (sumbuffer[i] > 9) {
                    tmpOverDigit = true;
                } else {
                    tmpOverDigit = false;
                }
            }
            overDigit = tmpOverDigit;
            for (int i = bufferSize - 1; i >= 0; i--) {
                result = sumbuffer[i]%10 + result;
            }
            // System.out.println("result: " + result);
            if (A.length() < bufferSize && B.length() < bufferSize) {
                if (overDigit) {
                    result = 1 + result;
                    overDigit = false;
                }
                break;
            }

            if (A.length() < bufferSize) {
                B = B.substring(0, B.length() - bufferSize);
                result = B + result;
                if (overDigit) {
                    result = 1 + result;
                    overDigit = false;
                }
                // System.out.println("result: " + result);
                break;
            }
            if (B.length() < bufferSize) {
                A = A.substring(0, A.length() - bufferSize);
                result = A + result;
                if (overDigit) {
                    result = 1 + result;
                    overDigit = false;
                }
                // System.out.println("result: " + result);
                break;
            }

            A = A.substring(0, A.length() - bufferSize);
            B = B.substring(0, B.length() - bufferSize);
        }

        if (overDigit) {
            result = 1 + result;
            overDigit = false;
        }
        
        int idx = 0;
        for (idx = 0; idx < result.length() && result.charAt(idx) == '0'; idx++);
        System.out.print(result.substring(idx));
    }

    static int prepSubstr(String str, int i) {
        return Integer.parseInt(str.charAt(i) + "");
    }
    static String substrGen(String str, int bufferSize) {
        String substr = "";
        if (str.length() < bufferSize) {
            String tmp = "";
            for (int i = bufferSize; i > str.length(); i--) {
                tmp += '0';
            }
            substr = tmp + str;
        } else {
            substr = str.substring(str.length() - bufferSize);
        }
        return substr;
    }
}
