package rpn.calculation_strategy;

import static java.lang.Math.pow;

public class Power implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    return pow(firstParameter, 2.0);
  }
}
