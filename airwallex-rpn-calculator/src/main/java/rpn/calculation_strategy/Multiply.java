package rpn.calculation_strategy;

public class Multiply implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    return firstParameter * secondParameter;
  }
}
