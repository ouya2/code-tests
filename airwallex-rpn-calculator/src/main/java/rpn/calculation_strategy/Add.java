package rpn.calculation_strategy;

public class Add implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    return firstParameter + secondParameter;
  }
}
