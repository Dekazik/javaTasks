package gamelogic;

import java.util.Locale;
import java.util.Scanner;

public class PlayerLogic {
    public static String[] getPlayerCharacter(Scanner sc) {
        String playerX = " X ";
        String player0 = " 0 ";
        String selectedPlayer = "";
        String selectedBot = "";

        while (true) {
            System.out.println("Оберіть за кого будете грати Х або 0: ");
            selectedPlayer = sc.nextLine().toUpperCase(Locale.ROOT);

            if (!(selectedPlayer.charAt(0) == 'X' || selectedPlayer.charAt(0) == '0') || selectedPlayer.charAt(0) == ' ') {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
            } else {
                if (selectedPlayer.charAt(0) == 'X') {
                    selectedPlayer = playerX;
                    selectedBot = player0;
                } else {
                    selectedPlayer = player0;
                    selectedBot = playerX;
                }
                break;
            }
        }
        return new String[]{selectedPlayer, selectedBot};
    }

}
