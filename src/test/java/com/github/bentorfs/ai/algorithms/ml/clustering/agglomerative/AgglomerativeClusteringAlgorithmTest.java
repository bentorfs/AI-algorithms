package com.github.bentorfs.ai.algorithms.ml.clustering.agglomerative;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.algorithms.ml.clustering.agglomerative.AgglomerativeClusteringAlgorithm.ClusteringType;

/**
 * 
 * 
 * @author betorfs
 */
public class AgglomerativeClusteringAlgorithmTest {

   @Test
   public void testClusteringOfOneElement() {
      Item item1 = new SimpleItem(1d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.SINGLE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1));

      Assert.assertEquals(1, superCluster.getItems().size());
      Assert.assertEquals(0, superCluster.getSubClusters().size());
   }

   @Test
   public void testSingleLinkClustering() {
      Item item1 = new SimpleItem(0d);
      Item item2 = new SimpleItem(3d);
      Item item3 = new SimpleItem(100d);
      Item item4 = new SimpleItem(103d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.SINGLE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());

      System.out.println(superCluster);
   }

   @Test
   public void testCompleteLinkClustering() {
      Item item1 = new SimpleItem(0d);
      Item item2 = new SimpleItem(1d);
      Item item3 = new SimpleItem(100d);
      Item item4 = new SimpleItem(101d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.COMPLETE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());
   }

   @Test
   public void testAvgLinkClustering() {
      Item item1 = new SimpleItem(0d);
      Item item2 = new SimpleItem(1d);
      Item item3 = new SimpleItem(100d);
      Item item4 = new SimpleItem(101d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.AVG_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());
   }
}
