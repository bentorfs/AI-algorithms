package com.github.bentorfs.ai.algorithms.search.asearch.mazes;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.search.asearch.ASearchAlgorithm;
import com.github.bentorfs.ai.algorithms.search.asearch.Node;


/**
 * 
 * 
 * @author betorfs
 */
public class SearchMazeTest {

  @Test
  public void testNoSolutions() {
    int[][] array = new int[][]
      {
        { 1, 0, 0 },
        { 0, 0, 0 },
        { 0, 0, 1 } };
    Maze maze = new Maze(0, 0, 2, 2, array);

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
    int[][] array = new int[][]
      {
        { 1, 0, 0 },
        { 1, 0, 0 },
        { 1, 1, 1 },
        { 1, 0, 1 },
        { 1, 1, 1 } };
    Maze maze = new Maze(0, 0, 2, 2, array);

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNotNull(solution);
    Assert.assertEquals(5, solution.getTotalCost());
  }

  @Test
  public void testBiggerMaze() {
    int[][] array = new int[][]
      {
        { 1, 0, 1, 1, 1, 0, 0, 0, 0, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
        { 1, 0, 1, 0, 0, 0, 1, 0, 1, 0 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
        { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1 },
        { 0, 1, 0, 0, 1, 0, 1, 1, 0, 1 },
        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 0 },
        { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 } };
    Maze maze = new Maze(0, 0, 9, 9, array);

    ASearchAlgorithm algo = new ASearchAlgorithm();

    Node startNode = new MazeNode(maze);
    Node solution = algo.searchSolution(startNode);

    Assert.assertNotNull(solution);
    Assert.assertEquals(25, solution.getTotalCost());

    System.out.println(((MazeNode) solution).toString());
  }
}
