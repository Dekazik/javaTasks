package dynamicProgrammingHomework;
/*
Кількість способів дістатися до кінця масиву
Дано число n. На кожному кроці можна рухатись на 1 або 2.
Скількома способами можна дійти з 0 до n
 */
public class WaysToReachEnd {
    public static void main(String[] args) {
        System.out.println(countWays(5)); // Output: 8
    }

    public static int countWays(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i >= 2) {
                dp[i] = dp[i] + dp[i - 2];
            }
        }
        return dp[n];
    }
}
