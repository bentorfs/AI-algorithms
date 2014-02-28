package com.github.bentorfs.ai.algorithms.search.minimax.tictactoe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.bentorfs.ai.algorithms.search.minimax.MiniMaxNode;
import com.github.bentorfs.ai.algorithms.search.minimax.tictactoe.TicTacToeBoard.Coordinate;

public class TicTacToeNode implements MiniMaxNode {

   private boolean myTurn;

   private TicTacToeBoard board;

   public TicTacToeNode() {
      this.board = new TicTacToeBoard();
      this.myTurn = true;
   }

   public TicTacToeNode(TicTacToeBoard board, boolean myTurn) {
      this.board = board;
      this.myTurn = myTurn;
   }

   @Override
   public Collection<MiniMaxNode> getPossibleMoves() {
      List<MiniMaxNode> result = new ArrayList<>();
      for (Coordinate emptyBox : board.getEmptyBoxes()) {
         // Put a TRUE
         TicTacToeBoard boardWithTrue = new TicTacToeBoard(board);
         boardWithTrue.putMark(emptyBox, true);
         TicTacToeNode childNodeWithTrue = new TicTacToeNode(boardWithTrue, !myTurn);
         result.add(childNodeWithTrue);

         // Put a FALSE
         TicTacToeBoard boardWithFalse = new TicTacToeBoard(board);
         boardWithFalse.putMark(emptyBox, false);
         TicTacToeNode childNodeWithFalse = new TicTacToeNode(boardWithFalse, !myTurn);
         result.add(childNodeWithFalse);
      }
      return result;
   }

   @Override
   public boolean isEndNode() {
      return board.hasWinner() || (board.getEmptyBoxes().size() == 0);
   }

   @Override
   public double getDesirability() {
      if (board.hasWinner() && myTurn) {
         return -1; // I lost
      } else if (board.hasWinner() && !myTurn) {
         return 1; // I won
      }
      return 0;
   }

   @Override
   public String toString() {
      return "My turn: " + myTurn + "\n" + board.toString();
   }

   public boolean isMyTurn() {
      return myTurn;
   }

   public void setMyTurn(boolean myTurn) {
      this.myTurn = myTurn;
   }

   public TicTacToeBoard getBoard() {
      return board;
   }

   public void setBoard(TicTacToeBoard board) {
      this.board = board;
   }

}
