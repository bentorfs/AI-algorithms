package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author betorfs
 */
public class Transaction {

   private Set<Item> items = new HashSet<>();

   public Transaction(Set<Item> items) {
      this.items = items;
   }

   public Set<Item> getItems() {
      return items;
   }

   public void setItems(Set<Item> items) {
      this.items = items;
   }

}
