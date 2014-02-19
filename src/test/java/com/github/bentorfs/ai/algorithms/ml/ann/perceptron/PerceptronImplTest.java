package com.github.bentorfs.ai.algorithms.ml.ann.perceptron;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class PerceptronImplTest {

   @Test
   public void testPerceptronWithGivenWeights() {
      Perceptron perceptron = new PerceptronImpl(0d, Arrays.asList(1d, -1d));

      Assert.assertEquals(1d, perceptron.getWeight(0), 0.000001d);
      Assert.assertEquals(-1d, perceptron.getWeight(1), 0.000001d);

      Assert.assertEquals(0d, perceptron.getRealValue(Arrays.asList(0d, 0d)), 0.000001d);
      Assert.assertEquals(0d, perceptron.getRealValue(Arrays.asList(1d, 1d)), 0.000001d);
      Assert.assertEquals(1d, perceptron.getRealValue(Arrays.asList(1d, 0d)), 0.000001d);
      Assert.assertEquals(2d, perceptron.getRealValue(Arrays.asList(1d, -1d)), 0.000001d);
   }

   @Test
   public void testPerceptronWithRandomWeights() {
      Perceptron perceptron = new PerceptronImpl(3);

      Assert.assertNotNull(perceptron.getWeight(0));
      Assert.assertNotNull(perceptron.getWeight(1));
      Assert.assertNotNull(perceptron.getWeight(2));
   }

}
