class Solution {

    // Checks if it's safe to place a number in the given cell
    public boolean isSafe(char[][] board, int row, int col, int number) {
        // Check the row and column
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number + '0')) {
                return false;
            }
            if (board[row][i] == (char) (number + '0')) {
                return false;
            }
        }

        // Check the 3x3 grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;

        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (board[i][j] == (char) (number + '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper function to solve the Sudoku using backtracking
    public boolean helper(char[][] board, int row, int col) {
        // If we've reached the end of the board, return true (board is solved)
        if (row == board.length) {
            return true;
        }

        int nrow = 0;
        int ncol = 0;
        // Move to the next cell
        if (col != board.length - 1) {
            nrow = row;
            ncol = col + 1;
        } else {
            nrow = row + 1;
            ncol = 0;
        }

        // If the current cell is already filled, move to the next one
        if (board[row][col] != '.') {
            return helper(board, nrow, ncol);
        } else {
            // Try placing digits 1-9
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0'); // Place the number
                    if (helper(board, nrow, ncol)) {
                        return true; // If the board is solved, return true
                    } else {
                        board[row][col] = '.'; // Backtrack if the number doesn't work
                    }
                }
            }
        }

        return false; // If no number can be placed, return false
    }

    // Function to solve the Sudoku puzzle
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }
}

public class sudoku {

    // Main function to test the solution
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example Sudoku board ('.' represents empty cells)
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        // Call the solveSudoku function to solve the board
        solution.solveSudoku(board);

        // Print the solved Sudoku board
        System.out.println("Solved Sudoku:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
