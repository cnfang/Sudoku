package idv.cnfang.Sodoku;

public class Cell {
      public final int row;
      public final int col;
      public Cell(int r, int c) {
          row = r;
          col = c;
      }
      
      public int getRow() {return row;}
      public int getCol() {return col;}
 }