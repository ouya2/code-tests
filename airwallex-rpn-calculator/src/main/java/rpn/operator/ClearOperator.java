package rpn.operator;

import static rpn.operator.OperatorConstants.CLEAR;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Clear;

/**
 * Define clear operator.
 */
public class ClearOperator extends GeneralOperator
{
    public ClearOperator()
    {
        super(CLEAR, null, 0);
    }

    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Clear();
    }
}
