package com.github.bentorfs.ai.ml.ann;

import java.util.ArrayList;
import java.util.List;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * Learns the weights for a multi-layer feed-forward network
 * 
 * @author betorfs
 */
public class BackPropagation implements FunctionLearner<Double, List<Double>> {

   private double learningRate = 0.1;
   private FeedForwardNetwork feedForwardNetwork;

   public BackPropagation(FeedForwardNetwork feedForwardNetwork, double learningRate) {
      super();
      this.feedForwardNetwork = feedForwardNetwork;
      this.learningRate = learningRate;
   }

   /** {@inheritDoc} */
   @Override
   public List<Double> predictValue(List<Double> attributes) {
      return feedForwardNetwork.getOutput(attributes);
   }

   /** {@inheritDoc} */
   @Override
   public void showExample(List<Double> targetValues, List<Double> inputAttributes) {
      // Calculate the outputs of each layer separately and store. The outputs at layer 0 are the example input
      List<List<Double>> outputsAtEachLayer = new ArrayList<>(feedForwardNetwork.getNbOfLayers() + 1);
      outputsAtEachLayer.add(inputAttributes);
      for (int i = 1; i <= feedForwardNetwork.getNbOfLayers(); i++) {
         outputsAtEachLayer.add(feedForwardNetwork.getOutputAtLayer(inputAttributes, i));
      }

      // This list will contain the error term at each output in each layer
      List<List<Double>> errorTerms = new ArrayList<>(feedForwardNetwork.getNbOfLayers());
      // Initialize the list with null values
      for (int i = 0; i < feedForwardNetwork.getNbOfLayers() + 1; i++) {
         errorTerms.add(null);
      }

      // Calculate the error terms for the output layer
      List<Double> outputsAtCurrentLayer = outputsAtEachLayer.get(outputsAtEachLayer.size() - 1);
      if (outputsAtCurrentLayer.size() != targetValues.size()) {
         throw new RuntimeException(
               "Mismatch between the number of outputs of the feedforward network and the training example");
      }
      List<Double> errorTermsForThisLayer = new ArrayList<>();
      for (int j = 0; j < outputsAtCurrentLayer.size(); j++) {
         Double currentOutput = outputsAtCurrentLayer.get(j);
         Double targetOutput = targetValues.get(j);
         double errorTerm = currentOutput * (1 - currentOutput) * (targetOutput - currentOutput);
         errorTermsForThisLayer.add(errorTerm);
      }
      errorTerms.set(feedForwardNetwork.getNbOfLayers(), errorTermsForThisLayer);

      // Backpropagate the error terms to the hidden layers
      for (int i = outputsAtEachLayer.size() - 1; i > 1; i--) {
         outputsAtCurrentLayer = outputsAtEachLayer.get(i);
         errorTermsForThisLayer = new ArrayList<>();
         for (int j = 0; j < outputsAtCurrentLayer.size(); j++) {
            Double currentOutput = outputsAtCurrentLayer.get(j);
            Double targetOutput = targetValues.get(j);

            NetworkLayer currentLayer = feedForwardNetwork.getLayer(i);
            double sumOfOutputs = 0;
            for (int k = 0; k < currentLayer.getNumberOfUnits(); k++) {
               double weightOfOutput = currentLayer.getUnit(k).getWeight(j);
               double errorTermOfOutput = errorTerms.get(i).get(k);
               sumOfOutputs = sumOfOutputs + (weightOfOutput * errorTermOfOutput);
            }

            double errorTerm = currentOutput * (1 - currentOutput) * (targetOutput - currentOutput);
            errorTermsForThisLayer.add(errorTerm);
         }
         errorTerms.set(i - 1, errorTermsForThisLayer);

      }

      // Adjust the weights in each layer
      for (int i = 1; i < outputsAtEachLayer.size(); i++) {
         List<Double> inputsAtLayer = outputsAtEachLayer.get(i - 1);
         NetworkLayer currentLayer = feedForwardNetwork.getLayer(i);
         for (int j = 0; j < currentLayer.getNumberOfUnits(); j++) {
            Perceptron currentUnit = currentLayer.getUnit(j);

            for (int k = 0; k < inputsAtLayer.size(); k++) {
               double currentWeight = currentUnit.getWeight(k);

               double newWeight = currentWeight + (learningRate * inputsAtLayer.get(k) * errorTerms.get(i).get(j));
               currentUnit.setWeight(k, newWeight);
            }
         }
         inputsAtLayer = feedForwardNetwork.getOutputAtLayer(inputAttributes, i);
      }
   }
}
