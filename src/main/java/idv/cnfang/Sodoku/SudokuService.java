package idv.cnfang.Sodoku;

import java.util.LinkedList;
import java.util.List;

/**
Leetcode <Problem 193> Hash Table: Valid Sudoku

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true

Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
*/

public class SudokuService {
    
    BackgroundCheck bgc;
    private static char emptyChar = '.';
    
    public boolean isValidSudoku(char[][] board, int id) {
        if (bgc.checkID(id)) {
            return check(board, 0, 0);
        }
        return false;
    }
    
    private boolean check(char [][]map, int row, int col) {
        if (row == 9) return true;
        if (col == 9) return check(map, row+1, 0);
        if (map[row][col] == '.') return check(map, row, col+1);
        
        int rowQ = row/3;
        int colQ = col/3;
        for (int i = 0; i < 9; i++) {
            if (i != row && map[i][col] == map[row][col]) return false; 
            if (i != col && map[row][i] == map[row][col]) return false;
            int g = rowQ*3 + i/3;
            int h = colQ*3 + i%3;
            if (g != row && h != col && map[g][h] == map[row][col]) return false;
        }
        return check(map, row, col+1);
    }
    
    
    
    public void solveSudoku(char[][] board) {
        
        List<Cell> emptyCell = new LinkedList<Cell>();
        
        // put the empty cells on linkedlist that waited to be solved
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == emptyChar) 
                    emptyCell.add(new Cell(i, j));
      
        Cell currCell = emptyCell.remove(0);
        backtrack(board, emptyCell, currCell);
        return;
    }

    /**
    * solve the Sudoku task given all the empty cells unsolved and current cell to be tried.
    * @param board
    * @param emptyCell
    * @param currCell
    * @return
    */
    private boolean backtrack(char [][]board, List<Cell> emptyCell, Cell currCell) {
        // base case: found the solution
        if (currCell == null)
            return true;
  
        String candidate = validCandidate(board, currCell);
  
        for (int index = 0; index < candidate.length(); index++) {
            char ch = candidate.charAt(index);
            
            Cell nextCell = place_number(board, emptyCell, currCell, ch);
            
            if (backtrack(board, emptyCell, nextCell))
                return true;
            
            remove_number(board, emptyCell, currCell, nextCell);
        }
  
        return false;
    }

    /**
    * return possible candidate given position (row, col)
    * @param board
    * @param currCell
    * @return 
    */
    private String validCandidate(char [][]board, Cell currCell) {
  
        StringBuilder candidate = new StringBuilder();
        boolean []show = new boolean[9];
        
        int rowQuotient = currCell.row/3;
        int colQuotient = currCell.col/3;
        
        // check row, column, subBlock is safe or not
        for (int i = 0; i < 9; i++) {
            if (board[currCell.row][i] != emptyChar) show[board[currCell.row][i]-49] = true;
            if (board[i][currCell.col] != emptyChar) show[board[i][currCell.col]-49] = true;
            if (board[i/3 + 3*rowQuotient][i%3 + 3*colQuotient] != emptyChar) show[board[i/3 + 3*rowQuotient][i%3 + 3*colQuotient]-49] = true;
        }
  
        for (int i = 0; i < 9; i++)
            if (!show[i]) candidate.append((char)(i+49));
  
        return candidate.toString();
    }

    /**
    * place the character ch at position currCell, and return next position to be solved
    * @param board
    * @param emptyCell
    * @param currCell
    * @param ch
    * @return
    */
    private Cell place_number(char [][]board, List<Cell> emptyCell, Cell currCell, char ch) {
        board[currCell.row][currCell.col] = ch;
        if (emptyCell.isEmpty()) return null;
        return emptyCell.remove(0);
    }

    /**
    * undo the action of putting character on position currCell, and put the to-be-solved cell back on list
    * @param board
    * @param emptyCell
    * @param currCell
    * @param nextCell
    */
    private void remove_number(char [][]board, List<Cell> emptyCell, Cell currCell, Cell nextCell) {
        board[currCell.row][currCell.col] = emptyChar;
        emptyCell.add(0, nextCell);
        return;
    }
}