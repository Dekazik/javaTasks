package datatypes;

public class GameField {
    public String[][] gameField;
    public static int fieldSize;

    public void gameFieldInicialization(int size){
        fieldSize = size * 2 + 1;
        gameField = new String[fieldSize][fieldSize];
        int maxNumber = 4;
        boolean swc = true;
        int a = 0;

        if (fieldSize == 11) {
            maxNumber = 6;
        } else if (fieldSize == 15) {
            maxNumber = 8;
        } else if (fieldSize == 19) {
            maxNumber = 10;
        }

        for (int j = 0; j < fieldSize; j++) {
            if(fieldSize == 7) {
                gameField[0][j] = (j % maxNumber) + " | ";
            } else if (fieldSize == 11) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }else if (fieldSize == 15) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }else if (fieldSize == 19) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }
            if (j > maxNumber - 1)
            {
                gameField[0][j] = "";
            }
        }

        for (int i = 1; i < gameField.length; i++) {
            if (swc) {
                for (int j = 0; j < gameField[i].length; j++) {
                    gameField[i][j] = "——";
                }
                swc = false;
            } else {
                for (int j = 0; j < gameField[i].length; j++) {
                    if (j % 2 == 0 && j != 0) {
                        gameField[i][j] = " - ";
                    } else if (j == 0) {
                        a += 1;
                        gameField[i][j] = a + " ";
                    } else {
                        gameField[i][j] = "|";
                    }
                }
                swc = true;
            }
        }
    }

    public void printGameField(){
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                System.out.print(gameField[i][j]);
            }
            System.out.println();
        }
    }
}
