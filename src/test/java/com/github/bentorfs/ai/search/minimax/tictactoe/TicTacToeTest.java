package com.github.bentorfs.ai.search.minimax.tictactoe;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.search.minimax.MiniMaxAlgorithm;

public class TicTacToeTest {

   @Test
   public void playTicTacToeRightBeforeWinning() {
      MiniMaxAlgorithm algo = new MiniMaxAlgorithm(5);
      TicTacToeBoard board = new TicTacToeBoard();
      board.setBoard(new Boolean[][]
         {
            { true, false, true },
            { false, true, true },
            { false, true, null } });
      TicTacToeNode startPosition = new TicTacToeNode(board, true);

      TicTacToeNode bestMove = (TicTacToeNode) algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(1, bestMove.getValue(), 0.000001);
   }

   @Test
   public void playTicTacToePreventOtherPlayerFromWinning() {
      MiniMaxAlgorithm algo = new MiniMaxAlgorithm(5);
      TicTacToeBoard board = new TicTacToeBoard();
      board.setBoard(new Boolean[][]
         {
            { true, true, false },
            { false, false, true },
            { true, null, null } });
      // Computer has to put a false
      TicTacToeNode startPosition = new TicTacToeNode(board, true);

      TicTacToeNode bestMove = (TicTacToeNode) algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(0, bestMove.getValue(), 0.000001);

      bestMove = (TicTacToeNode) algo.getBestMove(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(0, bestMove.getValue(), 0.000001);
   }

   @Test
   public void playTicTacToeFromTheStart() {
      MiniMaxAlgorithm algo = new MiniMaxAlgorithm(5);
      TicTacToeNode startPosition = new TicTacToeNode();

      TicTacToeNode bestMove = (TicTacToeNode) algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals("You cannot win from the first move", 0, bestMove.getValue(), 0.000001);
   }

   @Test
   public void playTicTacToeTwoComputersAgainstEachOther() {
      MiniMaxAlgorithm player1 = new MiniMaxAlgorithm(4);
      MiniMaxAlgorithm player2 = new MiniMaxAlgorithm(2);

      boolean player1Turn = true;
      TicTacToeNode currentPosition = new TicTacToeNode();
      do {
         TicTacToeNode currentNode = currentPosition;
         currentNode.setMyTurn(true);
         if (player1Turn) {
            currentPosition = (TicTacToeNode) player1.getBestMove(currentPosition);
            System.out.println("Player 1: " + currentPosition);
         } else {
            currentPosition = (TicTacToeNode) player2.getBestMove(currentPosition);
            System.out.println("Player 2: " + currentPosition);
         }
         player1Turn = !player1Turn;
      } while (!currentPosition.isSolutionNode());

      Assert.assertFalse("It should be a draw", currentPosition.getBoard().hasWinner());
   }
}
