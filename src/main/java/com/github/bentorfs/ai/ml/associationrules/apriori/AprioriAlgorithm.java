package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author betorfs
 */
public class AprioriAlgorithm {

   protected Logger logger = LoggerFactory.getLogger(this.getClass());

   private double minSupport;

   private double maxSupport;

   private double minConfidence;

   private double maxItemSetSize;

   public AprioriAlgorithm(int maxItemSetSize, double minSup, double maxSup, double minConf) {
      this.minSupport = minSup;
      this.maxSupport = maxSup;
      this.minConfidence = minConf;
      this.maxItemSetSize = maxItemSetSize;
   }

   public List<AprioriItemSet> getFrequentItemSets(Collection<AprioriTransaction> transactions) {
      List<AprioriItemSet> frequentItemSetsOfAllSizes = new ArrayList<>();

      Set<AprioriItem> allItems = getAllItems(transactions);

      logger.debug("Generating frequent itemsets from " + transactions.size() + " transactions and " + allItems.size()
            + " different items");

      List<AprioriItemSet> candidateFrequentItemSets = getItemSetsOfSize1(allItems);

      List<AprioriItemSet> frequentItemSetsOfCurrentSize = null;
      int currentSize = 1;
      do {
         frequentItemSetsOfCurrentSize = getFrequentItemSets(candidateFrequentItemSets, transactions);

         frequentItemSetsOfAllSizes.addAll(frequentItemSetsOfCurrentSize);

         currentSize++;
         candidateFrequentItemSets = getCandidateItemSets(frequentItemSetsOfCurrentSize, currentSize);
      } while (candidateFrequentItemSets.size() > 0 && currentSize < maxItemSetSize);

      logger.debug("Done generating frequent itemsets. " + frequentItemSetsOfAllSizes.size()
            + " frequent itemsets found");
      return frequentItemSetsOfAllSizes;
   }

   /**
    * Returns a list of itemsets that are frequent in the list of transactions
    */
   private List<AprioriItemSet> getFrequentItemSets(List<AprioriItemSet> candidateItemSets, Collection<AprioriTransaction> transactions) {
      logger.debug("Selecting frequent itemsets from " + candidateItemSets.size() + " candidates");
      List<AprioriItemSet> result = new ArrayList<>();

      for (AprioriItemSet itemSet : candidateItemSets) {
         double nbOfMatchingTx = 0;
         for (AprioriTransaction t : transactions) {
            Set<AprioriItem> items = t.getItems();
            if (items.containsAll(itemSet.getItems())) {
               nbOfMatchingTx++;
            }
         }
         double supportForThisItemSet = (nbOfMatchingTx / transactions.size());
         if (supportForThisItemSet >= minSupport && supportForThisItemSet <= maxSupport) {
            itemSet.setSupport(supportForThisItemSet);
            result.add(itemSet); // Is frequent
         }
      }
      logger.debug("Found " + result.size() + " frequent itemsets");
      return result;
   }

   /**
    * Generate candidate frequent itemsets of size N, based on the frequent item sets of size N-1
    */
   private List<AprioriItemSet> getCandidateItemSets(List<AprioriItemSet> smallerFrequentItemSets, int currentSize) {
      logger.debug("Generating candidate frequent itemsets of size " + currentSize + " from itemsets of size "
            + (currentSize - 1));
      List<AprioriItemSet> candidates = new ArrayList<>();

      for (int i = 0; i < smallerFrequentItemSets.size(); i++) {
         AprioriItemSet firstSet = smallerFrequentItemSets.get(i);
         for (int j = i + 1; j < smallerFrequentItemSets.size(); j++) {
            AprioriItemSet secondSet = smallerFrequentItemSets.get(j);

            TreeSet<AprioriItem> itemsInFirstSet = firstSet.getItems();
            AprioriItem lastFromSet1 = itemsInFirstSet.pollLast();

            TreeSet<AprioriItem> itemsInSecondSet = secondSet.getItems();
            AprioriItem lastFromSet2 = itemsInSecondSet.pollLast();

            if (itemsInFirstSet.equals(itemsInSecondSet) && lastFromSet1.isCompatibleWith(lastFromSet2)) {
               AprioriItemSet candidate = new AprioriItemSet();
               candidate.getItems().addAll(itemsInFirstSet);
               candidate.getItems().add(lastFromSet1);
               candidate.getItems().add(lastFromSet2);
               candidates.add(candidate);
            }
            // Put the last items back
            itemsInFirstSet.add(lastFromSet1);
            itemsInSecondSet.add(lastFromSet2);
         }
      }
      logger.debug("Found " + candidates.size() + " candidate frequent itemsets of size " + currentSize);
      return candidates;
   }

   private List<AprioriItemSet> getItemSetsOfSize1(Set<AprioriItem> allItems) {
      List<AprioriItemSet> result = new ArrayList<AprioriItemSet>();
      for (AprioriItem item : allItems) {
         AprioriItemSet itemSetOfSize1 = new AprioriItemSet();
         itemSetOfSize1.getItems().add(item);
         result.add(itemSetOfSize1);
      }
      return result;
   }

   private Set<AprioriItem> getAllItems(Collection<AprioriTransaction> transactions) {
      Set<AprioriItem> result = new HashSet<>();
      for (AprioriTransaction t : transactions) {
         result.addAll(t.getItems());
      }
      return result;
   }

   public List<AssociationRule> getAssociationRules(List<AprioriItemSet> frequentItemSets) {
      List<AssociationRule> result = new ArrayList<>();

      for (int i = 0; i < frequentItemSets.size(); i++) {
         AprioriItemSet firstSet = frequentItemSets.get(i);
         for (int j = 0; j < frequentItemSets.size(); j++) {
            AprioriItemSet secondSet = frequentItemSets.get(j);
            if (firstSet != secondSet && firstSet.getItems().size() > secondSet.getItems().size()) {
               if (firstSet.getItems().containsAll(secondSet.getItems())) {
                  double confidence = firstSet.getSupport() / secondSet.getSupport();
                  if (confidence >= minConfidence) {
                     Set<AprioriItem> condition = new HashSet<>(secondSet.getItems());
                     Set<AprioriItem> output = new HashSet<>(firstSet.getItems());
                     output.removeAll(secondSet.getItems());
                     AssociationRule rule = new AssociationRule(condition, output, secondSet.getSupport(), confidence);
                     result.add(rule);
                  }
               }
            }
         }
      }
      return result;
   }

}
