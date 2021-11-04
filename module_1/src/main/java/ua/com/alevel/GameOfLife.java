package ua.com.alevel;

public class GameOfLife {

    private static final int HEIGHT = 10;
    private static final int WIDTH = 10;
    private static boolean[][] board = new boolean[HEIGHT][WIDTH];
    private static int[][] boardWithNumberOfLivingBoxes = new int[HEIGHT][WIDTH];

    public static void main(String[] args) {
        int startTaskAgain;
        do {
            board[0][1] = true;
            board[1][1] = true;
            board[1][2] = true;
            board[2][1] = true;
            board[2][2] = true;
            board[3][2] = true;
            board[4][1] = true;
            board[4][3] = true;
            board[5][5] = true;
            board[5][6] = true;
            board[6][2] = true;
            board[6][7] = true;
            board[7][2] = true;
            board[7][5] = true;
            board[8][1] = true;
            board[8][9] = true;

            System.out.println("Стартовое состояние игры");
            printBoard();
            for (int i = 0; i < 10; i++) {
                step();
                System.out.println("Состояние игры после шага " + (i + 1));
                printBoard();
            }
            cleanBoard();
            startTaskAgain = MenuForTaskUtil.menu();
            System.out.println();
        } while (startTaskAgain != 0);
    }

    private static void printBoard() {
        System.out.println("__________");
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
        System.out.println("__________");
    }

    private static int numberOfLivingBoxes(int indexHeight, int indexWidth) {
        int numberOfLivingBoxes = 0;
        for (int i = indexHeight - 1; i <= indexHeight + 1; i++) {
            for (int j = indexWidth - 1; j <= indexWidth + 1; j++) {
                try {
                    if (!(i == indexHeight && j == indexWidth)) {
                        if (board[i][j]) {
                            numberOfLivingBoxes++;
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        return numberOfLivingBoxes;
    }

    private static void fillingBoardWithNumberOfLivingBoxes() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boardWithNumberOfLivingBoxes[i][j] = numberOfLivingBoxes(i, j);
            }
        }
    }

    private static boolean beOrNotToBe(int indexHeight, int indexWidth) {
        int numberOfLivingBoxes = boardWithNumberOfLivingBoxes[indexHeight][indexWidth];
        if (numberOfLivingBoxes == 3 || (numberOfLivingBoxes == 2 && board[indexHeight][indexWidth])) {
            return true;
        } else {
            return false;
        }
    }

    private static void step() {
        fillingBoardWithNumberOfLivingBoxes();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = beOrNotToBe(i, j);
            }
        }
    }

    private static void cleanBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                board[i][j] = false;
            }
        }
    }
}
