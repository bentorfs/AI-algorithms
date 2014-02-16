package com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning.strategy;

import java.util.List;

import com.github.bentorfs.ai.algorithms.ml.reinforcement.qlearning.State;
import com.github.bentorfs.ai.common.FunctionLearner;

public interface ActionSelectionStrategy {

   public State getNextState(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction);
}
