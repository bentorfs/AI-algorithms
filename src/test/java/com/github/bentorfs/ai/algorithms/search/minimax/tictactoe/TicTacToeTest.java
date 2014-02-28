package com.github.bentorfs.ai.algorithms.search.minimax.tictactoe;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.search.minimax.MiniMaxAlgorithm;
import com.github.bentorfs.ai.algorithms.search.minimax.MiniMaxNode;

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
      MiniMaxNode startPosition = new TicTacToeNode(board, true);

      MiniMaxNode bestMove = algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(1, bestMove.getDesirability(), 0.000001);
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
      MiniMaxNode startPosition = new TicTacToeNode(board, true);

      MiniMaxNode bestMove = algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(0, bestMove.getDesirability(), 0.000001);

      bestMove = algo.getBestMove(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals(0, bestMove.getDesirability(), 0.000001);
   }

   @Test
   public void playTicTacToeFromTheStart() {
      MiniMaxAlgorithm algo = new MiniMaxAlgorithm(4);
      MiniMaxNode startPosition = new TicTacToeNode();

      MiniMaxNode bestMove = algo.getBestMove(startPosition);

      Assert.assertNotNull(bestMove);
      System.out.println("Best move: " + bestMove);
      Assert.assertEquals("You cannot win from the first move", 0, bestMove.getDesirability(), 0.000001);
   }

   @Test
   public void playTicTacToeTwoComputersAgainstEachOther() {
      MiniMaxAlgorithm player1 = new MiniMaxAlgorithm(4);
      MiniMaxAlgorithm player2 = new MiniMaxAlgorithm(2);

      boolean player1Turn = true;
      MiniMaxNode currentPosition = new TicTacToeNode();
      do {
         TicTacToeNode currentNode = ((TicTacToeNode) currentPosition);
         currentNode.setMyTurn(true);
         if (player1Turn) {
            currentPosition = player1.getBestMove(currentPosition);
            System.out.println("Player 1: " + currentPosition);
         } else {
            currentPosition = player2.getBestMove(currentPosition);
            System.out.println("Player 2: " + currentPosition);
         }
         player1Turn = !player1Turn;
      } while (!currentPosition.isEndNode());

      Assert.assertFalse("It should be a draw", ((TicTacToeNode) currentPosition).getBoard().hasWinner());
   }
}
