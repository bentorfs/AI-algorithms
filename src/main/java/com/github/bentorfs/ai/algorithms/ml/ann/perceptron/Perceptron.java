package com.github.bentorfs.ai.algorithms.ml.ann.perceptron;

import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public interface Perceptron {

   public double getRealValue(List<Double> inputs);

   public int getBinaryValue(List<Double> inputs);

   public double getWeight(int index);

   public void setWeight(int index, double newWeight);

   public double getConstantInputWeight();

   public void setConstantInputWeight(double weight);

   public List<Double> getWeights();

   public void setWeights(List<Double> newWeights);

}
