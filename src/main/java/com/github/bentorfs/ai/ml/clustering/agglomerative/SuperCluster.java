package com.github.bentorfs.ai.ml.clustering.agglomerative;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

/**
 * 
 * 
 * @author betorfs
 */
public class SuperCluster extends Cluster {

   private List<Cluster> subClusters = new ArrayList<>();

   public SuperCluster() {

   }

   public SuperCluster(Cluster first, Cluster second) {
      subClusters.add(first);
      subClusters.add(second);
   }

   /** {@inheritDoc} */
   @Override
   public List<Item> getItems() {
      List<Item> result = new ArrayList<>();
      for (Cluster subCluster : subClusters) {
         result.addAll(subCluster.getItems());
      }
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public List<Cluster> getSubClusters() {
      return subClusters;
   }

   /** {@inheritDoc} */
   @Override
   public double meanInterdistance() {
      List<Item> items = getItems();
      Mean mean = new Mean();
      for (int i = 0; i < items.size(); i++) {
         for (int j = i + 1; j < items.size(); j++) {
            double distance = items.get(i).distanceTo(items.get(j));
            mean.increment(distance);
         }
      }
      return mean.getResult();
   }

}
