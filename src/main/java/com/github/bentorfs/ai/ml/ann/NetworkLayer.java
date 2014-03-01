package com.github.bentorfs.ai.ml.ann;

import java.util.ArrayList;
import java.util.List;

public class NetworkLayer {

   private List<Perceptron> units = new ArrayList<>();

   public NetworkLayer(int sizeOfLayer, int nbOfInputs) {
      for (int i = 0; i < sizeOfLayer; i++) {
         units.add(new PerceptronImpl(nbOfInputs));
      }
   }

   public List<Double> getOutput(List<Double> inputs) {
      List<Double> outputs = new ArrayList<>();
      for (Perceptron unit : units) {
         if (inputs.size() != unit.getWeights().size()) {
            throw new RuntimeException(
                  "Mismatch between the number of inputs and the number of inputs to the unit in the network layer");
         }
         double unitOutput = unit.getRealValue(inputs);
         outputs.add(unitOutput);
      }
      return outputs;
   }

   public Perceptron getUnit(int i) {
      return units.get(i);
   }

   public int getNumberOfUnits() {
      return units.size();
   }

}
