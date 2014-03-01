package com.github.bentorfs.ai.ml.ann.perceptron;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.ann.BackPropagation;
import com.github.bentorfs.ai.ml.ann.FeedForwardNetwork;

public class BackPropagationTest {

   @Test
   public void testLearnIdentityFunctionOnTwoLayerNetwork() {
      // One input, one hidden unit, one output unit
      FeedForwardNetwork ffn = new FeedForwardNetwork(1, 1, 1);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.1);

      for (int i = 0; i < 50; i++) {
         double randomValue = Math.random();
         learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));
      }
      double randomValue = Math.random();
      learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));

      List<Double> output = ffn.getOutput(Arrays.asList(0.5));
      System.out.println(output);

      Assert.assertEquals(1, ffn.getLayer(1).getUnit(0).getWeight(0), 0.05);

   }
}
