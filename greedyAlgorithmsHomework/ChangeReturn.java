
/*
Задача про здачу
Умова: Магазин видає здачу номіналами {50, 20, 10, 5, 1}.
Треба видати здачу сумою S з найменшою кількістю купюр.
 */
public class ChangeReturn {
    public static void main(String[] args) {
        int[] bills = {50, 20, 10, 5, 1};
        int S = 73;
        int count = 0;

        for (int i = 0; i < bills.length; i++) {
            while (S >= bills[i]) {
                S = S - bills[i];
                count++;
                System.out.println("Беремо купюру номіналом %s".formatted(bills[i]));
                System.out.println("Залишилось добрати %s".formatted(S));
            }
        }

        System.out.println("Купюр видано: " + count);
    }
}
