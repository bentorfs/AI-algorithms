package com.github.bentorfs.ai.algorithms.ml.clustering.agglomerative;


public class SimpleItem extends Item {

   private double value;

   public SimpleItem(double value) {
      this.value = value;
   }

   /** {@inheritDoc} */
   @Override
   public double distanceTo(Item other) {
      if (other instanceof SimpleItem) {
         return Math.abs(((SimpleItem) other).getValue() - value);
      } else {
         return Double.MAX_VALUE;
      }
   }

   public double getValue() {
      return value;
   }

   public void setValue(double value) {
      this.value = value;
   }

   @Override
   public String toString() {
      return Double.toString(value);
   }

}