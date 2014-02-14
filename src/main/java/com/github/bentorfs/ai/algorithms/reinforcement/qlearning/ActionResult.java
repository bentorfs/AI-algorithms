package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

/**
 * 
 * 
 * @author betorfs
 */
public class ActionResult {

  private double reward;

  private State resultingState;

  public ActionResult(double reward, State resultingState) {
    super();
    this.reward = reward;
    this.resultingState = resultingState;
  }

  public double getReward() {
    return reward;
  }

  public void setReward(double reward) {
    this.reward = reward;
  }

  public State getResultingState() {
    return resultingState;
  }

  public void setResultingState(State resultingState) {
    this.resultingState = resultingState;
  }

}
