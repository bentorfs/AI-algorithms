package com.github.bentorfs.ai.testutilities.mazes;

/**
 * It's a FACTORY! For MAZES!
 * 
 * @author betorfs
 */
public class MazeFactory {

  public static Maze getImpossibleMaze() {
    int[][] array = new int[][]
      {
        { 1, 0, 0 },
        { 1, 0, 0 },
        { 0, 0, 1 } };
    Maze maze = new Maze(0, 0, 2, 2, array);
    return maze;
  }

  public static Maze getSimpleMaze() {
    int[][] array = new int[][]
      {
        { 1, 0, 0 },
        { 1, 1, 0 },
        { 0, 1, 1 } };
    Maze maze = new Maze(0, 0, 2, 2, array);
    return maze;
  }

  public static Maze getBiggerMaze() {
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
    return maze;
  }
}
