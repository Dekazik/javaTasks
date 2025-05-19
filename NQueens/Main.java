import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Мега Меню ===");
            System.out.println("1. Ферзь (Q)");
            System.out.println("2. Ладья (R)");
            System.out.println("3. Слон (B)");
            System.out.print("Введіть цифру: ");
            int choice = sc.nextInt();

            System.out.print("Введіть розмір поля: ");
            int size = sc.nextInt();

            BacktrackingSolver solver;

            switch (choice) {
                case 1:
                    System.out.println("Ви вибрали Ферзя.");
                    solver = new NQueensSolver(size);
                    break;
                case 2:
                    System.out.println("Ви вибрали Туру.");
                    solver = new NRooksSolver(size);
                    break;
                case 3:
                    System.out.println("Ви вибрали Слона.");
                    solver = new NBishopsSolver(size);
                    break;
                default:
                    System.out.println("Невірний вибір.");
                    continue;
            }

            solver.solve();
        }
    }
}
