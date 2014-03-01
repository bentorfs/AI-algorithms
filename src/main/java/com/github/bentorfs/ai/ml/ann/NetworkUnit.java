package com.github.bentorfs.ai.ml.ann;

import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public interface NetworkUnit {

   public double getValue(List<Double> inputs);

   public double getWeight(int index);

   public void setWeight(int index, double newWeight);

   public double getConstantInputWeight();

   public void setConstantInputWeight(double weight);

   public List<Double> getWeights();

   public void setWeights(List<Double> newWeights);

}
