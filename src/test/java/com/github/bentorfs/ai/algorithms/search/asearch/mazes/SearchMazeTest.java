package com.github.bentorfs.ai.algorithms.search.asearch.mazes;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.search.asearch.ASearchAlgorithm;
import com.github.bentorfs.ai.algorithms.search.asearch.Node;
import com.github.bentorfs.ai.testutilities.mazes.Maze;
import com.github.bentorfs.ai.testutilities.mazes.MazeFactory;

/**
 * 
 * 
 * @author betorfs
 */
public class SearchMazeTest {

  @Test
  public void testNoSolutions() {
    Maze maze = MazeFactory.getImpossibleMaze();

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNull(solution);
  }

  @Test
  public void testOnePath() {
    int[][] array = new int[][]
      {
        { 1, 0, 0 },
        { 1, 1, 0 },
        { 0, 1, 1 } };
    Maze maze = new Maze(0, 0, 2, 2, array);

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNotNull(solution);
    Assert.assertEquals(5, solution.getTotalCost());
  }

  @Test
  public void testLongAndShortPath() {
    Maze maze = MazeFactory.getSimpleMaze();

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNotNull(solution);
    Assert.assertEquals(5, solution.getTotalCost());
  }

  @Test
  public void testBiggerMaze() {
    Maze maze = MazeFactory.getBiggerMaze();

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNotNull(solution);
    Assert.assertEquals(25, solution.getTotalCost());

    System.out.println(((MazeNode) solution).toString());
  }
}
