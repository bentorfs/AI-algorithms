package com.github.bentorfs.ai.algorithms.ml.ann;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * 
 * @author betorfs
 */
public class PerceptronImpl implements Perceptron {

   private List<Double> weights;

   private Double constantInputWeight;

   /**
    * Creates a new perceptron using the given weights
    */
   public PerceptronImpl(Double constantInputWeight, List<Double> weights) {
      this.constantInputWeight = constantInputWeight;
      this.weights = weights;
   }

   /**
    * Creates a new perceptron, initialized with random weights between 0 and 1.
    */
   public PerceptronImpl(int nbOfInputs) {
      weights = new ArrayList<>(nbOfInputs);
      Random random = new Random();
      for (int i = 0; i < nbOfInputs; i++) {
         weights.add(i, random.nextDouble());
      }
      constantInputWeight = random.nextDouble();
   }

   /** {@inheritDoc} */
   @Override
   public double getRealValue(List<Double> inputs) {
      if (inputs.size() != weights.size()) {
         throw new RuntimeException("Expecting " + (weights.size() - 1) + " inputs, but received " + inputs.size()
               + " inputs");
      }
      double result = 0d;
      for (int i = 0; i < inputs.size(); i++) {
         result = result + (weights.get(i) * inputs.get(i));
      }
      // Add the constant input
      result = result + constantInputWeight;
      return result;
   }

   @Override
   public double getConstantInputWeight() {
      return constantInputWeight;
   }

   @Override
   public void setConstantInputWeight(double constantInputWeight) {
      this.constantInputWeight = constantInputWeight;
   }

   @Override
   public int getBinaryValue(List<Double> inputs) {
      Double realValue = getRealValue(inputs);
      if (realValue > 0) {
         return 1;
      } else {
         return -1;
      }
   }

   /** {@inheritDoc} */
   @Override
   public List<Double> getWeights() {
      return new ArrayList<Double>(weights);
   }

   /** {@inheritDoc} */
   @Override
   public double getWeight(int index) {
      return weights.get(index);
   }

   @Override
   public void setWeight(int index, double newWeight) {
      weights.set(index, newWeight);
   }

   @Override
   public void setWeights(List<Double> newWeights) {
      weights = new ArrayList<>(newWeights);
   }

}
