package com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning.strategy.ActionSelectionStrategy;
import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * 
 * 
 * @author betorfs
 */
public class QLearningAlgorithm {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   private double discountFactor = 0.9;

   private ActionSelectionStrategy actionSelectionStrategy;

   public QLearningAlgorithm(double discountFactor, ActionSelectionStrategy actionSelectionStrategy) {
      this.discountFactor = discountFactor;
      this.actionSelectionStrategy = actionSelectionStrategy;
   }

   public Episode learnFunction(FunctionLearner<Object, Double> functionLearner, State startState) {
      Episode recordedEpisode = new Episode();

      State currentState = startState;
      while (!currentState.isFinalState()) {
         State nextState = selectNextState(currentState, functionLearner);
         ActionResult result = currentState.goToState(nextState);
         double highestQInState = getHighestQInState(functionLearner, nextState);
         double newQValue = result.getReward() + discountFactor * highestQInState;

         List<Object> attributes = getAttributesList(currentState, nextState);
         functionLearner.showExample(newQValue, attributes);

         recordedEpisode.addStep(currentState, nextState, result.getReward());
         currentState = nextState;
      }
      logger.debug("Q-Learning experienced an episode");
      return recordedEpisode;
   }

   private double getHighestQInState(FunctionLearner<Object, Double> functionLearner, State state) {
      double result = Double.NEGATIVE_INFINITY;
      List<State> possibleNextStates = state.getPossibleNextStates();
      for (State nextState : possibleNextStates) {
         Double value = functionLearner.predictValue(getAttributesList(state, nextState));
         if (value > result) {
            result = value;
         }
      }
      return result;
   }

   private State selectNextState(State state, FunctionLearner<Object, Double> functionLearner) {
      List<State> possibleNextStates = state.getPossibleNextStates();
      return actionSelectionStrategy.getNextState(state, possibleNextStates, functionLearner);
   }

   private List<Object> getAttributesList(State currentState, State nextState) {
      List<Object> attributes = new ArrayList<>();
      attributes.add(currentState);
      attributes.add(nextState);
      return attributes;
   }

   public static enum ActionStrategy {
      greedy, boltzmann
   }
}
