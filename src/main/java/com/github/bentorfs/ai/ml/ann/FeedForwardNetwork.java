package com.github.bentorfs.ai.ml.ann;

import java.util.LinkedList;
import java.util.List;

/**
 * Actually just a multi-layer perceptron, as it is fully interconnected.
 * 
 * TODO: Support non-fully interconnected feedforward networks
 * 
 * @author betorfs
 * 
 */
public class FeedForwardNetwork {

   private List<NetworkLayer> layers = new LinkedList<>();

   public FeedForwardNetwork(int nbOfInputs, int... unitsPerLayer) {
      int nbOfInputsForLayer = nbOfInputs;
      for (int i = 0; i < unitsPerLayer.length; i++) {
         layers.add(new NetworkLayer(unitsPerLayer[i], nbOfInputsForLayer));
         nbOfInputsForLayer = unitsPerLayer[i];
      }
   }

   public List<Double> getOutput(List<Double> inputs) {
      return getOutputAtLayer(inputs, layers.size());
   }

   public List<Double> getOutputAtLayer(List<Double> inputs, int layerNb) {
      List<Double> inputsForLayer = inputs;
      for (int i = 0; i < layerNb; i++) {
         NetworkLayer layer = layers.get(i);
         List<Double> outputsForLayer = layer.getOutput(inputsForLayer);
         inputsForLayer = outputsForLayer;
      }
      return inputsForLayer;
   }

   public int getNbOfLayers() {
      return layers.size();
   }

   public NetworkLayer getLayer(int i) {
      return layers.get(i - 1);
   }

}
