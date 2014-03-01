package com.github.bentorfs.ai.search.asearch.matchingperiods;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.github.bentorfs.ai.search.asearch.ASearchAlgorithm;
import com.github.bentorfs.ai.search.asearch.AStarSearchNode;

/**
 * 
 * 
 * @author betorfs
 */
public class SearchMatchingPeriodsTest {

   @Test
   public void testNoSolution() {
      List<Period> unlinkedPeriods = new LinkedList<>();
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30)));

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(unlinkedPeriods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNull(solution);
   }

   @Test
   public void testTrivialSolution() {
      List<Period> unlinkedPeriods = new LinkedList<>();
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30)));

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(unlinkedPeriods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      PeriodMatchNode s = (PeriodMatchNode) solution;
      Assert.assertEquals(1, s.getLinkedPeriods().size());
      Assert.assertEquals("2000-01-01", s.getLinkedPeriods().get(0).getStart().toString());
      Assert.assertEquals("2000-01-30", s.getLinkedPeriods().get(0).getEnd().toString());
   }

   @Test
   public void testPartialSolution() {
      List<Period> unlinkedPeriods = new LinkedList<>();
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 12, 30)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 12, 20)));

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(unlinkedPeriods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      PeriodMatchNode s = (PeriodMatchNode) solution;
      Assert.assertEquals(1, s.getLinkedPeriods().size());
      Assert.assertEquals("2000-01-01", s.getLinkedPeriods().get(0).getStart().toString());
      Assert.assertEquals("2000-12-20", s.getLinkedPeriods().get(0).getEnd().toString());
   }

   @Test
   public void testSimpleSolution() {
      List<Period> unlinkedPeriods = new LinkedList<>();
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 15)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 15), new LocalDate(2000, 1, 30)));

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(unlinkedPeriods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      PeriodMatchNode s = (PeriodMatchNode) solution;
      Assert.assertEquals(2, s.getLinkedPeriods().size());
      Collections.sort(s.getLinkedPeriods());
      Assert.assertEquals("2000-01-01", s.getLinkedPeriods().get(0).getStart().toString());
      Assert.assertEquals("2000-01-15", s.getLinkedPeriods().get(0).getEnd().toString());
      Assert.assertEquals("2000-01-15", s.getLinkedPeriods().get(1).getStart().toString());
      Assert.assertEquals("2000-01-30", s.getLinkedPeriods().get(1).getEnd().toString());
   }

   @Test
   public void testLessSimpleSolution() {
      List<Period> unlinkedPeriods = new LinkedList<>();
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 20)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 20), new LocalDate(2000, 1, 30)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 10)));
      unlinkedPeriods.add(new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 30)));

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(unlinkedPeriods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      Assert.assertNotNull(solution);
      PeriodMatchNode s = (PeriodMatchNode) solution;
      Assert.assertEquals(3, s.getLinkedPeriods().size());
      Collections.sort(s.getLinkedPeriods());
      Assert.assertEquals("2000-01-01", s.getLinkedPeriods().get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", s.getLinkedPeriods().get(0).getEnd().toString());
      Assert.assertEquals("2000-01-10", s.getLinkedPeriods().get(1).getStart().toString());
      Assert.assertEquals("2000-01-20", s.getLinkedPeriods().get(1).getEnd().toString());
      Assert.assertEquals("2000-01-20", s.getLinkedPeriods().get(2).getStart().toString());
      Assert.assertEquals("2000-01-30", s.getLinkedPeriods().get(2).getEnd().toString());
   }

   @Test
   public void testManyPeriods() {
      List<Period> periods = new LinkedList<>();
      for (int i = 0; i < 2; i++) {
         List<Period> firstPeriods = getDivisionOfPeriod(new LocalDate(2000, 1, 1), new LocalDate(2000, 12, 31));
         List<Period> secondPeriods = getDivisionOfPeriod(new LocalDate(2000, 1, 1), new LocalDate(2000, 12, 31));
         periods.addAll(firstPeriods);
         periods.addAll(secondPeriods);
      }
      int size = periods.size();

      ASearchAlgorithm algo = new ASearchAlgorithm();

      AStarSearchNode startNode = new PeriodMatchNode(periods, new LinkedList<Period>());
      AStarSearchNode solution = algo.searchSolution(startNode);

      System.out.println("Number of input periods: " + size);
      Assert.assertNotNull(solution);
   }

   private List<Period> getDivisionOfPeriod(LocalDate start, LocalDate end) {
      List<Period> result = new LinkedList<>();
      int days = Days.daysBetween(start, end).getDays();
      if (days > 14) {
         Random r = new Random();
         int delta = r.nextInt(days);
         LocalDate intersection = start.plusDays(delta);
         result.addAll(getDivisionOfPeriod(start, intersection));
         result.addAll(getDivisionOfPeriod(intersection, end));
      } else {
         result.add(new Period(start, end));
      }
      return result;
   }

}
