package com.github.bentorfs.ai.algorithms.search.asearch.mazes;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.search.asearch.ASearchAlgorithm;
import com.github.bentorfs.ai.algorithms.search.asearch.AStarSearchNode;
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

      AStarSearchNode startNode = new MazeNode(maze);
      AStarSearchNode solution = algo.searchSolution(startNode);

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

      AStarSearchNode startNode = new MazeNode(maze);
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      Assert.assertEquals(5, solution.getValue(), 0);
   }

   @Test
   public void testLongAndShortPath() {
      Maze maze = MazeFactory.getSimpleMazeWithOneSolution();

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new MazeNode(maze);
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      Assert.assertEquals(5, solution.getValue(), 0);
   }

   @Test
   public void testBiggerMaze() {
      Maze maze = MazeFactory.getBiggerMaze();

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new MazeNode(maze);
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      Assert.assertEquals(25, solution.getValue(), 0);

      System.out.println(((MazeNode) solution).toString());
   }
}
