package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author betorfs
 */
public class AprioriTransaction {

   private Set<AprioriItem> items = new HashSet<>();

   public AprioriTransaction(Set<AprioriItem> items) {
      this.items = items;
   }

   public Set<AprioriItem> getItems() {
      return items;
   }

   public void setItems(Set<AprioriItem> items) {
      this.items = items;
   }

}
