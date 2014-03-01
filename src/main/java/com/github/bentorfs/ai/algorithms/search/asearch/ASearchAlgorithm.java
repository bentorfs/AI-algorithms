package com.github.bentorfs.ai.algorithms.search.asearch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.common.TreeNode;

/**
 * 
 * 
 * @author betorfs
 */
public class ASearchAlgorithm {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   /**
    * Searches the child nodes of startNode for a solution node and returns it.
    * 
    * Returns null if no solution could be found
    */
   public AStarSearchNode searchSolution(AStarSearchNode startNode) {
      logger.debug("Starting A* search");

      // This queue will contain the nodes we have not yet visited, ordered by cost from low to high
      Queue<TreeNode> unvisitedNodes = new PriorityQueue<>();
      unvisitedNodes.add(startNode);

      // This list will contain all the nodes we have visited so far
      List<TreeNode> visitedNodes = new LinkedList<>();

      while (!unvisitedNodes.isEmpty()) {
         TreeNode mostPromising = unvisitedNodes.remove();
         logger.debug("A* Search is visiting node: {} ", mostPromising);

         Collection<TreeNode> childNodes = mostPromising.getChildNodes();
         logger.debug("Node has {} children", childNodes.size());
         for (TreeNode child : childNodes) {
            if (child.isSolutionNode()) {
               logger.debug("A* search found solution: {}", child);
               return (AStarSearchNode) child;
            } else if (!isAlreadyInListWithLowerCost(child, unvisitedNodes)
                  && !isAlreadyInListWithLowerCost(child, visitedNodes)) {
               unvisitedNodes.add(child);
            }
         }
         visitedNodes.add(mostPromising);
      }

      logger.debug("A* Search found no solution");
      return null;
   }

   private boolean isAlreadyInListWithLowerCost(TreeNode candidate, Collection<TreeNode> nodes) {
      for (TreeNode node : nodes) {
         AStarSearchNode searchNode = (AStarSearchNode) node;
         AStarSearchNode candidateNode = (AStarSearchNode) candidate;
         if (searchNode.isSamePosition(candidateNode) && searchNode.getValue() <= candidateNode.getValue()) {
            return true;
         }
      }
      return false;
   }
}
