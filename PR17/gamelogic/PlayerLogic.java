package gamelogic;

import java.util.Locale;
import java.util.Scanner;

public class PlayerLogic {
    public static String[] getPlayerCharacter(Scanner sc) {
        String playerX = " X ";
        String player0 = " 0 ";
        String firstPlayer = "";
        String secondPlayer = "";

        while (true) {
            System.out.println("Оберіть за кого будете грати Х або 0: ");
            firstPlayer = sc.nextLine().toUpperCase(Locale.ROOT);

            if (!(firstPlayer.charAt(0) == 'X' || firstPlayer.charAt(0) == '0') || firstPlayer.charAt(0) == ' ') {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
            } else {
                if (firstPlayer.charAt(0) == 'X') {
                    firstPlayer = playerX;
                    secondPlayer = player0;
                } else {
                    firstPlayer = player0;
                    secondPlayer = playerX;
                }
                break;
            }
        }
        return new String[]{firstPlayer, secondPlayer};
    }

}
