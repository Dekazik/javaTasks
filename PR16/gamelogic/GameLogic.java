package gamelogic;

import java.util.Scanner;

import fieldlogic.FieldLogic;
import filemanager.SaveGameStats;
import winlogic.WinLogic;

public class GameLogic {
    public static void playGame(int size, Scanner sc, String[] players){
        System.out.println("--- Гра починається! ---");

        boolean isWin = false;
        boolean isYourTurn = true;
        boolean draw = false;
        int fieldSize = size * 2 + 1;
        int correctTurnsCount = 0;
        int col = 0;
        int row = 0;
        String winner = "";
        String selectedPlayer;
        String selectedBot = "";
        String winnerSymbol = "";

        String[][] gameField = new String[fieldSize][fieldSize];

        FieldLogic.gameFieldInicialization(fieldSize, gameField);

        FieldLogic.printGameField(gameField);

        String[] characters = PlayerLogic.getPlayerCharacter(sc);;
        selectedPlayer = characters[0];
        selectedBot = characters[1];

        while (!isWin) {
            if (isYourTurn) {
                System.out.println(players[0] + " введіть координати " + selectedPlayer + " щоб обрати СТРОКУ: ");
                row = GetCord.getRow(sc);

                System.out.println(players[0] + " введіть координати " + selectedPlayer + " щоб обрати СТОВБИЧ: ");
                col = GetCord.getColumn(sc);

                TurnLogic.makeTurn(row, col, fieldSize, gameField, selectedPlayer);
                correctTurnsCount++;
                isYourTurn = false;

                if (correctTurnsCount >= 3){
                    isWin = WinLogic.winSystem(selectedPlayer, gameField, fieldSize, isWin);
                    winner = players[0];
                    winnerSymbol = selectedPlayer;

                }

            } else {
                System.out.println(players[1] + " введіть координати " + selectedBot + " щоб обрати СТРОКУ: ");
                row = GetCord.getRow(sc);

                System.out.println(players[1] + " введіть координати " + selectedBot + " щоб обрати СТОВБИЧ: ");
                col = GetCord.getColumn(sc);

                TurnLogic.makeTurn(row, col, fieldSize, gameField, selectedBot);
                correctTurnsCount++;
                isYourTurn = true;

                if (correctTurnsCount >= 3){
                    isWin = WinLogic.winSystem(selectedBot, gameField, fieldSize, isWin);
                    winner = players[1];
                    winnerSymbol = selectedBot;
                }
            }
            draw = WinLogic.isDraw(correctTurnsCount, size);
            FieldLogic.printGameField(gameField);
            if (draw) break;
        }

        if (draw) {
            System.out.println("Перемогла дружба!\n");
        } else {
            System.out.println("Переміг " + winner + "\n");
        }
        SaveGameStats.saveStats(winner, size, winnerSymbol);
        return;
    }

}
