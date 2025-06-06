package gamelogic;

import java.util.Scanner;

import datatypes.*;

import filemanager.SaveGameStats;
import winlogic.WinLogic;

public class GameLogic {

    public static void playGame(int size, Scanner sc, String[] players){
        GameField gameField = new GameField();
        GameSettings settings = new GameSettings();
        GameStats stats = new GameStats();


        System.out.println("--- Гра починається! ---");

        int correctTurnsCount = 0;
        int col = 0;
        int row = 0;

        gameField.gameFieldInicialization(settings.size);
        gameField.printGameField();

        String[] characters = PlayerLogic.getPlayerCharacter(sc);;
        settings.firstPlayer = characters[0];
        settings.secondPlayer = characters[1];

        while (!stats.isWin) {
            if (stats.isYourTurn) {
                System.out.println(players[0] + " введіть координати " + settings.firstPlayer + " щоб обрати СТРОКУ: ");
                row = GetCord.getRow(sc);

                System.out.println(players[0] + " введіть координати " + settings.firstPlayer + " щоб обрати СТОВБИЧ: ");
                col = GetCord.getColumn(sc);

                TurnLogic.makeTurn(row, col, GameField.fieldSize, gameField.gameField, settings.firstPlayer);
                correctTurnsCount++;
                stats.isYourTurn = false;

                if (correctTurnsCount >= 3){
                    stats.isWin = WinLogic.winSystem(settings.firstPlayer, gameField.gameField, GameField.fieldSize, stats.isWin);
                    stats.winner = players[0];
                    stats.winnerSymbol = settings.firstPlayer;

                }

            } else {
                System.out.println(players[1] + " введіть координати " + settings.secondPlayer + " щоб обрати СТРОКУ: ");
                row = GetCord.getRow(sc);

                System.out.println(players[1] + " введіть координати " + settings.secondPlayer + " щоб обрати СТОВБИЧ: ");
                col = GetCord.getColumn(sc);

                TurnLogic.makeTurn(row, col, GameField.fieldSize, gameField.gameField, settings.secondPlayer);
                correctTurnsCount++;
                stats.isYourTurn = true;

                if (correctTurnsCount >= 3){
                    stats.isWin = WinLogic.winSystem(settings.secondPlayer, gameField.gameField, GameField.fieldSize, stats.isWin);
                    stats.winner = players[1];
                    stats.winnerSymbol = settings.secondPlayer;
                }
            }
            stats.draw = WinLogic.isDraw(correctTurnsCount, size);
            gameField.printGameField();
            if (stats.draw) break;
        }

        if (stats.draw) {
            System.out.println("Перемогла дружба!\n");
        } else {
            System.out.println("Переміг " + stats.winner + "\n");
        }
        SaveGameStats.saveStats(stats.winner, size, stats.winnerSymbol);
        return;
    }

}
