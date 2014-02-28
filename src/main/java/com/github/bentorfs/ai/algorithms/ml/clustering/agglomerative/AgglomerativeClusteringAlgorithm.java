package com.github.bentorfs.ai.algorithms.ml.clustering.agglomerative;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Agglomerative clustering algorithm. Continually merges the two most similar clusters in the input list that are the
 * most similar into a bigger cluster. The input list are clusters of size 1
 * 
 * @author betorfs
 */
public class AgglomerativeClusteringAlgorithm {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   private ClusteringType type;

   /**
    * Creates a agglomerative clustering algorithm using the given clustering type: average linkage (minimizes the
    * average distance between elements), complete (minimizes the largest distance between elements), or single linkage
    * (minimizes the shortest distance between elements).
    * 
    * @param type
    *           The type of clustering to use.
    */
   public AgglomerativeClusteringAlgorithm(ClusteringType type) {
      this.type = type;
   }

   public Cluster induceClusters(Collection<Item> items) {
      logger.debug("Incuding hierarchical clusters from a dataset with {} items, using clustering type {}",
            items.size(), type.toString());

      // Create initial clusters of size 1, one for each item
      List<Cluster> currentClusters = new ArrayList<Cluster>();
      for (Item item : items) {
         currentClusters.add(item);
      }

      // Keep merging the two closest clusters, until there is only one cluster left
      while (currentClusters.size() > 1) {
         ClusterPair closestClusters = findClosestClusters(currentClusters);

         logger.trace("Closest clusters are {} and {}", closestClusters.first, closestClusters.second);

         Cluster newCluster = new SuperCluster(closestClusters.first, closestClusters.second);
         currentClusters.add(newCluster);

         currentClusters.remove(closestClusters.first);
         currentClusters.remove(closestClusters.second);
      }

      return currentClusters.get(0);
   }

   private ClusterPair findClosestClusters(List<Cluster> currentClusters) {
      Cluster firstCandidate = null;
      Cluster secondCandidate = null;
      double distanceBetweenThem = Double.MAX_VALUE;

      for (int i = 0; i < currentClusters.size(); i++) {
         Cluster clusterI = currentClusters.get(i);
         for (int j = i + 1; j < currentClusters.size(); j++) {
            Cluster clusterJ = currentClusters.get(j);
            double distance = getDistanceBetween(clusterI, clusterJ);
            if (distance <= distanceBetweenThem) {
               firstCandidate = clusterI;
               secondCandidate = clusterJ;
               distanceBetweenThem = distance;
            }
         }
      }
      return new ClusterPair(firstCandidate, secondCandidate);
   }

   private double getDistanceBetween(Cluster i, Cluster j) {
      switch (type) {
      case AVG_LINKAGE:
         return i.averageLinkageDistance(j);
      case COMPLETE_LINKAGE:
         return i.completeLinkageDistance(j);
      case SINGLE_LINKAGE:
         return i.singleLinkageDistance(j);
      }
      return Double.MAX_VALUE;
   }

   private static class ClusterPair {

      public Cluster first;

      public Cluster second;

      public ClusterPair(Cluster first, Cluster second) {
         this.first = first;
         this.second = second;
      }

   }

   public static enum ClusteringType {
      SINGLE_LINKAGE, COMPLETE_LINKAGE, AVG_LINKAGE;
   }

}
