package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * 
 * 
 * @author betorfs
 */
public class QLearningAlgorithm {

  private double discountFactor = 0.9;

  public QLearningAlgorithm(double discountFactor) {
    this.discountFactor = discountFactor;
  }

  public Episode learnFunction(FunctionLearner<Double> learnedFunction, State startState) {
    Episode recordedEpisode = new Episode();

    State currentState = startState;
    while (!currentState.isFinalState()) {
      Action action = selectNextAction();
      double currentValue = learnedFunction.predictValue(currentState, action);
      ActionResult result = currentState.performAction();
      double newValue = result.getReward() + discountFactor * currentValue;
      learnedFunction.showValue(newValue, currentState, action);

      recordedEpisode.addStep(currentState, action, result.getReward());
    }

    return recordedEpisode;
  }

  /**
   * @return
   */
  private Action selectNextAction() {
    // TODO Auto-generated method stub
    return null;
  }
}
