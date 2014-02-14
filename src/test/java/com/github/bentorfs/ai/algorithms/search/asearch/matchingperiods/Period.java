package com.github.bentorfs.ai.algorithms.search.asearch.matchingperiods;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * 
 * 
 * @author betorfs
 */
public class Period implements Comparable<Period> {

  private LocalDate start;

  private LocalDate end;

  public Period(LocalDate start, LocalDate end) {
    super();
    this.start = start;
    this.end = end;
  }

  public int getLength() {
    return Days.daysBetween(start, end).getDays();
  }

  public Period getOverlappingPart(Period o) {
    if (o.getEnd().isAfter(getStart()) && o.getStart().isBefore(getEnd())) {
      // There is overlap
      if (getStart().isBefore(o.getStart())) {
        if (getEnd().isBefore(o.getEnd())) {
          return new Period(o.getStart(), getEnd());
        }
        else {
          return new Period(o.getStart(), o.getEnd());
        }
      }
      else {
        if (getEnd().isBefore(o.getEnd())) {
          return new Period(getStart(), getEnd());
        }
        else {
          return new Period(getStart(), o.getEnd());
        }
      }
    }
    else {
      // There is no overlap
      return null;
    }
  }

  public List<Period> getNonOverlappingParts(Period o) {
    List<Period> result = new LinkedList<>();
    if (o.getEnd().isAfter(getStart()) && o.getStart().isBefore(getEnd())) {
      // There is overlap
      if (getStart().isBefore(o.getStart())) {
        if (getEnd().isBefore(o.getEnd())) {
          result.add(new Period(getStart(), o.getStart()));
          result.add(new Period(getEnd(), o.getEnd()));
        }
        else if (getEnd().isAfter(o.getEnd())) {
          result.add(new Period(getStart(), o.getStart()));
          result.add(new Period(o.getEnd(), getEnd()));
        }
        else {
          result.add(new Period(getStart(), o.getStart()));
        }
      }
      else if (getStart().isAfter(o.getStart())) {
        if (getEnd().isBefore(o.getEnd())) {
          result.add(new Period(o.getStart(), getStart()));
          result.add(new Period(getEnd(), o.getEnd()));
        }
        else if (getEnd().isAfter(o.getEnd())) {
          result.add(new Period(o.getStart(), getStart()));
          result.add(new Period(o.getEnd(), getEnd()));
        }
        else {
          result.add(new Period(o.getStart(), getStart()));
        }
      }
      else {
        if (getEnd().isBefore(o.getEnd())) {
          result.add(new Period(getEnd(), o.getEnd()));
        }
        else if (getEnd().isAfter(o.getEnd())) {
          result.add(new Period(o.getEnd(), getEnd()));
        }
      }
    }
    else {
      // There is no overlap
      result.add(new Period(getStart(), getEnd()));
    }
    return result;
  }

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return start + " - " + end;
  }

  /** {@inheritDoc} */
  @Override
  public int compareTo(Period o) {
    int compareTo = start.compareTo(o.getStart());
    if (compareTo == 0) {
      compareTo = end.compareTo(o.getEnd());
    }
    return compareTo;
  }

}
