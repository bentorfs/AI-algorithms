package com.github.bentorfs.ai.algorithms.ml.associationrules.apriori;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * 
 * 
 * @author betorfs
 */
public class AssociationRule {

   private Set<Item> conditions;

   private Set<Item> output;

   private double confidence;

   private double support;

   public AssociationRule(Set<Item> conditions, Set<Item> output, double support, double confidence) {
      this.conditions = conditions;
      this.output = output;
      this.support = support;
      this.confidence = confidence;
   }

   @Override
   public String toString() {
      DecimalFormat df = new DecimalFormat("0.000");
      return df.format(support) + " Support " + df.format(confidence) + " Confidence IF " + conditions.toString()
            + " THEN " + output.toString();
   }

}
