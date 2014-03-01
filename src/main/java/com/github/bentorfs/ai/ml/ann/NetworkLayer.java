package com.github.bentorfs.ai.ml.ann;

import java.util.ArrayList;
import java.util.List;

public class NetworkLayer {

   private List<NetworkUnit> units = new ArrayList<>();

   public NetworkLayer(int sizeOfLayer, int nbOfInputs) {
      for (int i = 0; i < sizeOfLayer; i++) {
         units.add(new SigmoidUnit(nbOfInputs));
      }
   }

   public List<Double> getOutput(List<Double> inputs) {
      List<Double> outputs = new ArrayList<>();
      for (NetworkUnit unit : units) {
         if (inputs.size() != unit.getWeights().size()) {
            throw new RuntimeException(
                  "Mismatch between the number of inputs and the number of inputs to the unit in the network layer");
         }
         double unitOutput = unit.getValue(inputs);
         outputs.add(unitOutput);
      }
      return outputs;
   }

   public NetworkUnit getUnit(int i) {
      return units.get(i);
   }

   public int getNumberOfUnits() {
      return units.size();
   }

}
