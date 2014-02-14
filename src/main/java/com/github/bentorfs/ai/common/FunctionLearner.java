package com.github.bentorfs.ai.common;

/**
 * 
 * 
 * @author betorfs
 */
public interface FunctionLearner<T> {

  public T predictValue(Object... attributes);

  public void showValue(T newValue, Object... attributes);

}
