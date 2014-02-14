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

  public void addStep(State state, Action action, double reward) {
    steps.add(new EpisodeStep(state, action, reward));
  }

  public List<EpisodeStep> getSteps() {
    return steps;
  }

  public void setSteps(List<EpisodeStep> steps) {
    this.steps = steps;
  }

  public static class EpisodeStep {

    private State state;

    private Action action;

    private double reward;

    public EpisodeStep(State state, Action action, double reward) {
      super();
      this.state = state;
      this.action = action;
      this.reward = reward;
    }

    public State getState() {
      return state;
    }

    public void setState(State state) {
      this.state = state;
    }

    public Action getAction() {
      return action;
    }

    public void setAction(Action action) {
      this.action = action;
    }

    public double getReward() {
      return reward;
    }

    public void setReward(double reward) {
      this.reward = reward;
    }

  }
}
