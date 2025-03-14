import java.util.ArrayList;
import java.util.List;

public class nqueens {

    // Checks if a queen can be safely placed at the given row and column.
    public boolean isSafe(int row, int col, char[][] board) {
        // Check the column for any existing queen
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // Check upper left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check upper right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    // Converts the current board configuration into a list of strings to add to allBoards
    public void saveBoard(char[][] board, List<List<String>> allBoards) {
        List<String> newBoard = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                row.append(board[i][j] == 'Q' ? 'Q' : '.');
            }
            newBoard.add(row.toString());
        }
        allBoards.add(newBoard);
    }

    // Helper function to solve the N-Queens problem using backtracking
    public void helper(char[][] board, List<List<String>> allBoards, int row) {
        if (row == board.length) {
            saveBoard(board, allBoards);
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(row, col, board)) {
                board[row][col] = 'Q';
                helper(board, allBoards, row + 1);
                board[row][col] = '.';  // Backtrack
            }
        }
    }

    // Main function to return all solutions for N-Queens
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> allBoards = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with '.' (empty cells)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // Call the helper function starting from row 0
        helper(board, allBoards, 0);

        return allBoards;
    }

    // Testing the solution
    public static void main(String[] args) {
        nqueens nQueens = new nqueens();

        // Get the solutions for N-Queens with n = 4
        List<List<String>> result = nQueens.solveNQueens(4);

        // Printing the result as a List of Lists format
        System.out.println("result = [");
        for (int i = 0; i < result.size(); i++) {
            List<String> solution = result.get(i);
            System.out.print("    [");
            for (int j = 0; j < solution.size(); j++) {
                System.out.print("\"" + solution.get(j) + "\"");
                if (j < solution.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("],");
        }
        System.out.println("]");
    }
}
