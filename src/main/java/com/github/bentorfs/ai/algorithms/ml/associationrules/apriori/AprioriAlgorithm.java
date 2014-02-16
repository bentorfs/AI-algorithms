package com.github.bentorfs.ai.algorithms.ml.associationrules.apriori;

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

   public List<ItemSet> getFrequentItemSets(Collection<Transaction> transactions) {
      List<ItemSet> frequentItemSetsOfAllSizes = new ArrayList<>();

      Set<Item> allItems = getAllItems(transactions);

      logger.info("Generating frequent itemsets from " + transactions.size() + " transactions and " + allItems.size()
            + " different items");

      List<ItemSet> candidateFrequentItemSets = getItemSetsOfSize1(allItems);

      List<ItemSet> frequentItemSetsOfCurrentSize = null;
      int currentSize = 1;
      do {
         frequentItemSetsOfCurrentSize = getFrequentItemSets(candidateFrequentItemSets, transactions);

         frequentItemSetsOfAllSizes.addAll(frequentItemSetsOfCurrentSize);

         currentSize++;
         candidateFrequentItemSets = getCandidateItemSets(frequentItemSetsOfCurrentSize, currentSize);
      } while (candidateFrequentItemSets.size() > 0 && currentSize < maxItemSetSize);

      logger.info("Done generating frequent itemsets. " + frequentItemSetsOfAllSizes.size()
            + " frequent itemsets found");
      return frequentItemSetsOfAllSizes;
   }

   /**
    * Returns a list of itemsets that are frequent in the list of transactions
    */
   private List<ItemSet> getFrequentItemSets(List<ItemSet> candidateItemSets, Collection<Transaction> transactions) {
      logger.info("Selecting frequent itemsets from " + candidateItemSets.size() + " candidates");
      List<ItemSet> result = new ArrayList<>();

      for (ItemSet itemSet : candidateItemSets) {
         double nbOfMatchingTx = 0;
         for (Transaction t : transactions) {
            Set<Item> items = t.getItems();
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
      logger.info("Found " + result.size() + " frequent itemsets");
      return result;
   }

   /**
    * Generate candidate frequent itemsets of size N, based on the frequent item sets of size N-1
    */
   private List<ItemSet> getCandidateItemSets(List<ItemSet> smallerFrequentItemSets, int currentSize) {
      logger.info("Generating candidate frequent itemsets of size " + currentSize + " from itemsets of size "
            + (currentSize - 1));
      List<ItemSet> candidates = new ArrayList<>();

      for (int i = 0; i < smallerFrequentItemSets.size(); i++) {
         ItemSet firstSet = smallerFrequentItemSets.get(i);
         for (int j = i + 1; j < smallerFrequentItemSets.size(); j++) {
            ItemSet secondSet = smallerFrequentItemSets.get(j);

            TreeSet<Item> itemsInFirstSet = firstSet.getItems();
            Item lastFromSet1 = itemsInFirstSet.pollLast();

            TreeSet<Item> itemsInSecondSet = secondSet.getItems();
            Item lastFromSet2 = itemsInSecondSet.pollLast();

            if (itemsInFirstSet.equals(itemsInSecondSet) && lastFromSet1.isCompatibleWith(lastFromSet2)) {
               ItemSet candidate = new ItemSet();
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
      logger.info("Found " + candidates.size() + " candidate frequent itemsets of size " + currentSize);
      return candidates;
   }

   private List<ItemSet> getItemSetsOfSize1(Set<Item> allItems) {
      List<ItemSet> result = new ArrayList<ItemSet>();
      for (Item item : allItems) {
         ItemSet itemSetOfSize1 = new ItemSet();
         itemSetOfSize1.getItems().add(item);
         result.add(itemSetOfSize1);
      }
      return result;
   }

   private Set<Item> getAllItems(Collection<Transaction> transactions) {
      Set<Item> result = new HashSet<>();
      for (Transaction t : transactions) {
         result.addAll(t.getItems());
      }
      return result;
   }

   public List<AssociationRule> getAssociationRules(List<ItemSet> frequentItemSets) {
      List<AssociationRule> result = new ArrayList<>();

      for (int i = 0; i < frequentItemSets.size(); i++) {
         ItemSet firstSet = frequentItemSets.get(i);
         for (int j = 0; j < frequentItemSets.size(); j++) {
            ItemSet secondSet = frequentItemSets.get(j);
            if (firstSet != secondSet && firstSet.getItems().size() > secondSet.getItems().size()) {
               if (firstSet.getItems().containsAll(secondSet.getItems())) {
                  double confidence = firstSet.getSupport() / secondSet.getSupport();
                  if (confidence >= minConfidence) {
                     Set<Item> condition = new HashSet<>(secondSet.getItems());
                     Set<Item> output = new HashSet<>(firstSet.getItems());
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
