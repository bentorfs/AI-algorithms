package com.github.bentorfs.ai.ml.clustering.agglomerative;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cluster containing only one item
 * 
 * @author betorfs
 */
public abstract class ClusteringItem extends Cluster {

   public abstract double distanceTo(ClusteringItem other);

   /** {@inheritDoc} */
   @Override
   public List<ClusteringItem> getItems() {
      ArrayList<ClusteringItem> result = new ArrayList<>();
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
