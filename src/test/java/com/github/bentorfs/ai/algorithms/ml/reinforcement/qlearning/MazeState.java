package com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.github.bentorfs.ai.testutilities.mazes.Maze;
import com.github.bentorfs.ai.testutilities.mazes.MazeCoordinate;

/**
 * 
 * 
 * @author betorfs
 */
public class MazeState implements State {

   private Maze maze;

   private MazeCoordinate positionInMaze;

   public MazeState(Maze maze, MazeCoordinate positionInMaze) {
      this.maze = maze;
      this.positionInMaze = positionInMaze;
   }

   /** {@inheritDoc} */
   @Override
   public List<State> getPossibleNextStates() {
      List<State> result = new LinkedList<>();
      Collection<MazeCoordinate> possibleCoordinates = maze.getPlacesToGoFrom(positionInMaze);
      for (MazeCoordinate possibleCoordinate : possibleCoordinates) {
         result.add(new MazeState(maze, possibleCoordinate));
      }
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public boolean isFinalState() {
      return maze.getEndPosition().equals(positionInMaze);
   }

   /** {@inheritDoc} */
   @Override
   public ActionResult goToState(State nextState) {
      double reward = 0;
      if (nextState.isFinalState()) {
         reward = 100;
      }
      return new ActionResult(reward, nextState);
   }

   @Override
   public String toString() {
      StringBuffer result = new StringBuffer();
      result.append("\n");
      for (int i = 0; i < maze.getxSize(); i++) {
         for (int j = 0; j < maze.getySize(); j++) {
            if (positionInMaze.getX() == i && positionInMaze.getY() == j) {
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

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((maze == null) ? 0 : maze.hashCode());
      result = prime * result + ((positionInMaze == null) ? 0 : positionInMaze.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      MazeState other = (MazeState) obj;
      if (maze == null) {
         if (other.maze != null) {
            return false;
         }
      } else if (!maze.equals(other.maze)) {
         return false;
      }
      if (positionInMaze == null) {
         if (other.positionInMaze != null) {
            return false;
         }
      } else if (!positionInMaze.equals(other.positionInMaze)) {
         return false;
      }
      return true;
   }

}
