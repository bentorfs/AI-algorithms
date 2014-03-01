package com.github.bentorfs.ai.ml.ann.perceptron;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.github.bentorfs.ai.common.FunctionLearner;
import com.github.bentorfs.ai.ml.ann.BackPropagation;
import com.github.bentorfs.ai.ml.ann.MultiLayerPerceptron;

public class BackPropagationTest {

   @Test
   public void testLearnIdentityFunctionOnTwoLayerNetworkWithOneUnitEach() {
      MultiLayerPerceptron ffn = new MultiLayerPerceptron(1, 1, 1);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.1);

      for (int i = 0; i < 5000; i++) {
         double randomValue = Math.random();
         learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));
      }
      List<Double> output = ffn.getOutput(Arrays.asList(0.5));

      Assert.assertEquals(0.5d, output.get(0), 0.1);
   }

   @Test
   public void testLearnIdentityFunctionOnTwoLayerNetworkWithMultipleUnits() {
      MultiLayerPerceptron ffn = new MultiLayerPerceptron(1, 3, 1);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.1);

      for (int i = 0; i < 5000; i++) {
         double randomValue = Math.random();
         learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));
      }
      List<Double> output = ffn.getOutput(Arrays.asList(0.5));

      Assert.assertEquals(0.5d, output.get(0), 0.1);
   }

   @Test
   public void testLearnIdentityFunctionOnThreeLayerNetworkWithOneUnitEach() {
      MultiLayerPerceptron ffn = new MultiLayerPerceptron(1, 1, 1, 1);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.1);

      for (int i = 0; i < 5000; i++) {
         double randomValue = Math.random();
         learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));
      }
      List<Double> output = ffn.getOutput(Arrays.asList(0.5));

      Assert.assertEquals(0.5d, output.get(0), 0.1);
   }

   @Test
   public void testLearnIdentityFunctionOnThreeLayerNetworkWithMultipleUnits() {
      MultiLayerPerceptron ffn = new MultiLayerPerceptron(1, 3, 2, 1);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.1);

      for (int i = 0; i < 5000; i++) {
         double randomValue = Math.random();
         learner.showExample(Arrays.asList(randomValue), Arrays.asList(randomValue));
      }
      List<Double> output = ffn.getOutput(Arrays.asList(0.5));

      Assert.assertEquals(0.5d, output.get(0), 0.1);
   }

   @Test
   @Ignore("Not stable enough without many, many iterations")
   public void testLearnInternalRepresentation() {
      // Example from Mitchell. p. 107
      MultiLayerPerceptron ffn = new MultiLayerPerceptron(8, 3, 8);
      FunctionLearner<Double, List<Double>> learner = new BackPropagation(ffn, 0.9);

      for (int i = 0; i < 50000; i++) {
         learner.showExample(Arrays.asList(1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d), Arrays.asList(1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d), Arrays.asList(0d, 1d, 0d, 0d, 0d, 0d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d), Arrays.asList(0d, 0d, 1d, 0d, 0d, 0d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d), Arrays.asList(0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d), Arrays.asList(0d, 0d, 0d, 0d, 1d, 0d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 0d, 0d, 0d, 1d, 0d, 0d), Arrays.asList(0d, 0d, 0d, 0d, 0d, 1d, 0d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 1d, 0d), Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 1d, 0d));
         learner.showExample(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d), Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d));
      }

      List<Double> output1 = ffn.getOutput(Arrays.asList(1d, 0d, 0d, 0d, 0d, 0d, 0d, 0d));
      System.out.println(output1);
      Assert.assertEquals(1d, output1.get(0), 0.5);
      Assert.assertEquals(0d, output1.get(1), 0.5);
      Assert.assertEquals(0d, output1.get(2), 0.5);
      Assert.assertEquals(0d, output1.get(3), 0.5);
      Assert.assertEquals(0d, output1.get(4), 0.5);
      Assert.assertEquals(0d, output1.get(5), 0.5);
      Assert.assertEquals(0d, output1.get(6), 0.5);
      Assert.assertEquals(0d, output1.get(7), 0.5);

      List<Double> output2 = ffn.getOutput(Arrays.asList(0d, 0d, 0d, 1d, 0d, 0d, 0d, 0d));
      System.out.println(output2);
      Assert.assertEquals(0d, output2.get(0), 0.5);
      Assert.assertEquals(0d, output2.get(1), 0.5);
      Assert.assertEquals(0d, output2.get(2), 0.5);
      Assert.assertEquals(1d, output2.get(3), 0.5);
      Assert.assertEquals(0d, output2.get(4), 0.5);
      Assert.assertEquals(0d, output2.get(5), 0.5);
      Assert.assertEquals(0d, output2.get(6), 0.5);
      Assert.assertEquals(0d, output2.get(7), 0.5);

      List<Double> output3 = ffn.getOutput(Arrays.asList(0d, 0d, 0d, 0d, 0d, 0d, 0d, 1d));
      System.out.println(output3);
      Assert.assertEquals(0d, output3.get(0), 0.5);
      Assert.assertEquals(0d, output3.get(1), 0.5);
      Assert.assertEquals(0d, output3.get(2), 0.5);
      Assert.assertEquals(0d, output3.get(3), 0.5);
      Assert.assertEquals(0d, output3.get(4), 0.5);
      Assert.assertEquals(0d, output3.get(5), 0.5);
      Assert.assertEquals(0d, output3.get(6), 0.5);
      Assert.assertEquals(1d, output3.get(7), 0.5);
   }
}
