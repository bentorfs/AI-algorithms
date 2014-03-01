package com.github.bentorfs.ai.ml.clustering.agglomerative;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public abstract class Item extends Cluster {

   public abstract double distanceTo(Item other);

   /** {@inheritDoc} */
   @Override
   public List<Item> getItems() {
      ArrayList<Item> result = new ArrayList<>();
      result.add(this);
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public List<Cluster> getSubClusters() {
      return new ArrayList<>();
   }

   @Override
   public String toString() {
      return "Implement toString";
   }

   /** {@inheritDoc} */
   @Override
   public double meanInterdistance() {
      return 0;
   }
}
