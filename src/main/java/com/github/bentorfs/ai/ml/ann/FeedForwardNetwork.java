package com.github.bentorfs.ai.ml.ann;

import java.util.List;

/**
 * Represents a neural network architecture without any feedback loops
 * 
 * @author betorfs
 * 
 */
public interface FeedForwardNetwork {

   /**
    * Returns the network value for the given input values.
    */
   public List<Double> getOutput(List<Double> attributes);

   /**
    * Returns the number of layers in the network
    */
   public int getNbOfLayers();

   /**
    * Returns the internal network representation for the given input values, at the given layer
    */
   public List<Double> getOutputAtLayer(List<Double> attributes, int layer);

   /**
    * Returns the layer representation at the given index. Indices start at 1 for the first layer. Index 0 is used
    * internally to represent the input layer.
    * 
    * The NetworkLayer provides access to the network internals, such as the units, interconnections, and weights
    */
   public NetworkLayer getLayer(int index);

}
