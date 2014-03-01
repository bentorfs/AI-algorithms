package com.github.bentorfs.ai.search.asearch.matchingperiods;

import java.util.LinkedList;
import java.util.List;

import com.github.bentorfs.ai.common.TreeNode;
import com.github.bentorfs.ai.search.asearch.AStarSearchNode;

/**
 * 
 * 
 * @author betorfs
 */
public class PeriodMatchNode extends AStarSearchNode {

   private List<Period> unlinkedPeriods;
   private List<Period> linkedPeriods;

   public PeriodMatchNode(List<Period> unlinkedPeriods, List<Period> linkedPeriods) {
      super();
      this.unlinkedPeriods = unlinkedPeriods;
      this.linkedPeriods = linkedPeriods;
   }

   /** {@inheritDoc} */
   @Override
   public List<TreeNode> getChildNodes() {
      List<TreeNode> result = new LinkedList<>();
      for (int i = 0; i < unlinkedPeriods.size(); i++) {
         Period periodI = unlinkedPeriods.get(i);
         for (int j = i + 1; j < unlinkedPeriods.size(); j++) {
            Period periodJ = unlinkedPeriods.get(j);
            Period overlappingPeriod = periodI.getOverlappingPart(periodJ);
            if (overlappingPeriod != null) {
               List<Period> nonOverlappingParts = periodI.getNonOverlappingParts(periodJ);

               List<Period> unlinkedPeriodsForChild = new LinkedList<>(unlinkedPeriods);
               unlinkedPeriodsForChild.remove(periodI);
               unlinkedPeriodsForChild.remove(periodJ);
               unlinkedPeriodsForChild.addAll(nonOverlappingParts);

               List<Period> linkedPeriodsForChild = new LinkedList<>(linkedPeriods);
               linkedPeriodsForChild.add(overlappingPeriod);

               AStarSearchNode child = new PeriodMatchNode(unlinkedPeriodsForChild, linkedPeriodsForChild);
               result.add(child);
            }
         }
      }
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public boolean isSolutionNode() {
      // Let's say it is a solution if more than X % of days is linked
      double percentageOfLinkedDays = getPercentageOfLinkedDays();
      if (percentageOfLinkedDays > 0.95) {
         return true;
      } else {
         return false;
      }
   }

   private double getPercentageOfLinkedDays() {
      int linkedDays = 0;
      for (Period linkedPeriod : linkedPeriods) {
         linkedDays = linkedDays + linkedPeriod.getLength();
      }

      int unlinkedDays = 0;
      for (Period unlinkedPeriod : unlinkedPeriods) {
         unlinkedDays = unlinkedDays + unlinkedPeriod.getLength();
      }

      double percentageLinked = ((double) linkedDays) / (linkedDays + unlinkedDays);
      return percentageLinked;
   }

   /** {@inheritDoc} */
   @Override
   public int getCostSoFar() {
      int result = 0;
      for (Period linkedPeriod : linkedPeriods) {
         result = result + linkedPeriod.getLength();
      }
      return result; // * (linkedPeriods.size() / 365) ? Something to encourage long periods
   }

   /** {@inheritDoc} */
   @Override
   public int getEstimatedCostToSolution() {
      int result = 0;
      for (Period unlinkedPeriod : unlinkedPeriods) {
         result = result + unlinkedPeriod.getLength();
      }
      return result;
   }

   /** {@inheritDoc} */
   @Override
   public boolean isSamePosition(AStarSearchNode o) {
      return false;
   }

   public List<Period> getUnlinkedPeriods() {
      return unlinkedPeriods;
   }

   public List<Period> getLinkedPeriods() {
      return linkedPeriods;
   }

   @Override
   public String toString() {
      StringBuffer result = new StringBuffer();
      double linkedPercentage = getPercentageOfLinkedDays();
      result.append("\nPeriod matching with linked ratio: " + linkedPercentage + "\n");
      result.append("Linked Periods: (" + linkedPeriods.size() + ")\n");
      for (Period linked : linkedPeriods) {
         result.append("\t" + linked.toString() + "\n");
      }
      result.append("\n");
      result.append("Unlinked Periods: (" + unlinkedPeriods.size() + ")\n");
      for (Period unlinked : unlinkedPeriods) {
         result.append("\t" + unlinked.toString() + "\n");
      }
      result.append("\n");
      return result.toString();
   }

}
