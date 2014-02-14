package com.github.bentorfs.ai.algorithms.reinforcement.qlearning;

/**
 * 
 * 
 * @author betorfs
 */
public interface State {

  public boolean isFinalState();

  public ActionResult performAction();

}
