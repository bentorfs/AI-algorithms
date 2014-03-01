package com.github.bentorfs.ai.ml.ann.perceptron;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.ml.ann.NetworkUnit;
import com.github.bentorfs.ai.ml.ann.Perceptron;

public class PerceptronImplTest {

   @Test
   public void testPerceptronWithGivenWeights() {
      NetworkUnit perceptron = new Perceptron(0d, Arrays.asList(1d, -1d));

      Assert.assertEquals(1d, perceptron.getWeight(0), 0.000001d);
      Assert.assertEquals(-1d, perceptron.getWeight(1), 0.000001d);

      Assert.assertEquals(0d, perceptron.getValue(Arrays.asList(0d, 0d)), 0.000001d);
      Assert.assertEquals(0d, perceptron.getValue(Arrays.asList(1d, 1d)), 0.000001d);
      Assert.assertEquals(1d, perceptron.getValue(Arrays.asList(1d, 0d)), 0.000001d);
      Assert.assertEquals(2d, perceptron.getValue(Arrays.asList(1d, -1d)), 0.000001d);
   }

   @Test
   public void testPerceptronWithRandomWeights() {
      NetworkUnit perceptron = new Perceptron(3);

      Assert.assertNotNull(perceptron.getWeight(0));
      Assert.assertNotNull(perceptron.getWeight(1));
      Assert.assertNotNull(perceptron.getWeight(2));
   }

}
