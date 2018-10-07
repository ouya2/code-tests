package rpn.operator;

import static rpn.operator.OperatorConstants.PLUS;
import static rpn.operator.OperatorConstants.MINUS;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Subtract;

/**
 * Define subtraction/"-" operator with calculation.
 */
public class SubtractOperator extends GeneralOperator
{
    public SubtractOperator()
    {
        super(MINUS, PLUS, 2);
    }


    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Subtract();
    }
}
