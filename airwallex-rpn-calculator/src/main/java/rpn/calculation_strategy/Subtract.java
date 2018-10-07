package rpn.calculation_strategy;

public class Subtract implements CalculateStrategy {


  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    return secondParameter - firstParameter;
  }
}
