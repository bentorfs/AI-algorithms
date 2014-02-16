package com.github.bentorfs.ai.testutilities.mazes;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public class Maze {

   private MazeCoordinate start;
   private MazeCoordinate end;

   private int xSize;
   private int ySize;

   private int[][] map;

   public Maze(int startPositionX, int startPositionY, int endPositionX, int endPositionY, int[][] map) {
      super();
      this.start = new MazeCoordinate(startPositionX, startPositionY);
      this.end = new MazeCoordinate(endPositionX, endPositionY);
      this.map = map;
      this.xSize = map.length;
      this.ySize = map[0].length;
   }

   public MazeCoordinate getStartPosition() {
      return start;
   }

   public MazeCoordinate getEndPosition() {
      return end;

   }

   public Collection<MazeCoordinate> getPlacesToGoFrom(MazeCoordinate position) {
      List<MazeCoordinate> result = new LinkedList<>();
      if (isWithinLimits(position.getX() + 1, position.getY(), map) && map[position.getX() + 1][position.getY()] != 0) {
         // Possible to go one step to the right
         result.add(new MazeCoordinate(position.getX() + 1, position.getY()));
      }
      if (isWithinLimits(position.getX() - 1, position.getY(), map) && map[position.getX() - 1][position.getY()] != 0) {
         // Possible to go one step to the left
         result.add(new MazeCoordinate(position.getX() - 1, position.getY()));
      }
      if (isWithinLimits(position.getX(), position.getY() + 1, map) && map[position.getX()][position.getY() + 1] != 0) {
         // Possible to go one step up
         result.add(new MazeCoordinate(position.getX(), position.getY() + 1));
      }
      if (isWithinLimits(position.getX(), position.getY() - 1, map) && map[position.getX()][position.getY() - 1] != 0) {
         // Possible to go one step to the bottom
         result.add(new MazeCoordinate(position.getX(), position.getY() - 1));
      }
      return result;
   }

   private boolean isWithinLimits(int x, int y, int[][] map) {
      if (x < 0 || y < 0) {
         return false;
      }
      if (x > map.length - 1 || y > map[0].length - 1) {
         return false;
      }
      return true;
   }

   public int getxSize() {
      return xSize;
   }

   public int getySize() {
      return ySize;
   }

   public boolean isWall(int x, int y) {
      if (map[x][y] == 0) {
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((end == null) ? 0 : end.hashCode());
      result = prime * result + Arrays.hashCode(map);
      result = prime * result + ((start == null) ? 0 : start.hashCode());
      result = prime * result + xSize;
      result = prime * result + ySize;
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
      Maze other = (Maze) obj;
      if (end == null) {
         if (other.end != null) {
            return false;
         }
      } else if (!end.equals(other.end)) {
         return false;
      }
      if (!Arrays.equals(map, other.map)) {
         return false;
      }
      if (start == null) {
         if (other.start != null) {
            return false;
         }
      } else if (!start.equals(other.start)) {
         return false;
      }
      if (xSize != other.xSize) {
         return false;
      }
      if (ySize != other.ySize) {
         return false;
      }
      return true;
   }

}
