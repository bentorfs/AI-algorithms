package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public class Episode {

  private List<EpisodeStep> steps = new LinkedList<>();

  public Episode() {

  }

  public void addStep(State state, State nextState, double reward) {
    steps.add(new EpisodeStep(state, nextState, reward));
  }

  public List<EpisodeStep> getSteps() {
    return steps;
  }

  public void setSteps(List<EpisodeStep> steps) {
    this.steps = steps;
  }

  public static class EpisodeStep {

    private State state;

    private State nextState;

    private double reward;

    public EpisodeStep(State state, State nextState, double reward) {
      super();
      this.state = state;
      this.nextState = nextState;
      this.reward = reward;
    }

    public State getState() {
      return state;
    }

    public void setState(State state) {
      this.state = state;
    }

    public double getReward() {
      return reward;
    }

    public void setReward(double reward) {
      this.reward = reward;
    }

    public State getNextState() {
      return nextState;
    }

    public void setNextState(State nextState) {
      this.nextState = nextState;
    }

  }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    for (EpisodeStep step : steps) {
      result.append("Earned reward: " + step.getReward() + ", by doing: " + step.getNextState());
    }
    return result.toString();
  }
}
