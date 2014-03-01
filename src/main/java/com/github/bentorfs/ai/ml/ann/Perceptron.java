package com.github.bentorfs.ai.ml.ann;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Simple, linear perceptron.
 * 
 * @author betorfs
 */
public class Perceptron implements NetworkUnit {

   protected List<Double> weights;

   protected Double constantInputWeight;

   /**
    * Creates a new perceptron using the given weights
    */
   public Perceptron(Double constantInputWeight, List<Double> weights) {
      this.constantInputWeight = constantInputWeight;
      this.weights = weights;
   }

   /**
    * Creates a new perceptron, initialized with random weights between 0 and 1.
    */
   public Perceptron(int nbOfInputs) {
      weights = new ArrayList<>(nbOfInputs);
      Random random = new Random();
      for (int i = 0; i < nbOfInputs; i++) {
         weights.add(i, getRandomValueBetween(random, -0.05, 0.05));
      }
      constantInputWeight = getRandomValueBetween(random, -0.05, 0.05);
   }

   /** {@inheritDoc} */
   @Override
   public double getValue(List<Double> inputs) {
      if (inputs.size() != weights.size()) {
         throw new RuntimeException("Expecting " + (weights.size() - 1) + " inputs, but received " + inputs.size() + " inputs");
      }
      double result = 0d;
      for (int i = 0; i < inputs.size(); i++) {
         result = result + (weights.get(i) * inputs.get(i));
      }
      // Add the constant input
      result = result + constantInputWeight;
      return activationFunction(result);
   }

   @Override
   public double getConstantInputWeight() {
      return constantInputWeight;
   }

   @Override
   public void setConstantInputWeight(double constantInputWeight) {
      this.constantInputWeight = constantInputWeight;
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

   protected Double activationFunction(double inputValue) {
      return inputValue; // Linear activation function
   }

   private Double getRandomValueBetween(Random random, double min, double max) {
      double positiveMax = Math.abs(max) + Math.abs(min);
      double positiveRandom = positiveMax * random.nextDouble();
      return positiveRandom - Math.abs(min);
   }

}
