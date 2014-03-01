package com.github.bentorfs.ai.ml.ann;

import java.util.List;

import org.apache.commons.math3.analysis.function.Sigmoid;

/**
 * Non-linear perceptron that uses the sigmoid function as activation function
 * 
 * @author betorfs
 */
public class SigmoidUnit extends Perceptron {

   private Sigmoid sigmoid = new Sigmoid();

   /**
    * Creates a new sigmoid unit using the given weights
    */
   public SigmoidUnit(Double constantInputWeight, List<Double> weights) {
      super(constantInputWeight, weights);
   }

   /**
    * Creates a new sigmoid unit, initialized with random weights between 0 and 1.
    */
   public SigmoidUnit(int nbOfInputs) {
      super(nbOfInputs);
   }

   @Override
   protected Double activationFunction(double inputValue) {
      return sigmoid.value(inputValue);
   }

}
