import java.util.Scanner;
import java.util.Random;
//Варіант 4. Написати програму для вгадування числа — програма генерує випадкове число 
//(використовуйте java.util.Random.nextInt()), а користувач вгадує його.
// Результатом є кількість спроб вгадати число.

public class Tasc2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int tryCount = 1;

        int randomInt = random.nextInt(10) + 1;
        System.out.println("Я загадал число от 1 до 10, попробуй отгадать!");
        while (true) {
            
            int intInput = scanner.nextInt();
            
            if (randomInt == intInput) {
                System.out.println("Красава, ты отгодал всего с " + tryCount + " попытки!");
                break;
            }
            else
            {
               System.out.println("Неа, не оно, попробуй еще:");
               tryCount++;

            }            
        }
        scanner.close();
    }
}