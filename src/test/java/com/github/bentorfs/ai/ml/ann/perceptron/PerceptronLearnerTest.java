package com.github.bentorfs.ai.ml.ann.perceptron;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.ann.NetworkUnit;
import com.github.bentorfs.ai.ml.ann.Perceptron;
import com.github.bentorfs.ai.ml.ann.PerceptronLearner;
import com.github.bentorfs.ai.ml.ann.PerceptronLearner.PerceptronTrainingStrategy;

public class PerceptronLearnerTest {

   public static double delta = 0.999999999;

   @Test
   public void testLearnORFunctionUsingPerceptronRule() {
      NetworkUnit perceptron = new Perceptron(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.perceptron_rule, 0.1);

      for (int i = 0; i < 1000; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(1d, Arrays.asList(1d, -1d));
         learner.showExample(1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, 1d)), delta);
      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, -1d)), delta);
      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(-1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, -1d)), delta);
   }

   @Test
   public void testLearnANDFunctionUsingPerceptronRule() {
      NetworkUnit perceptron = new Perceptron(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.perceptron_rule, 0.1);

      for (int i = 0; i < 50; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(-1d, Arrays.asList(1d, -1d));
         learner.showExample(-1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(1d, -1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, -1d)), delta);
   }

   @Test
   public void testLearnORFunctionUsingDeltaRule() {
      NetworkUnit perceptron = new Perceptron(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.delta_rule, 0.1);

      for (int i = 0; i < 100; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(1d, Arrays.asList(1d, -1d));
         learner.showExample(1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, 1d)), delta);
      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, -1d)), delta);
      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(-1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, -1d)), delta);
   }

   @Test
   public void testLearnANDFunctionUsingDeltaRule() {
      NetworkUnit perceptron = new Perceptron(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.delta_rule, 0.1);

      for (int i = 0; i < 50; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(-1d, Arrays.asList(1d, -1d));
         learner.showExample(-1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getValue(Arrays.asList(1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(1d, -1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, 1d)), delta);
      Assert.assertEquals(-1, perceptron.getValue(Arrays.asList(-1d, -1d)), delta);
   }
}
