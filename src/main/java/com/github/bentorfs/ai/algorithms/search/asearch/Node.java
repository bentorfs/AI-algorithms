package com.github.bentorfs.ai.algorithms.search.asearch;

import java.util.List;

/**
 * Represents a node in the search space
 * 
 * @author betorfs
 */
public abstract class Node implements Comparable<Node> {

   /*
    * Cache of the total cost, so it does not have to be re-calculated every time
    */
   private int totalCost = -1;

   /**
    * Returns all nodes that are accessible from this node
    */
   public abstract List<Node> getChildNodes();

   /**
    * Returns whether this node represents a solution in the search space
    */
   public abstract boolean isSolution();

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
   public int getTotalCost() {
      if (totalCost == -1) {
         totalCost = getCostSoFar() + getEstimatedCostToSolution();
      }
      return totalCost;
   }

   /**
    * Comparison between nodes is done using the total cost
    */
   @Override
   public int compareTo(Node o) {
      int x = getTotalCost();
      int y = o.getTotalCost();
      return (x < y) ? -1 : ((x == y) ? 0 : 1);
   }

   /**
    * Returns whether this node represents the same place in the search space as the given node
    */
   public abstract boolean isSamePosition(Node o);

}
