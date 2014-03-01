package com.github.bentorfs.ai.ml.associationrules.apriori;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.ml.associationrules.apriori.AprioriAlgorithm;
import com.github.bentorfs.ai.ml.associationrules.apriori.AssociationRule;
import com.github.bentorfs.ai.ml.associationrules.apriori.Item;
import com.github.bentorfs.ai.ml.associationrules.apriori.ItemSet;
import com.github.bentorfs.ai.ml.associationrules.apriori.Transaction;

/**
 * 
 * 
 * @author betorfs
 */
public class AprioriAlgorithmTest {

   @Test
   public void testFrequentItemSets() {
      AprioriAlgorithm algo = new AprioriAlgorithm(100, 0.6d, 1d, 1d);

      List<Transaction> transactions = new ArrayList<>();
      Set<Item> items1 = getStringItems("M", "O", "N", "K", "E", "Y");
      transactions.add(new Transaction(items1));
      Set<Item> items2 = getStringItems("D", "O", "N", "K", "E", "Y");
      transactions.add(new Transaction(items2));
      Set<Item> items3 = getStringItems("M", "A", "K", "E");
      transactions.add(new Transaction(items3));
      Set<Item> items4 = getStringItems("M", "U", "C", "K", "Y");
      transactions.add(new Transaction(items4));
      Set<Item> items5 = getStringItems("C", "O", "O", "K", "I", "E");
      transactions.add(new Transaction(items5));

      List<ItemSet> frequentItemSets = algo.getFrequentItemSets(transactions);

      Assert.assertEquals("Wrong number of frequent itemsets returned", 11, frequentItemSets.size());
      Assert.assertEquals("Wrong number of frequent itemsets of size 1 returned", 5,
            getItemSetsOfSize(1, frequentItemSets));
      Assert.assertEquals("Wrong number of frequent itemsets of size 2 returned", 5,
            getItemSetsOfSize(2, frequentItemSets));
      Assert.assertEquals("Wrong number of frequent itemsets of size 3 returned", 1,
            getItemSetsOfSize(3, frequentItemSets));
      Assert.assertEquals("Wrong number of frequent itemsets of size 4 returned", 0,
            getItemSetsOfSize(4, frequentItemSets));

      List<AssociationRule> associationRules = algo.getAssociationRules(frequentItemSets);
      Assert.assertEquals(8, associationRules.size());
   }

   @Test
   public void testMaxSupport() {
      AprioriAlgorithm algo = new AprioriAlgorithm(100, 0.6d, 0.70d, 1d);

      List<Transaction> transactions = new ArrayList<>();
      Set<Item> items1 = getStringItems("M", "O", "N", "K", "E", "Y");
      transactions.add(new Transaction(items1));
      Set<Item> items2 = getStringItems("D", "O", "N", "K", "E", "Y");
      transactions.add(new Transaction(items2));
      Set<Item> items3 = getStringItems("M", "A", "K", "E");
      transactions.add(new Transaction(items3));
      Set<Item> items4 = getStringItems("M", "U", "C", "K", "Y");
      transactions.add(new Transaction(items4));
      Set<Item> items5 = getStringItems("C", "O", "O", "K", "I", "E");
      transactions.add(new Transaction(items5));

      List<ItemSet> frequentItemSets = algo.getFrequentItemSets(transactions);

      Assert.assertEquals("Wrong number of frequent itemsets returned", 3, frequentItemSets.size());
   }

   @Test
   public void testWithEmptyTransactions() {
      AprioriAlgorithm algo = new AprioriAlgorithm(100, 0.6d, 1d, 1d);

      List<Transaction> transactions = new ArrayList<>();

      List<ItemSet> frequentItemSets = algo.getFrequentItemSets(transactions);

      Assert.assertEquals("Wrong number of frequent itemsets returned", 0, frequentItemSets.size());

      List<AssociationRule> associationRules = algo.getAssociationRules(frequentItemSets);
      Assert.assertEquals(0, associationRules.size());

   }

   private Object getItemSetsOfSize(int i, List<ItemSet> frequentItemSets) {
      int count = 0;
      for (ItemSet is : frequentItemSets) {
         if (is.getItems().size() == i) {
            count++;
         }
      }
      return count;
   }

   private Set<Item> getStringItems(String... strings) {
      Set<Item> result = new HashSet<>();
      for (String element : strings) {
         result.add(new StringItem(element));
      }
      return result;
   }

   private static class StringItem implements Item {

      private String value;

      public StringItem(String value) {
         this.value = value;
      }

      /** {@inheritDoc} */
      @Override
      public int compareTo(Item o) {
         if (o instanceof StringItem) {
            return value.compareTo(((StringItem) o).getValue());
         } else {
            return 0;
         }
      }

      /** {@inheritDoc} */
      @Override
      public boolean isCompatibleWith(Item item) {
         return true;
      }

      public String getValue() {
         return value;
      }

      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + ((value == null) ? 0 : value.hashCode());
         return result;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         }
         if (obj == null) {
            return false;
         }
         if (getClass() != obj.getClass()) {
            return false;
         }
         StringItem other = (StringItem) obj;
         if (value == null) {
            if (other.value != null) {
               return false;
            }
         } else if (!value.equals(other.value)) {
            return false;
         }
         return true;
      }

      @Override
      public String toString() {
         return value;
      }

   }
}
