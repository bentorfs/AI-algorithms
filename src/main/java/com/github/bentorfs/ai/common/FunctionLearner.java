package com.github.bentorfs.ai.common;

import java.util.List;

/**
 * Generic interface for a function learning algorithm.
 * 
 * The attributes of the shown values must have a proper hashcode/equals, of course.
 * 
 * @author betorfs
 * 
 * @param <A>
 *           The type of the attributes
 * @param <T>
 *           The type of the target output
 */
public interface FunctionLearner<A, T> {

   /**
    * Predict the value for the given list of attributes, based on the examples seen so far
    */
   public T predictValue(List<A> attributes);

   /**
    * Show the learner an example, containing its list of attributes, and the target value
    */
   public void showExample(T value, List<A> attributes);

}
