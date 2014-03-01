package com.github.bentorfs.ai.search.minimax;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.common.TreeNode;

/**
 * Minimax algorithm, with support for alpha-beta pruning and a limited search depth.
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

   public TreeNode getBestMove(TreeNode currentPosition) {
      return getBestMove(currentPosition, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, searchDepth);
   }

   private TreeNode getBestMove(TreeNode currentPosition, double alpha, double beta, int levelsLeft) {
      logger.debug("Finding best move, starting from position: {}", currentPosition);

      TreeNode result = null;
      if (currentPosition.isSolutionNode() || levelsLeft == 0) {
         return currentPosition;
      }
      // Generate all moves from the current position
      Collection<TreeNode> possibleMoves = currentPosition.getChildNodes();
      for (TreeNode possibleMove : possibleMoves) {
         TreeNode worstMove = getWorstMove(possibleMove, alpha, beta, levelsLeft - 1);
         if (worstMove != null) {
            double desirability = worstMove.getValue();
            if (desirability > alpha) {
               alpha = desirability;
               result = possibleMove;
            }
            if (alpha >= beta) {
               break;
            }
         }
      }
      return result;
   }

   private TreeNode getWorstMove(TreeNode currentPosition, double alpha, double beta, int levelsLeft) {
      logger.debug("Finding worst move, starting from position: {}", currentPosition);

      TreeNode result = null;
      if (currentPosition.isSolutionNode() || levelsLeft == 0) {
         return currentPosition;
      }
      // Generate all moves from the current position
      Collection<TreeNode> possibleMoves = currentPosition.getChildNodes();
      for (TreeNode possibleMove : possibleMoves) {
         TreeNode bestMove = getBestMove(possibleMove, alpha, beta, levelsLeft - 1);
         if (bestMove != null) {
            double desirability = bestMove.getValue();
            if (desirability < beta) {
               beta = desirability;
               result = possibleMove;
            }
            if (alpha >= beta) {
               break;
            }
         }
      }
      return result;
   }
}
