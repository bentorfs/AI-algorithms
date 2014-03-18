package com.github.bentorfs.ai.ml.associationrules.apriori;

/**
 * Item that can be used in an ItemSet for the apriori algorithm.
 * 
 * Equals and hashcode MUST be well-defined.
 * 
 * @author betorfs
 */
public interface AprioriItem extends Comparable<AprioriItem> {

   /**
    * Return if this item is compatible with an other item: if false, this item cannot be in the same itemset as the
    * other item
    */
   public boolean isCompatibleWith(AprioriItem item);

}
