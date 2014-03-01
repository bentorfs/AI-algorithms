package com.github.bentorfs.ai.algorithms.search.asearch.mazes;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.bentorfs.ai.algorithms.search.asearch.AStarSearchNode;
import com.github.bentorfs.ai.common.TreeNode;
import com.github.bentorfs.ai.testutilities.mazes.Maze;
import com.github.bentorfs.ai.testutilities.mazes.MazeCoordinate;

/**
 * 
 * 
 * @author betorfs
 */
public class MazeNode extends AStarSearchNode {

   private Maze maze;

   private MazeCoordinate positionInMaze;

   private List<MazeCoordinate> steps = new LinkedList<>();

   public MazeNode(Maze maze, MazeCoordinate coord, List<MazeCoordinate> stepsSoFar) {
      this.maze = maze;
      this.positionInMaze = coord;
      this.steps = stepsSoFar;
   }

   public MazeNode(Maze maze) {
      this(maze, maze.getStartPosition(), new LinkedList<MazeCoordinate>());
      steps.add(maze.getStartPosition());
   }

   /** {@inheritDoc} */
   @Override
   public List<TreeNode> getChildNodes() {
      List<TreeNode> result = new LinkedList<>();
      Collection<MazeCoordinate> placesToGo = maze.getPlacesToGoFrom(positionInMaze);
      for (MazeCoordinate coord : placesToGo) {
         List<MazeCoordinate> newSteps = new LinkedList<>(steps);
         newSteps.add(coord);
         result.add(new MazeNode(maze, coord, newSteps));
      }
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public boolean isSolutionNode() {
      return maze.getEndPosition().equals(positionInMaze);
   }

   /** {@inheritDoc} */
   @Override
   public int getCostSoFar() {
      return steps.size();
   }

   /** {@inheritDoc} */
   @Override
   public int getEstimatedCostToSolution() {
      return positionInMaze.getManhattanDistance(maze.getEndPosition());
   }

   /** {@inheritDoc} */
   @Override
   public boolean isSamePosition(AStarSearchNode o) {
      if (o instanceof MazeNode) {
         return positionInMaze.equals(((MazeNode) o).getPositionInMaze());
      }
      return false;
   }

   public MazeCoordinate getPositionInMaze() {
      return positionInMaze;
   }

   public void setPositionInMaze(MazeCoordinate positionInMaze) {
      this.positionInMaze = positionInMaze;
   }

   @Override
   public String toString() {
      StringBuffer result = new StringBuffer();
      result.append("\n");
      for (int i = 0; i < maze.getxSize(); i++) {
         for (int j = 0; j < maze.getySize(); j++) {
            if (hasBeenAtPosition(i, j)) {
               result.append("X");
            } else {
               if (maze.isWall(i, j)) {
                  result.append("█");
               } else {
                  result.append(" ");
               }
            }
         }
         result.append("\n");
      }
      return result.toString();
   }

   private boolean hasBeenAtPosition(int i, int j) {
      for (MazeCoordinate step : steps) {
         if (step.getX() == i && step.getY() == j) {
            return true;
         }
      }
      return false;
   }

}
