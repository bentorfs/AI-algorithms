package com.github.bentorfs.ai.ml.clustering.agglomerative;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.ml.clustering.agglomerative.AgglomerativeClusteringAlgorithm;
import com.github.bentorfs.ai.ml.clustering.agglomerative.Cluster;
import com.github.bentorfs.ai.ml.clustering.agglomerative.ClusteringItem;
import com.github.bentorfs.ai.ml.clustering.agglomerative.AgglomerativeClusteringAlgorithm.ClusteringType;

/**
 * 
 * 
 * @author betorfs
 */
public class AgglomerativeClusteringAlgorithmTest {

   @Test
   public void testClusteringOfOneElement() {
      ClusteringItem item1 = new SimpleItem(1d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.SINGLE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1));

      Assert.assertEquals(1, superCluster.getItems().size());
      Assert.assertEquals(0, superCluster.getSubClusters().size());
   }

   @Test
   public void testSingleLinkClustering() {
      ClusteringItem item1 = new SimpleItem(0d);
      ClusteringItem item2 = new SimpleItem(3d);
      ClusteringItem item3 = new SimpleItem(100d);
      ClusteringItem item4 = new SimpleItem(103d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.SINGLE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());

      System.out.println(superCluster);
   }

   @Test
   public void testCompleteLinkClustering() {
      ClusteringItem item1 = new SimpleItem(0d);
      ClusteringItem item2 = new SimpleItem(1d);
      ClusteringItem item3 = new SimpleItem(100d);
      ClusteringItem item4 = new SimpleItem(101d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.COMPLETE_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());
   }

   @Test
   public void testAvgLinkClustering() {
      ClusteringItem item1 = new SimpleItem(0d);
      ClusteringItem item2 = new SimpleItem(1d);
      ClusteringItem item3 = new SimpleItem(100d);
      ClusteringItem item4 = new SimpleItem(101d);

      AgglomerativeClusteringAlgorithm algo = new AgglomerativeClusteringAlgorithm(ClusteringType.AVG_LINKAGE);
      Cluster superCluster = algo.induceClusters(Arrays.asList(item1, item2, item3, item4));

      Assert.assertEquals(4, superCluster.getItems().size());
      Assert.assertEquals(2, superCluster.getSubClusters().size());
   }
}
