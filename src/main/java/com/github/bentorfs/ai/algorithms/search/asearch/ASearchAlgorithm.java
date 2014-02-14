package com.github.bentorfs.ai.algorithms.search.asearch;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
  public Node searchSolution(Node startNode) {
    logger.info("Starting A* search");

    // This queue will contain the nodes we have not yet visited, ordered by cost from low to high
    Queue<Node> unvisitedNodes = new PriorityQueue<>();
    unvisitedNodes.add(startNode);

    // This list will contain all the nodes we have visited so far
    List<Node> visitedNodes = new LinkedList<>();

    while (!unvisitedNodes.isEmpty()) {
      Node mostPromising = unvisitedNodes.remove();
      logger.info("Visiting node: {} ", mostPromising);

      List<Node> childNodes = mostPromising.getChildNodes();
      logger.info("Node has {} children", childNodes.size());
      for (Node child : childNodes) {
        if (child.isSolution()) {
          logger.info("Solution found: {}", child);
          return child;
        }
        else if (!isAlreadyInListWithLowerCost(child, unvisitedNodes) &&
            !isAlreadyInListWithLowerCost(child, visitedNodes))
        {
          unvisitedNodes.add(child);
        }
      }
      visitedNodes.add(mostPromising);
    }

    logger.info("No solution found");
    return null;
  }

  private boolean isAlreadyInListWithLowerCost(Node candidate, Collection<Node> nodes) {
    for (Node node : nodes) {
      if (node.isSamePosition(candidate) && node.getTotalCost() <= candidate.getTotalCost()) {
        return true;
      }
    }
    return false;
  }
}
