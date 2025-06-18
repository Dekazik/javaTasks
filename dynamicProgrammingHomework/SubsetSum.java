package dynamicProgrammingHomework;

/*
Чи можна набрати суму S з масиву
Дано масив a[] і число S. Чи можна набрати суму S, використовуючи елементи (без повторень)?
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] a = {3, 1, 5, 9};
        System.out.println(canSum(a, 14)); // true
    }

    public static boolean canSum(int[] a, int S) {
        boolean[] dp = new boolean[S + 1];
        dp[0] = true;
        for (int i = 0; i < a.length; i++) {
            for (int j = S; j >= a[i]; j--) {
                dp[j] = dp[j] || dp[j - a[i]];
            }
        }
        return dp[S];
    }
}
