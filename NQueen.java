public class NQueen {
    // make a another function can i create a queen yaani saari conditions ko check
    // karenge hum, is function mei
    static boolean caniplacedqueen(boolean board[][], int currentrow, int col) {
        // all the constraint check
        // 1.same row--above row and same column
        for (int i = currentrow; i >= 0; i--) {
            if (board[i][col]) {
                return false;
            }
        }
        // 2.check upper left diogonal side
        for (int i = currentrow, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }
        // check upper right diogional side
        for (int i = currentrow, j = col; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }
        return true;
    }

    // make a function to count the number of ways to place queen
    static int countnumberofwaystoplacequeen(boolean board[][], int currentrow) {
        int count = 0;
        // termination case
        if (currentrow == board.length) {
            return 1; // it means i return a count(1 ways i have done)
        }
        // 1st steps is traverse the column
        for (int cols = 0; cols < board[currentrow].length; cols++) {
            // check before placed
            if (caniplacedqueen(board, currentrow, cols)) {
                // place a queen in a cell
                board[currentrow][cols] = true;
                // or agar ye true hogi to ab isko aage ke liye recursive call lagao
                count += countnumberofwaystoplacequeen(board, currentrow + 1);
                // backtrack picture mei ab aayegito uska simple tarika hai ye
                board[currentrow][cols] = false; // undo the changes
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 4x4 matrix and it is boolean
        boolean[][] board = new boolean[4][4];
        int ways = countnumberofwaystoplacequeen(board, 0);
        System.out.println("Number of ways " + ways);
    }
}