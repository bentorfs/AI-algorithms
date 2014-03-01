package com.github.bentorfs.ai.ml.ann.perceptron;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.ml.ann.FeedForwardNetwork;

public class FeedForwardNetworkTest {

   @Test
   public void testGetOutputOfSimpleNetwork() {
      // One input, one hidden unit, one output unit
      FeedForwardNetwork ffn = new FeedForwardNetwork(1, 1, 1);

      List<Double> outputs = ffn.getOutput(Arrays.asList(1d));

      Assert.assertEquals(1, outputs.size());
   }

   @Test
   public void testGetOutputOfComplexNetwork() {
      // One input, one hidden unit, one output unit
      FeedForwardNetwork ffn = new FeedForwardNetwork(7, 5, 9, 3);

      List<Double> outputs = ffn.getOutput(Arrays.asList(1d, 2d, 3d, 4d, 5d, 6d, 7d));

      Assert.assertEquals(3, outputs.size());
   }
}
