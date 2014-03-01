package com.github.bentorfs.ai.ml.reinforcement.qlearning;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.Episode;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.QLearningAlgorithm;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.strategy.BoltzmannStrategy;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.strategy.EpsilonGreedyStrategy;
import com.github.bentorfs.ai.testutilities.SimpleLearner;
import com.github.bentorfs.ai.testutilities.mazes.Maze;
import com.github.bentorfs.ai.testutilities.mazes.MazeFactory;

/**
 * There are no assertions in this test, because every episode is the result of a random process. So, the algorithm will
 * learn its Q-function, but you can never be sure it will take the optimal path
 * 
 * @author betorfs
 */
public class QLearningAlgorithmTest {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   @Test
   public void learnToNavigateSimpleMazeUsingBoltzmann() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, BoltzmannStrategy.withTemperature(10));
      Maze maze = MazeFactory.getSimpleMazeWithOneSolution();
      for (int i = 0; i < 10; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateSimpleMazeUsingEpsilon() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, EpsilonGreedyStrategy.withEpsilon(0.1));
      Maze maze = MazeFactory.getSimpleMazeWithOneSolution();
      for (int i = 0; i < 10; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateMazeWithEquivalentSolutionsUsingBoltzmann() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, BoltzmannStrategy.withTemperature(10));
      Maze maze = MazeFactory.getSimpleMazeWithTwoEquivalentSolutions();
      for (int i = 0; i < 30; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateMazeWithEquivalentSolutionsUsingEpsilon() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, EpsilonGreedyStrategy.withEpsilon(0.1));
      Maze maze = MazeFactory.getSimpleMazeWithTwoEquivalentSolutions();
      for (int i = 0; i < 30; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateMazeWithUnequivalentSolutionsUsingBoltzmann() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, BoltzmannStrategy.withTemperature(10));
      Maze maze = MazeFactory.getSimpleMazeWithTwoUnequivalentSolutions();
      for (int i = 0; i < 100; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateMazeWithUnequivalentSolutionsUsingEpsilon() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, EpsilonGreedyStrategy.withEpsilon(0.1));
      Maze maze = MazeFactory.getSimpleMazeWithTwoUnequivalentSolutions();
      for (int i = 0; i < 100; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   @Ignore("This goes into an infinite loop. Maybe we should set a limit to the number of actions?")
   public void learnToNavigateImpossibleMaze() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, BoltzmannStrategy.withTemperature(10));
      Maze maze = MazeFactory.getImpossibleMaze();
      for (int i = 0; i < 1; i++) {
         algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateBiggerMazeUsingBoltzmann() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, BoltzmannStrategy.withTemperature(10));
      Maze maze = MazeFactory.getBiggerMaze();
      for (int i = 0; i < 100; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }

   @Test
   public void learnToNavigateBiggerMazeUsingEpsilon() {
      FunctionLearner<Object, Double> learner = new SimpleLearner();

      QLearningAlgorithm algo = new QLearningAlgorithm(0.9, EpsilonGreedyStrategy.withEpsilon(0.1));
      Maze maze = MazeFactory.getBiggerMaze();
      for (int i = 0; i < 100; i++) {
         Episode episode = algo.learnFunction(learner, new MazeState(maze, maze.getStartPosition()));
         logger.info("Finished maze after " + episode.getSteps().size() + " steps");
      }
      logger.info("Target function after learning: \n" + learner.toString());
   }
}
