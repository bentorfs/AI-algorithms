package com.github.bentorfs.ai.ml.reinforcement.qlearning.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.State;

/**
 * This strategy will always select the next action that has the highest current Q-value. Except, there is a probability
 * (epsilon), that it will instead randomly select a different action.
 * 
 * @author betorfs
 */
public class EpsilonGreedyStrategy implements ActionSelectionStrategy {

   private double epilon;

   private Random randomGenerator = new Random();

   /**
    * 
    * @param epsilon
    *           The probability (between 0 and 1) that the agent will not not select the best action, but instead
    *           randomly take one of the other possibilities
    */
   public static EpsilonGreedyStrategy withEpsilon(double epsilon) {
      return new EpsilonGreedyStrategy(epsilon);
   }

   private EpsilonGreedyStrategy(double epsilon) {
      if (epsilon < 0 || epsilon > 1) {
         throw new RuntimeException("Epsilon should be a value between 0 and 1");
      }
      this.epilon = epsilon;
   }

   @Override
   public State getNextState(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction) {
      State result = null;
      float random = randomGenerator.nextFloat();
      if (random < epilon && possibleNextStates.size() > 1) {
         // Choose a random next state, that is not an optimal state
         List<State> statesWithHighestQValue = getStatesWithHighestQValue(currentState, possibleNextStates, qFunction);
         possibleNextStates.removeAll(statesWithHighestQValue);
         if (possibleNextStates.size() > 0) {
            int rand = randomGenerator.nextInt(possibleNextStates.size());
            result = possibleNextStates.get(rand);
         } else {
            result = getRandomOptimalState(currentState, statesWithHighestQValue, qFunction);
         }
      } else if (result == null) {
         result = getRandomOptimalState(currentState, possibleNextStates, qFunction);
      }
      return result;
   }

   private State getRandomOptimalState(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction) {
      State result;
      // Choose a random next state, that is an optimal state
      List<State> statesWithHighestQValue = getStatesWithHighestQValue(currentState, possibleNextStates, qFunction);
      int rand = randomGenerator.nextInt(statesWithHighestQValue.size());
      result = statesWithHighestQValue.get(rand);
      return result;
   }

   private List<State> getStatesWithHighestQValue(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction) {
      List<State> result = new ArrayList<>();
      double highestQValue = Double.NEGATIVE_INFINITY;
      for (State possibleNextState : possibleNextStates) {
         Double qValue = qFunction.predictValue(getAttributesList(currentState, possibleNextState));
         if (qValue > highestQValue) {
            highestQValue = qValue;
            result.clear();
            result.add(possibleNextState);
         } else if (qValue == highestQValue) {
            result.add(possibleNextState);
         }
      }
      return result;
   }

   private List<Object> getAttributesList(State currentState, State nextState) {
      List<Object> attributes = new ArrayList<>();
      attributes.add(currentState);
      attributes.add(nextState);
      return attributes;
   }
}
