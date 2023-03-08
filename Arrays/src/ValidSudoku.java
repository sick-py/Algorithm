import java.util.HashSet;

public class ValidSudoku {
    boolean checkSquare(char[][] board, int i, int j) {
        HashSet<Character> set = new HashSet<>();
        int ti = i, tj = j;
        for (; i < ti + 3; i++) {
            for (; j < tj + 3; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                } else {
                    System.out.println(c);
                    if (set.contains(c)) {
                        return false;
                    } else {
                        set.add(c);
                    }
                }
            }
        }
        return true;
    }
    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                } else {
                    if (set.contains(board[i][j])) {
                        return false;
                    } else {
                        set.add(board[i][j]);
                    }
                }
            }
        }

        for (int j = 0; j < 9; j++) {
            HashSet<Character> set = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                } else {
                    if (set.contains(c)) {
                        return false;
                    } else {
                        set.add(c);
                    }
                }
            }
        }

        /*for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!checkSquare(board, i, j)) {
                    return false;
                }
            }
        }*/

        checkSquare(board, 3, 3);

        return true;
    }
}
