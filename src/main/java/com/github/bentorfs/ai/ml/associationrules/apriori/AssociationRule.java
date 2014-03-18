package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * 
 * 
 * @author betorfs
 */
public class AssociationRule {

   private Set<AprioriItem> conditions;

   private Set<AprioriItem> output;

   private double confidence;

   private double support;

   public AssociationRule(Set<AprioriItem> conditions, Set<AprioriItem> output, double support, double confidence) {
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
