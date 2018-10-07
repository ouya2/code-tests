package rpn.operator;

import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MULTIPLY;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Multiply;

/**
 * Define multiplication/"*" operator with calculation.
 */
public class MultiplyOperator extends GeneralOperator
{
    public MultiplyOperator()
    {
        super(MULTIPLY, DIVIDE, 2);
    }

    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Multiply();
    }
}
