package com.github.bentorfs.ai.ml.ann;

import java.util.ArrayList;
import java.util.List;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * Learns the weights for a multi-layer feed-forward network.
 * 
 * Currently assumes a fully interconnected network.
 * 
 * TODO: Support all feedforward networks
 * 
 * @author betorfs
 */
public class BackPropagation implements FunctionLearner<Double, List<Double>> {

   private double learningRate = 0.1;
   private FeedForwardNetwork feedForwardNetwork;

   public BackPropagation(MultiLayerPerceptron feedForwardNetwork, double learningRate) {
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

      // This list will contain the error term for each layer, in each unit.
      List<List<Double>> errorTerms = getErrorTerms(targetValues, outputsAtEachLayer);

      // Adjust the weights in each layer
      for (int i = 1; i < outputsAtEachLayer.size(); i++) {
         List<Double> inputsAtLayer = outputsAtEachLayer.get(i - 1);
         NetworkLayer currentLayer = feedForwardNetwork.getLayer(i);
         for (int j = 0; j < currentLayer.getNumberOfUnits(); j++) {
            NetworkUnit currentUnit = currentLayer.getUnit(j);

            for (int k = 0; k < inputsAtLayer.size(); k++) {
               double currentWeight = currentUnit.getWeight(k);

               double newWeight = currentWeight + (learningRate * inputsAtLayer.get(k) * errorTerms.get(i).get(j));
               currentUnit.setWeight(k, newWeight);
            }
         }
         inputsAtLayer = feedForwardNetwork.getOutputAtLayer(inputAttributes, i);
      }
   }

   private List<List<Double>> getErrorTerms(List<Double> targetValues, List<List<Double>> outputsAtEachLayer) {
      // Create the list that will contain the error terms for each layer. Add 1 to the size that represents the input
      // layer (it makes cross-indexing with the network easier).
      List<List<Double>> errorTerms = getEmptyListWithSize(feedForwardNetwork.getNbOfLayers() + 1);

      // Calculate the error terms for the output units at the output layer
      List<Double> outputLayerErrorTerms = getOutputLayerErrorTerms(targetValues, outputsAtEachLayer);
      errorTerms.set(feedForwardNetwork.getNbOfLayers(), outputLayerErrorTerms);

      // Backpropagate the error terms to the hidden layers
      for (int i = outputsAtEachLayer.size() - 2; i > 0; i--) {
         List<Double> layerOutputs = outputsAtEachLayer.get(i);
         List<Double> layerErrorTerms = new ArrayList<>();
         for (int j = 0; j < layerOutputs.size(); j++) {
            Double currentOutput = layerOutputs.get(j);

            NetworkLayer nextLayer = feedForwardNetwork.getLayer(i + 1);
            double sumOfOutputs = 0;
            for (int k = 0; k < nextLayer.getNumberOfUnits(); k++) {
               double weightOfOutput = nextLayer.getUnit(k).getWeight(j);
               double errorTermOfOutput = errorTerms.get(i + 1).get(k);
               sumOfOutputs = sumOfOutputs + (weightOfOutput * errorTermOfOutput);
            }

            double errorTerm = currentOutput * (1 - currentOutput) * sumOfOutputs;
            layerErrorTerms.add(errorTerm);
         }
         errorTerms.set(i, layerErrorTerms);
      }
      return errorTerms;
   }

   private List<Double> getOutputLayerErrorTerms(List<Double> targetValues, List<List<Double>> outputsAtEachLayer) {
      List<Double> outputsAtOutputLayer = outputsAtEachLayer.get(outputsAtEachLayer.size() - 1);
      if (outputsAtOutputLayer.size() != targetValues.size()) {
         throw new RuntimeException("Mismatch between the number of outputs of the feedforward network and the training example");
      }
      List<Double> outputLayerErrorTerms = new ArrayList<>();
      for (int j = 0; j < outputsAtOutputLayer.size(); j++) {
         Double currentOutput = outputsAtOutputLayer.get(j);
         Double targetOutput = targetValues.get(j);
         double errorTerm = currentOutput * (1 - currentOutput) * (targetOutput - currentOutput);
         outputLayerErrorTerms.add(errorTerm);
      }
      return outputLayerErrorTerms;
   }

   private List<List<Double>> getEmptyListWithSize(int size) {
      List<List<Double>> errorTerms = new ArrayList<>(size);
      // Initialize the list with null values
      for (int i = 0; i < size; i++) {
         errorTerms.add(null);
      }
      return errorTerms;
   }
}
