import java.util.Scanner;

public class HomeWork2 {
    static void tromino(int x_board, /* x coordinate of board */
                        int y_board, /* y coordinate of board */
                        int x_missing, /* x coordinate of missing square */
                        int y_missing, /* y coordinate of missing square */
                        int board_size /* size of board, which is a power of 2*/) {
        //System.out.printf("%d %d %d", x_missing, y_missing, board_size);
        String[][] board = new String[board_size][board_size];
        helper(x_board, y_board, x_missing, y_missing, board_size, board, "MS");

        rotate(board);
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                System.out.printf(" %s ", board[i][j]);
            }
            System.out.printf("\n");
        }

    }

    private static void helper(int x_board, int y_board, int x_missing, int y_missing, int boardSize, String[][] board, String pos) {
        if (boardSize < 2) {
            //run case?
            return;
        }
        //boolean change = false;
        if (boardSize == 2) {//change the 2X2 board to the right position
            //base case
            for (int i = x_board; i < x_board + 2; i++) {
                for (int j = y_board; j < y_board + 2; j++) {
                    //System.out.printf("i: %d j:%d\n", i, j);
                    if (i == x_missing && j == y_missing) {
                        //change = true;
                        //System.out.printf("inloop\n");
                        board[i][j] = pos;
                        //System.out.printf("%d %d %s", i, j, board[i][j]);
                        if (i == x_board && j == y_board) {
                            board[i][j + 1] = "UR";
                            //System.out.printf("%d %d %s", i + 1, j, board[i + 1][j]);
                            board[i + 1][j] = "UR";
                            //System.out.printf("%d %d %s", i, j, board[i][j]);
                            board[i + 1][j + 1] = "UR";
                            //System.out.printf("%d %d %s", i, j, board[i][j]);
                        } else if (i == x_board && j == y_board + 1) {
                            board[i][j - 1] = board[i + 1][j] = board[i + 1][j - 1] = "LR";
                        } else if (i == x_board + 1 && j == y_board) {
                            board[i - 1][j] = board[i - 1][j + 1] = board[i][j + 1] = "UL";
                        } else {
                            board[i - 1][j - 1] = board[i - 1][j] = board[i][j - 1] = "LL";
                        }
                    }
//                    if (!change) {
//                        System.out.printf("here did nothing i: %d, j %d, missingx: %d misingy %d\n", i, j, x_missing, y_missing);
//                    }
                }
            }
        }

        //divide
        int half_size = boardSize / 2;
        int x_upper_left = x_board + half_size - 1, y_upper_left = y_board + half_size, x_upper_right = x_board + half_size, y_upper_right = y_board + half_size;
        int x_lower_left = x_board + half_size - 1, y_lower_left = y_board + half_size - 1, x_lower_right = x_board + half_size, y_lower_right = y_board + half_size - 1;
        if (x_missing < half_size && y_missing >= half_size) {//UL
            helper( x_board, y_board + half_size, x_missing, y_missing, half_size, board, pos);//UL
            helper( x_board + half_size, y_board + half_size, x_upper_right, y_upper_right, half_size, board,"LR" );
            helper( x_board, y_board, x_lower_left, y_lower_left, half_size, board, "LR");
            helper( x_board + half_size, y_board, x_lower_right, y_lower_right, half_size, board, "LR");
        } else if (x_missing >= half_size && y_missing >= half_size){
            helper( x_board, y_board + half_size, x_upper_left, y_upper_left, half_size, board, "LL");//UL
            helper( x_board + half_size, y_board + half_size, x_missing, y_missing, half_size, board,pos );
            helper( x_board, y_board, x_lower_left, y_lower_left, half_size, board, "LL");
            helper( x_board + half_size, y_board, x_lower_right, y_lower_right, half_size, board, "LL");
        } else if (x_missing < half_size && y_missing < half_size) {
            helper( x_board, y_board + half_size, x_upper_left, y_upper_left, half_size, board, "UR");//UL
            helper( x_board + half_size, y_board + half_size, x_upper_right, y_upper_right, half_size, board,"UR" );
            helper( x_board, y_board, x_missing, y_missing, half_size, board, pos);
            helper( x_board + half_size, y_board, x_lower_right, y_lower_right, half_size, board, "UR");
        } else if (x_missing >= half_size && y_missing < half_size){
            helper( x_board, y_board + half_size, x_upper_left, y_upper_left, half_size, board, "UL");//UL
            helper( x_board + half_size, y_board + half_size, x_upper_right, y_upper_right, half_size, board,"UL" );
            helper( x_board, y_board, x_lower_left, y_lower_left, half_size, board, "UL");
            helper( x_board + half_size, y_board, x_missing, y_missing, half_size, board, pos);
        } else {
            System.out.printf("error here i: %d j %d, mx %d my %d", x_board, y_board, x_missing, y_missing);
        }

    }


    public static void main(String[] args) {
        int board_size;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Please enter size of board (need to be 2^n and 0 to quit):");
        board_size = scanner.nextInt();
        if (board_size == 0) {
            System.out.printf("Quit!\n");
            return;
        }
        if (!checkPower(board_size)) {
            System.out.printf("Please input power of 2. \n");
            return;
        }

        int x_missing, y_missing;
        System.out.printf("“Please enter coordinates of missing square (separate by a space):");
        x_missing = scanner.nextInt();
        y_missing = scanner.nextInt();
        tromino( 0, 0, x_missing, y_missing, board_size);
    }

    static boolean checkPower(int x) {
        while (((x % 2) == 0) && x > 1) {
            x /= 2;
        }
        return x == 1;
    }

    static void rotate(String[][] matrix) {
        int n = matrix.length;
        //mirror
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                String temp = matrix[j][i];
                matrix[j][i] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = temp;
            }
        }
    }

}
