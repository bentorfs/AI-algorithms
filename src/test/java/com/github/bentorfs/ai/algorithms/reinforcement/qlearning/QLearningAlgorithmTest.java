package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.testutilities.SimpleLearner;
import com.github.bentorfs.ai.testutilities.mazes.Maze;
import com.github.bentorfs.ai.testutilities.mazes.MazeFactory;

/**
 * 
 * 
 * @author betorfs
 */
public class QLearningAlgorithmTest {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void learnToNavigateSimpleMaze() {
    FunctionLearner<Double> learner = new SimpleLearner();

    QLearningAlgorithm algo = new QLearningAlgorithm(0.9);
    Maze maze = MazeFactory.getSimpleMaze();
    for (int i = 0; i < 10; i++) {
      algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
    }
    logger.info("Target function after learning from " + 10 + " episodes: \n" + learner.toString());
  }

  @Test
  @Ignore("This goes into an infinite loop. Maybe we should set a limit to the number of actions?")
  public void learnToNavigateImpossibleMaze() {
    FunctionLearner<Double> learner = new SimpleLearner();

    QLearningAlgorithm algo = new QLearningAlgorithm(0.9);
    Maze maze = MazeFactory.getImpossibleMaze();
    for (int i = 0; i < 1; i++) {
      algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
    }
    logger.info("Target function after learning from " + 1 + " episodes: \n" + learner.toString());
  }

  @Test
  public void learnToNavigateBiggerMaze() {
    FunctionLearner<Double> learner = new SimpleLearner();

    QLearningAlgorithm algo = new QLearningAlgorithm(0.9);
    Maze maze = MazeFactory.getBiggerMaze();
    for (int i = 0; i < 100; i++) {
      algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
    }
    logger.info("Target function after learning from " + 100 + " episodes: \n" + learner.toString());
  }
}
