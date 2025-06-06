/*
Максимальний прибуток зі змагань
Умова:
Дано n змагань. Кожне змагання має:
час початку start[i],
час закінчення end[i],
прибуток profit[i].
Потрібно вибрати набір неперетинаючихся змагань так, щоб максимізувати загальний прибуток.
 */
public class ContestProfit {
    public static void main(String[] args) {
        int[] start =   {1,  3, 0, 5, 8, 5};
        int[] end =     {2, 4, 6, 7, 9, 9};
        int[] profit =  {50, 20, 100, 200, 150, 80};
        int n = start.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (end[i] > end[j]) {
                    swap(start, i, j);
                    swap(end, i, j);
                    swap(profit, i, j);
                }
            }
        }

        int[] dp = new int[n];
        dp[0] = profit[0];

        for (int i = 1; i < n; i++) {
            int currentProfit = profit[i];

            int l = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (end[j] <= start[i]) {
                    l = j;
                    break;
                }
            }
            if (l != -1) {
                currentProfit += dp[l];
            }

            dp[i] = Math.max(currentProfit, dp[i - 1]);
        }

        System.out.println("Максимальний прибуток: " + dp[n - 1]);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
