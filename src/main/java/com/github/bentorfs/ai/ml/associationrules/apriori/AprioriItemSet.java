package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.text.DecimalFormat;
import java.util.TreeSet;

/**
 * 
 * 
 * @author betorfs
 */
public class AprioriItemSet {

   private TreeSet<AprioriItem> items = new TreeSet<>();

   private double support;

   public AprioriItemSet() {

   }

   public TreeSet<AprioriItem> getItems() {
      return items;
   }

   public void setItems(TreeSet<AprioriItem> items) {
      this.items = items;
   }

   public double getSupport() {
      return support;
   }

   public void setSupport(double support) {
      this.support = support;
   }

   @Override
   public String toString() {
      DecimalFormat df = new DecimalFormat("0.000");
      return df.format(support) + " Support " + items.toString();
   }

}
