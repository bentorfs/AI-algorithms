package com.github.bentorfs.ai.search.asearch;

import java.util.List;

import com.github.bentorfs.ai.common.TreeNode;

/**
 * Represents a node in the search space
 * 
 * @author betorfs
 */
public abstract class AStarSearchNode implements Comparable<AStarSearchNode>, TreeNode {

   /*
    * Cache of the total cost, so it does not have to be re-calculated every time
    */
   private int totalCost = -1;

   /**
    * Returns all nodes that are accessible from this node
    */
   @Override
   public abstract List<TreeNode> getChildNodes();

   /**
    * Returns whether this node represents a solution in the search space
    */
   @Override
   public abstract boolean isSolutionNode();

   /**
    * Returns the cost that was needed to reach this node
    * 
    */
   public abstract int getCostSoFar();

   /**
    * Returns the estimated cost to reach a solution, starting from this node
    * 
    */
   public abstract int getEstimatedCostToSolution();

   /**
    * Returns the total cost of this node, which is the sum of:
    * 
    * - The effort that was needed to reach this node (getCostSoFar)
    * 
    * - The estimated cost to reach a solution (getEstimatedCostToSolution)
    */
   @Override
   public double getValue() {
      if (totalCost == -1) {
         totalCost = getCostSoFar() + getEstimatedCostToSolution();
      }
      return totalCost;
   }

   /**
    * Comparison between nodes is done using the total cost
    */
   @Override
   public int compareTo(AStarSearchNode o) {
      double x = getValue();
      double y = o.getValue();
      return (x < y) ? -1 : ((x == y) ? 0 : 1);
   }

   /**
    * Returns whether this node represents the same place in the search space as the given node
    */
   public abstract boolean isSamePosition(AStarSearchNode o);

}
