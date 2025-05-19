package gamelogic;

import java.util.Scanner;

public class GetCord {
    public static int getColumn(Scanner sc) {
        int col;
        while (true) {
            if (sc.hasNextInt()) {
                col = sc.nextInt() + 1;

                if (col == 3) {
                    col += 1;
                } else if (col >= 5) {
                    col += (col - 2);
                } else if (col != 2) {
                    col += 2;
                }

                return col;
            } else {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
                sc.next();
            }
        }
    }

    public static int getRow(Scanner sc) {
        int row;
        while (true) {
            if (sc.hasNextInt()) {
                row = sc.nextInt() + 1;

                if (row == 3) {
                    row += 1;
                } else if (row >= 5) {
                    row += (row - 2);
                } else if (row != 2) {
                    row += 2;
                }

                return row;
            } else {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
                sc.next();
            }
        }
    }

}
