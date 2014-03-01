package com.github.bentorfs.ai.search.minimax.tictactoe;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TicTacToeBoard {

   // True is O, False is X, null is empty
   private Boolean[][] board = new Boolean[][]
      {
         { null, null, null },
         { null, null, null },
         { null, null, null } };

   public TicTacToeBoard() {
   }

   public TicTacToeBoard(TicTacToeBoard toClone) {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            board[i][j] = toClone.board[i][j];
         }
      }
   }

   public boolean hasWinner() {
      // Check if any of the rows is full and equal
      boolean rowWinner = hasRowWinner();

      // Check if any of the columns is full and equal
      boolean columnWinner = hasColumnWinner();

      // Check if any of the diagonals is full and equal
      boolean diagonalWinner = hasDiagonalWinner();

      return rowWinner || columnWinner || diagonalWinner;
   }

   private boolean hasDiagonalWinner() {
      if (board[0][0] != null && board[1][1] != null && board[2][2] != null) {
         if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return true;
         }
      }
      if (board[2][0] != null && board[1][1] != null && board[0][2] != null) {
         if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
            return true;
         }
      }
      return false;
   }

   private boolean hasRowWinner() {
      if (board[0][0] != null && board[0][1] != null && board[0][2] != null) {
         if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])) {
            return true;
         }
      }
      if (board[1][0] != null && board[1][1] != null && board[1][2] != null) {
         if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2])) {
            return true;
         }
      }
      if (board[2][0] != null && board[2][1] != null && board[2][2] != null) {
         if (board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2])) {
            return true;
         }
      }
      return false;
   }

   private boolean hasColumnWinner() {
      if (board[0][0] != null && board[1][0] != null && board[2][0] != null) {
         if (board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0])) {
            return true;
         }
      }
      if (board[0][1] != null && board[1][1] != null && board[2][1] != null) {
         if (board[0][1].equals(board[1][1]) && board[1][1].equals(board[2][1])) {
            return true;
         }
      }
      if (board[0][2] != null && board[1][2] != null && board[2][2] != null) {
         if (board[0][2].equals(board[1][2]) && board[1][2].equals(board[2][2])) {
            return true;
         }
      }
      return false;
   }

   public Collection<Coordinate> getEmptyBoxes() {
      List<Coordinate> result = new LinkedList<>();
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            if (board[i][j] == null) {
               Coordinate emptyBox = Coordinate.get(i, j);
               result.add(emptyBox);
            }
         }
      }
      return result;
   }

   public void putMark(Coordinate coord, boolean mark) {
      if (board[coord.x][coord.y] == null) {
         board[coord.x][coord.y] = mark;
      } else {
         throw new RuntimeException("Position already marked");
      }
   }

   public static class Coordinate {
      public int x, y;

      public static Coordinate get(int i, int j) {
         Coordinate result = new Coordinate();
         result.x = i;
         result.y = j;
         return result;
      }
   }

   @Override
   public String toString() {
      StringBuffer result = new StringBuffer();
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            result.append(toChar(board[i][j]) + "\t");
         }
         result.append("\n");
      }
      return result.toString();
   }

   private char toChar(Boolean value) {
      if (Boolean.TRUE.equals(value)) {
         return 'O';
      } else if (Boolean.FALSE.equals(value)) {
         return 'X';
      } else {
         return ' ';
      }
   }

   public Boolean[][] getBoard() {
      return board;
   }

   public void setBoard(Boolean[][] board) {
      this.board = board;
   }

}
