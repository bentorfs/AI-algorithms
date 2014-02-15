package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * 
 * 
 * @author betorfs
 */
public class QLearningAlgorithm {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  private double discountFactor = 0.9;

  public QLearningAlgorithm(double discountFactor) {
    this.discountFactor = discountFactor;
  }

  public Episode learnFunction(FunctionLearner<Double> functionLearner, State startState) {
    Episode recordedEpisode = new Episode();

    State currentState = startState;
    while (!currentState.isFinalState()) {
      State nextState = selectNextAction(currentState);
      double optimalPayoff = getOptimalPayoffInState(functionLearner, nextState);
      ActionResult result = currentState.goToState(nextState);
      nextState = result.getResultingState();
      double newValue = result.getReward() + discountFactor * optimalPayoff;
      functionLearner.showExample(newValue, currentState, nextState);

      recordedEpisode.addStep(currentState, nextState, result.getReward());
      currentState = nextState;
    }
    logger.info("Experienced episode: \n" + recordedEpisode.toString());
    return recordedEpisode;
  }

  private double getOptimalPayoffInState(FunctionLearner<Double> functionLearner, State state) {
    double result = Double.NEGATIVE_INFINITY;
    List<State> possibleNextStates = state.getPossibleNextStates();
    for (State nextState : possibleNextStates) {
      Double value = functionLearner.predictValue(state, nextState);
      if (value > result) {
        result = value;
      }
    }
    return result;
  }

  private State selectNextAction(State state) {
    List<State> possibleActions = state.getPossibleNextStates();
    // Pick a random action (TODO: Improve)
    int actionToTake = (new Random()).nextInt(possibleActions.size());
    return possibleActions.get(actionToTake);
  }

}
