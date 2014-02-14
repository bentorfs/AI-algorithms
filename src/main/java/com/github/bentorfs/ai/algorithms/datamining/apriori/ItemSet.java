package com.github.bentorfs.ai.algorithms.datamining.apriori;

import java.text.DecimalFormat;
import java.util.TreeSet;

/**
 * 
 * 
 * @author betorfs
 */
public class ItemSet {

  private TreeSet<Item> items = new TreeSet<>();

  private double support;

  public ItemSet() {

  }

  public TreeSet<Item> getItems() {
    return items;
  }

  public void setItems(TreeSet<Item> items) {
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
