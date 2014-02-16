package com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Pair;

import com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning.State;
import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * This strategy assigns a non-zero probability for every action, taking into account the relative current Q-values for
 * them. So, a more attractive action has a higher probability of being selected. The 'temperature' can be configured to
 * encourage more exploration, or more exploitation of current values.
 * 
 * @author betorfs
 */
public class BoltzmannStrategy implements ActionSelectionStrategy {

   private int temperature;

   /**
    * @param temperature
    *           Larger value for temperature increases the exploration rate, versus a lower value leads to more
    *           exploitation
    */
   public static ActionSelectionStrategy withTemperature(int temperature) {
      return new BoltzmannStrategy(temperature);
   }

   private BoltzmannStrategy(int temperature) {
      this.temperature = temperature;
   }

   @Override
   public State getNextState(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction) {
      // Boltzmann
      double qMax = getHighestQInState(qFunction, currentState);

      Map<State, Double> assignedProbabilityByAction = new HashMap<>();
      // In here we keep the total sum of these assigned probabilities
      Double totalSumOfAssignedProbabilities = 0d;
      for (State nextState : possibleNextStates) {
         Double predictedRewardForAction = qFunction.predictValue(getAttributesList(currentState, nextState));

         double thisProbability = Math.pow(FastMath.E, (predictedRewardForAction - qMax) / temperature);
         assignedProbabilityByAction.put(nextState, thisProbability);
         totalSumOfAssignedProbabilities = totalSumOfAssignedProbabilities + thisProbability;
      }

      // Now we insert in the probability distribution the effective probabilities, which are the assigned
      // probability
      // divided by the total
      List<Pair<State, Double>> pmf = new ArrayList<>();
      for (State nextState : possibleNextStates) {
         Double assignedProbability = assignedProbabilityByAction.get(nextState);
         double effectiveProbability = assignedProbability / totalSumOfAssignedProbabilities;
         pmf.add(new Pair<>(nextState, effectiveProbability));
      }
      EnumeratedDistribution<State> distribution = new EnumeratedDistribution<State>(pmf);
      State chosenState = distribution.sample();

      return chosenState;
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

   private List<Object> getAttributesList(State currentState, State nextState) {
      List<Object> attributes = new ArrayList<>();
      attributes.add(currentState);
      attributes.add(nextState);
      return attributes;
   }

}
