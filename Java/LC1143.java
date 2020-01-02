package lc1143;

import java.util.Arrays;

/**
 *
 * @author Jeffrey
 */
public class LC1143 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Test variables
        String text1 = "abcde";
        String text2 = "ace";

        LC1143 LC1143 = new LC1143();
        System.out.println(LC1143.longestCommonSubsequence(text1, text2));
        System.out.println(LC1143.longestCommonSubsequenceDPTD(text1, text2));
        System.out.println(LC1143.longestCommonSubsequenceDPBU(text1, text2));
        System.out.println(LC1143.longestCommonSubsequenceDPBUConstantSpace(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {

        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();

        return LCSHelper(x, y, x.length, y.length);
    }

    public int LCSHelper(char[] x, char[] y, int i, int j) {

        if (i < 1 || j < 1) {
            return 0;
        }

        if (x[i - 1] == y[j - 1]) {
            return 1 + LCSHelper(x, y, i - 1, j - 1);
        } else {
            return Math.max(LCSHelper(x, y, i - 1, j),
                    LCSHelper(x, y, i, j - 1));
        }
    }

    public int longestCommonSubsequenceDPTD(String text1, String text2) {

        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();
        Integer[][] memo = new Integer[x.length + 1][y.length + 1];

        return LCSDPTDHelper(x, y, x.length, y.length, memo);
    }

    public int LCSDPTDHelper(char[] x, char[] y, int i, int j,
            Integer[][] memo) {

        if (i < 1 || j < 1) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        if (x[i - 1] == y[j - 1]) {
            return 1 + LCSDPTDHelper(x, y, i - 1, j - 1, memo);
        } else {
            return memo[i][j] = Math.max(LCSDPTDHelper(x, y, i - 1, j, memo),
                    LCSDPTDHelper(x, y, i, j - 1, memo));
        }
    }

    public int longestCommonSubsequenceDPBU(String text1, String text2) {

        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();
        int m = x.length;
        int n = y.length;
        int[][] memo = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 0;
                } else if (x[i - 1] == y[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        return memo[m][n];
    }

    public int longestCommonSubsequenceDPBUConstantSpace(String text1,
            String text2) {

        char[] x = text1.toCharArray();
        char[] y = text2.toCharArray();
        int m = x.length;
        int n = y.length;
        int[] memo = new int[n + 1];

        for (int i = 1; i < m + 1; i++) {
            int prev = 0;

            for (int j = 1; j < n + 1; j++) {
                int temp = memo[j];

                if (x[i - 1] == y[j - 1]) {
                    memo[j] = prev + 1;
                } else {
                    memo[j] = Math.max(memo[j], memo[j - 1]);
                }
                prev = temp;
            }
        }
        return memo[n];
    }
}