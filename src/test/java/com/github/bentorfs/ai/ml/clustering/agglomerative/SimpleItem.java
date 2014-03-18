package com.github.bentorfs.ai.ml.clustering.agglomerative;

import com.github.bentorfs.ai.ml.clustering.agglomerative.ClusteringItem;


public class SimpleItem extends ClusteringItem {

   private double value;

   public SimpleItem(double value) {
      this.value = value;
   }

   /** {@inheritDoc} */
   @Override
   public double distanceTo(ClusteringItem other) {
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