//sudoku solver prolem
public class Practise {
    // assigned tha final size of the board
    final int maxsize = 9;

    // make a constructor of teh variable board
    public Practise(int board[][]) {
        // assigned the variable
        this.board = board;
    }

    // make a function display
    private void display() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // make a function ispresentinrow
    private boolean ispresentinrow(int row, int value) {
        for (int col = 0; col < maxsize; col++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    // make a function ispresentincol
    private boolean ispresentincol(int col, int value) {
        for (int row = 0; row < maxsize; row++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    // make a function ispresentinsubgrid
    private boolean ispresentinsubgrid(int row, int col, int value) {
        int startrow = row - row % 3;
        int startcol = col - col % 3;
        for (int i = startrow; i < startrow + 3; i++) {
            for (int j = startcol; j < startcol + 3; j++) {
                if (board[i][j] == value) {
                    return true;
                }
            }
        }
        return false;
    }

    // make a final function isacorrectplacethevalue - it is a function that full
    // fill the all above function
    private boolean iscorrecttoplacevalue(int row, int col, int value) {
        return !ispresentinrow(row, value) &&
                !ispresentincol(col, value) &&
                !ispresentinsubgrid(row, col, value);
    }

    // declared a variable board
    int board[][];

    // make a function sudokusolver
    public boolean sudokusolver(int row, int col) {
        // edge case
        if (col == maxsize) {
            // we increment teh row by +1
            row = row + 1;
            // and also reset the colimn
            col = 0;
        }
        // termination case
        if (row == maxsize) {
            return true;
        }
        // small problem
        // agar cell mei valuye empty naa hui to
        if (board[row][col] != 0) {
            return sudokusolver(row, col + 1);

        }
        for (int i = 1; i <= maxsize; i++) {
            if (iscorrecttoplacevalue(row, col, i)) {
                // if this function can fullfill the whole condition then it will assign the
                // value to the cell
                // simply assigned the value
                board[row][col] = i;
                boolean result = sudokusolver(row, col + 1);
                // ab agar ye sab fullfill hota gaya to last mei return kara do true
                if (result) {
                    return true;
                }
                // lekin agar ais anahi hua to undo teh operations yaani apply the backtracking
                // rule
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // first we need to create a board
        int board[][] = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
        // make a constructor
        Practise solver = new Practise(board);
        boolean result = solver.sudokusolver(0, 0);
        // System.out.println(result ? "Sudoku is solve" : "Sudoku is not solve");
        System.out.println(result ? "Sudoku is solve...." : "Sudoku is not solve....");
        solver.display();
    }
}