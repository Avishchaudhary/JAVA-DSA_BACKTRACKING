public class Sudoku {
    // board ka final size declared klarte hai
    final int MAX_SIZE = 9;

    public Sudoku(int[][] board) {
        this.board = board; // ye humne constructer banaya hai taaki hum is board naam ke varuiable ko pure
                            // code mei kahi bhi used aa jau.
    }

    /*
     * isse hum ye pata lagaye nge ki kya mera sudoku correct aay ahai yaa fir nahi
     */
    public void display() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean ispresentinrow(int row, int value) {
        for (int col = 0; col < MAX_SIZE; col++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean ispresentincol(int col, int value) {
        for (int row = 0; row < MAX_SIZE; row++) {
            if (board[row][col] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean ispresentinsubgrid(int row, int col, int value) {
        // sabse pehle to mujhe ye pata hona chaiyee ki kya ye meri subgrid hai bhi yaa
        // nahi
        int startrow = row - row % 3;
        int startcol = col - col % 3;
        // ab hum start par aa gaye us subgrid ki to ab hum traverse karna start karte
        // hai by using for loop
        for (int i = startrow; i < startrow + 3; i++) {
            for (int j = startcol; j < startcol + 3; j++) {
                // or ab yha par ek condition honi chaiyee
                if (board[i][j] == value) {
                    return true;
                }
            }
        }
        return false;
    }
    // make a function

    // make a another function iscorrecttoplace
    private boolean iscorrecttoplace(int row, int col, int value) {
        // check row unique
        // check column unique
        // check sungrid unique
        return !ispresentinrow(row, value) &&
                !ispresentincol(col, value) &&
                !ispresentinsubgrid(row, col, value);

        // yha pe agar ye teeno false mil jaye to matlab hamara true hai kyoki yha par
        // hum condition false ki check kar rahe hai basicallly

    }

    int board[][];

    // public boolean solversudoku(int board[][],int row,int col){ [pehle humne ye
    // kiya tha but humko naa board ki need padegi to hum board ka clone bana ke
    // aate hai]
    public boolean solversudoku(int row, int col) {

        /*
         * edge case yaani agar vo col ko plus karte karte screen se barah nikal gaya to
         * ais anaa ho to hum ek edge case laga dete hai ki jab col last tak aa jaye to
         * usko next row par chal jana chaiyee
         */

        // edge case --column is out of board
        if (col == MAX_SIZE) {
            row = row + 1; // yaani move to the next row and re set to teh column 0.
            col = 0;
        }
        /*
         * but ek time par aisa aayega ki meri row bhi full ho jaayegi to ye mera ban
         * gay termination case
         */

        // termination case --if row is out of board
        if (row == MAX_SIZE) {
            return true;
        }

        /*
         * actually mei jab mei ye problem ko solve karunga to mujhe ye identified karna
         * hai kimere pass zero kha kha par hai or mujhe first row and first col se hi
         * start karna hai
         */

        // if cell is not empty
        if (board[row][col] != 0) {
            return solversudoku(row, col + 1);
            // yaani agar aisa hai to har baar sudoku ko bulao or col mei increment
            // kara do.

        }

        // if cell is zero/empty tab kya kare so we do put the value and check the
        // conditions/possibilities by using for loop
        for (int i = 1; i <= MAX_SIZE; i++) {
            if (iscorrecttoplace(row, col, i)) {
                board[row][col] = i; // yaani put the value in a particulat cell but we also check that it is a right
                                     // place to put the value

                // ab agar aisa ho gaya to move to tehn next cell
                boolean result = solversudoku(row, col + 1);

                // ab jab ye bhi true ho jaaye ga to
                if (result) {
                    return true; // yaani ab sab sahi hoga to ab finally return kardo true. yaani ab stack fall
                                 // hon asuru ho jaayegaa.
                }
                /*
                 * lekin agar aisa nahi hua to mei undo operation chala ke aaunga yaani
                 * backtarcking karunga bus [yaani undo the cell value this operation is
                 * performed when stack is fall]
                 */

                // to undo teh operation hoga
                board[row][col] = 0; // empty the cell
            }
        }
        // nothing works yaani chezo ne kaam nahi kiya that why we return a false.
        return false;
    }

    public static void main(String[] args) {
        // first we make a board
        // empty is represent 0
        int board[][] = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

        // ab isko chala na hai to iske constructer ka objects ban lena chaiyee
        Sudoku solver = new Sudoku(board);
        boolean result = solver.solversudoku(0, 0);
        System.out.println(result ? "Sudoku is solve...." : "Sudoku is not solve....");
        // ab hum yha par display wale function ko call lagate hai vaise ye hum laga bhi
        // sakte hai or nahi bhi ye hamari marzi hai ki hum aone sudoku ko check karna
        // chahate hai yaa fir nahi

        solver.display();
    }
}
