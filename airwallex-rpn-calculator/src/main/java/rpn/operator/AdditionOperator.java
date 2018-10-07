package rpn.operator;

import static rpn.operator.OperatorConstants.PLUS;
import static rpn.operator.OperatorConstants.MINUS;

import rpn.calculation_strategy.Add;
import rpn.calculation_strategy.CalculateStrategy;

/**
 * Define addition/"+" operator with calculation.
 */
public class AdditionOperator extends GeneralOperator
{
    public AdditionOperator()
    {
        super(PLUS, MINUS, 2);
    }

    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Add();
    }
}
