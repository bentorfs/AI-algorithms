package com.github.bentorfs.ai.algorithms.ml.ann.perceptron;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.ml.ann.Perceptron;
import com.github.bentorfs.ai.algorithms.ml.ann.PerceptronImpl;
import com.github.bentorfs.ai.algorithms.ml.ann.PerceptronLearner;
import com.github.bentorfs.ai.algorithms.ml.ann.PerceptronLearner.PerceptronTrainingStrategy;
import com.github.bentorfs.ai.common.FunctionLearner;

public class PerceptronLearnerTest {

   @Test
   public void testLearnORFunctionUsingPerceptronRule() {
      Perceptron perceptron = new PerceptronImpl(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.perceptron_rule);

      for (int i = 0; i < 10; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(1d, Arrays.asList(1d, -1d));
         learner.showExample(1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, 1d)));
      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, -1d)));
      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(-1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, -1d)));
   }

   @Test
   public void testLearnANDFunctionUsingPerceptronRule() {
      Perceptron perceptron = new PerceptronImpl(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron,
            PerceptronTrainingStrategy.perceptron_rule);

      for (int i = 0; i < 50; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(-1d, Arrays.asList(1d, -1d));
         learner.showExample(-1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(1d, -1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, -1d)));
   }

   @Test
   public void testLearnORFunctionUsingDeltaRule() {
      Perceptron perceptron = new PerceptronImpl(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron, PerceptronTrainingStrategy.delta_rule);

      for (int i = 0; i < 10; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(1d, Arrays.asList(1d, -1d));
         learner.showExample(1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, 1d)));
      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, -1d)));
      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(-1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, -1d)));
   }

   @Test
   public void testLearnANDFunctionUsingDeltaRule() {
      Perceptron perceptron = new PerceptronImpl(2);
      FunctionLearner<Double, Double> learner = new PerceptronLearner(perceptron, PerceptronTrainingStrategy.delta_rule);

      for (int i = 0; i < 50; i++) {
         learner.showExample(1d, Arrays.asList(1d, 1d));
         learner.showExample(-1d, Arrays.asList(1d, -1d));
         learner.showExample(-1d, Arrays.asList(-1d, 1d));
         learner.showExample(-1d, Arrays.asList(-1d, -1d));
      }

      Assert.assertEquals(1, perceptron.getBinaryValue(Arrays.asList(1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(1d, -1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, 1d)));
      Assert.assertEquals(-1, perceptron.getBinaryValue(Arrays.asList(-1d, -1d)));
   }
}
