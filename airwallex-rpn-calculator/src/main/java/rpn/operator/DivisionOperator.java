package rpn.operator;

import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MULTIPLY;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Divide;

/**
 * Define division operator with calculation.
 */
public class DivisionOperator extends GeneralOperator
{
    public DivisionOperator()
    {
        super(DIVIDE, MULTIPLY, 2);
    }


    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Divide();
    }
}
