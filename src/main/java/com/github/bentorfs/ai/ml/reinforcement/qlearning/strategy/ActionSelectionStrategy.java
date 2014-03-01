package com.github.bentorfs.ai.ml.reinforcement.qlearning.strategy;

import java.util.List;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.reinforcement.qlearning.State;

public interface ActionSelectionStrategy {

   public State getNextState(State currentState, List<State> possibleNextStates,
         FunctionLearner<Object, Double> qFunction);
}
