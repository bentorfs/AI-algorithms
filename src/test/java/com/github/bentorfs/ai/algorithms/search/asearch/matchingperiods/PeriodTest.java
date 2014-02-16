package com.github.bentorfs.ai.algorithms.search.asearch.matchingperiods;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * 
 * @author betorfs
 */
public class PeriodTest {

   @Test
   public void testOverlappingPartsExactMatch() {
      Period p1 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));
      Period p2 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(0, nonOverlappingParts.size());
   }

   @Test
   public void testOverlappingPartsLeftOverlap() {
      Period p1 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));
      Period p2 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 15));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-15", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-01", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-15", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsLeftOverlapReverse() {
      Period p1 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 15));
      Period p2 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-15", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-01", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-15", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsRightOverlap() {
      Period p1 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));
      Period p2 = new Period(new LocalDate(2000, 1, 15), new LocalDate(2000, 1, 25));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-15", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-15", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-25", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsRightOverlapReverse() {
      Period p1 = new Period(new LocalDate(2000, 1, 15), new LocalDate(2000, 1, 25));
      Period p2 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-15", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-15", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-25", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsCompletelyInside() {
      Period p1 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30));
      Period p2 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-01", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-30", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsCompletelyInsideReverse() {
      Period p1 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));
      Period p2 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 30));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(2, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-01", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getEnd().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(1).getStart().toString());
      Assert.assertEquals("2000-01-30", nonOverlappingParts.get(1).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsStartIsBorder() {
      Period p1 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 10));
      Period p2 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-01", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-10", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(1, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-20", nonOverlappingParts.get(0).getEnd().toString());
   }

   @Test
   public void testOverlappingPartsEndIsBorder() {
      Period p1 = new Period(new LocalDate(2000, 1, 10), new LocalDate(2000, 1, 20));
      Period p2 = new Period(new LocalDate(2000, 1, 1), new LocalDate(2000, 1, 20));

      Period overlappingPart = p1.getOverlappingPart(p2);
      Assert.assertEquals("2000-01-10", overlappingPart.getStart().toString());
      Assert.assertEquals("2000-01-20", overlappingPart.getEnd().toString());

      List<Period> nonOverlappingParts = p1.getNonOverlappingParts(p2);
      Assert.assertEquals(1, nonOverlappingParts.size());
      Assert.assertEquals("2000-01-01", nonOverlappingParts.get(0).getStart().toString());
      Assert.assertEquals("2000-01-10", nonOverlappingParts.get(0).getEnd().toString());
   }

}
