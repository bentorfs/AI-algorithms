package com.github.bentorfs.ai.ml.clustering.agglomerative;

import java.util.List;

/**
 * 
 * 
 * @author betorfs
 */
public abstract class Cluster {

   public abstract List<Item> getItems();

   public abstract List<Cluster> getSubClusters();

   public double singleLinkageDistance(Cluster other) {
      double shortestDistance = Double.MAX_VALUE;

      for (Item item : getItems()) {
         for (Item otherItem : other.getItems()) {
            double distance = item.distanceTo(otherItem);
            if (distance < shortestDistance) {
               shortestDistance = distance;
            }
         }
      }

      return shortestDistance;
   }

   public double completeLinkageDistance(Cluster other) {
      double longestDistance = Double.MIN_VALUE;

      for (Item item : getItems()) {
         for (Item otherItem : other.getItems()) {
            double distance = item.distanceTo(otherItem);
            if (distance > longestDistance) {
               longestDistance = distance;
            }
         }
      }

      return longestDistance;
   }

   public double averageLinkageDistance(Cluster other) {
      double totalDistance = 0;
      int nbOfDistances = 0;

      for (Item item : getItems()) {
         for (Item otherItem : other.getItems()) {
            double distance = item.distanceTo(otherItem);
            totalDistance = totalDistance + distance;
            nbOfDistances++;
         }
      }

      return (totalDistance / nbOfDistances);
   }

   public abstract double meanInterdistance();

   @Override
   public String toString() {
      StringBuffer result = new StringBuffer();

      if (!getSubClusters().isEmpty()) {
         result.append("{\tMean interdistance: " + meanInterdistance() + "\n");
         for (Cluster subCluster : getSubClusters()) {
            String string = subCluster.toString();
            String[] subLines = string.split("\\r?\\n");
            for (String subLine : subLines) {
               result.append("\t" + subLine + "\n");
            }
         }
         result.append("}\n");
      } else {
         for (Item item : getItems()) {
            result.append("ITEM:" + item.toString() + "\n");
         }
      }

      return result.toString();
   }

}
