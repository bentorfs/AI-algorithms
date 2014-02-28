package com.github.bentorfs.ai.algorithms.search.minimax;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimax algorithm.
 * 
 * TODO: Implement alpha-beta pruning
 * 
 * @author betorfs
 */
public class MiniMaxAlgorithm {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   private int searchDepth;

   /**
    * Create a minimax instance that searches the game tree for the best next move
    * 
    * @param searchDepth
    *           The maximum depth of the tree to search, e.g the number of moves to think ahead
    */
   public MiniMaxAlgorithm(int searchDepth) {
      this.searchDepth = searchDepth;
   }

   public MiniMaxNode getBestMove(MiniMaxNode currentPosition) {
      return getBestMove(currentPosition, searchDepth);
   }

   private MiniMaxNode getBestMove(MiniMaxNode currentPosition, int levelsLeft) {
      logger.debug("Finding best move, starting from position: {}", currentPosition);

      MiniMaxNode result = null;
      if (currentPosition.isEndNode() || levelsLeft == 0) {
         return currentPosition;
      }
      // Generate all moves from the current position
      Collection<MiniMaxNode> possibleMoves = currentPosition.getPossibleMoves();
      double highestDesirability = Double.NEGATIVE_INFINITY;
      for (MiniMaxNode possibleMove : possibleMoves) {
         MiniMaxNode worstMove = getWorstMove(possibleMove, levelsLeft - 1);
         if (worstMove != null) {
            double desirability = worstMove.getDesirability();
            if (desirability > highestDesirability) {
               highestDesirability = desirability;
               result = possibleMove;
            }
         }
      }
      return result;
   }

   private MiniMaxNode getWorstMove(MiniMaxNode currentPosition, int levelsLeft) {
      logger.debug("Finding worst move, starting from position: {}", currentPosition);

      MiniMaxNode result = null;
      if (currentPosition.isEndNode() || levelsLeft == 0) {
         return currentPosition;
      }
      // Generate all moves from the current position
      Collection<MiniMaxNode> possibleMoves = currentPosition.getPossibleMoves();
      double lowestDesirability = Double.POSITIVE_INFINITY;
      for (MiniMaxNode possibleMove : possibleMoves) {
         MiniMaxNode bestMove = getBestMove(possibleMove, levelsLeft - 1);
         if (bestMove != null) {
            double desirability = bestMove.getDesirability();
            if (desirability < lowestDesirability) {
               lowestDesirability = desirability;
               result = possibleMove;
            }
         }
      }
      return result;
   }
}
