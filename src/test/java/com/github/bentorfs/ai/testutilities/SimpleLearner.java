package com.github.bentorfs.ai.testutilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.bentorfs.ai.common.FunctionLearner;

/**
 * Learns value by simply remembering them, but not extrapolating to unseen values
 * 
 * Values are set to be Doubles
 * 
 * @author betorfs
 */
public class SimpleLearner implements FunctionLearner<Double> {

  private static final Double DEFAULT_VALUE = 0d;

  private Map<Integer, List<Object>> explanationMap = new HashMap<>();

  private Map<Integer, Double> learnedValues = new HashMap<>();

  /** {@inheritDoc} */
  @Override
  public Double predictValue(Object... attributes) {
    List<Object> key = Arrays.asList(attributes);
    Double predictedValue = learnedValues.get(key.hashCode());
    if (predictedValue == null) {
      predictedValue = DEFAULT_VALUE;
    }
    return predictedValue;
  }

  /** {@inheritDoc} */
  @Override
  public void showExample(Double value, Object... attributes) {
    List<Object> stateList = Arrays.asList(attributes);
    Integer hashCode = stateList.hashCode();
    learnedValues.put(hashCode, value);
    explanationMap.put(hashCode, stateList);
  }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    for (Entry<Integer, Double> entry : learnedValues.entrySet()) {
      StringBuffer keyString = new StringBuffer();
      List<Object> explanation = explanationMap.get(entry.getKey());
      if (explanation != null) {
        for (Object object : explanation) {
          keyString.append(object + "\n\n");
        }
        result.append("Doing this: \n" + keyString.toString() + "--> earns: " + entry.getValue().toString() + "\n");
      }
    }
    return result.toString();
  }
}
