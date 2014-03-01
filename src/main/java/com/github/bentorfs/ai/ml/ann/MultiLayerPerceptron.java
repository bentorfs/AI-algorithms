package com.github.bentorfs.ai.ml.ann;

import java.util.LinkedList;
import java.util.List;

/**
 * A fully-interconnected feed-forward network. The number of layers, and their number of units is configurable.
 * 
 * The type of network units is fixed to Sigmoid units.
 * 
 * @author betorfs
 */
public class MultiLayerPerceptron implements FeedForwardNetwork {

   private List<NetworkLayer> layers = new LinkedList<>();

   public MultiLayerPerceptron(int nbOfInputs, int... unitsPerLayer) {
      int nbOfInputsForLayer = nbOfInputs;
      for (int i = 0; i < unitsPerLayer.length; i++) {
         layers.add(new NetworkLayer(unitsPerLayer[i], nbOfInputsForLayer));
         nbOfInputsForLayer = unitsPerLayer[i];
      }
   }

   @Override
   public List<Double> getOutput(List<Double> inputs) {
      return getOutputAtLayer(inputs, layers.size());
   }

   @Override
   public List<Double> getOutputAtLayer(List<Double> inputs, int layerNb) {
      List<Double> inputsForLayer = inputs;
      for (int i = 0; i < layerNb; i++) {
         NetworkLayer layer = layers.get(i);
         List<Double> outputsForLayer = layer.getOutput(inputsForLayer);
         inputsForLayer = outputsForLayer;
      }
      return inputsForLayer;
   }

   @Override
   public int getNbOfLayers() {
      return layers.size();
   }

   @Override
   public NetworkLayer getLayer(int i) {
      return layers.get(i - 1);
   }

}
