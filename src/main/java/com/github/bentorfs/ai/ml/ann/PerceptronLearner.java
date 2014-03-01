package com.github.bentorfs.ai.ml.ann;

import java.util.List;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * Learns the weights for a perceptron, based on either the perceptron rule or the delta rule (stochastic gradient
 * descent) (Cfr Mitchell p.92-95).
 * 
 * @author betorfs
 */
public class PerceptronLearner implements FunctionLearner<Double, Double> {

   private double learningRate = 0.1;
   private Perceptron perceptron;
   private PerceptronTrainingStrategy strategy = PerceptronTrainingStrategy.delta_rule;

   /**
    * Instantiates a perceptron learner using the default values for the learning rate (0.1) and strategy (delta rule)
    */
   public PerceptronLearner(Perceptron perceptron) {
      super();
      this.perceptron = perceptron;
   }

   /**
    * Instantiates a perceptron learning using the given values for the learning rate and strategy
    */
   public PerceptronLearner(Perceptron perceptron, PerceptronTrainingStrategy strategy, double learningRate) {
      super();
      this.perceptron = perceptron;
      this.strategy = strategy;
      this.learningRate = learningRate;
   }

   /** {@inheritDoc} */
   @Override
   public Double predictValue(List<Double> attributes) {
      return perceptron.getRealValue(attributes);
   }

   /** {@inheritDoc} */
   @Override
   public void showExample(Double targetValue, List<Double> attributes) {
      double currentValue;
      if (PerceptronTrainingStrategy.perceptron_rule.equals(strategy)) {
         currentValue = perceptron.getBinaryValue(attributes);
      } else {
         currentValue = perceptron.getRealValue(attributes);
      }

      List<Double> weights = perceptron.getWeights();
      for (int i = 0; i < weights.size(); i++) {
         double delta = learningRate * (targetValue - currentValue) * attributes.get(i);
         double currentWeight = perceptron.getWeight(i);
         perceptron.setWeight(i, currentWeight + delta);
      }
      // Update constant weight
      double delta = learningRate * (targetValue - currentValue);
      double currentWeight = perceptron.getConstantInputWeight();
      perceptron.setConstantInputWeight(currentWeight + delta);
   }

   public static enum PerceptronTrainingStrategy {
      perceptron_rule, delta_rule
   }

}
