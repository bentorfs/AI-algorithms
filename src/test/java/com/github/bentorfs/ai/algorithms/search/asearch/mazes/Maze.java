package com.github.bentorfs.ai.algorithms.search.asearch.mazes;

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

  public Maze(int startX, int startY, int endX, int endY, int[][] map) {
    super();
    this.start = new MazeCoordinate(startX, startY);
    this.end = new MazeCoordinate(endX, endY);
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
    if (x > map[0].length - 1 || y > map.length - 1) {
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
    }
    else {
      return false;
    }
  }

}
