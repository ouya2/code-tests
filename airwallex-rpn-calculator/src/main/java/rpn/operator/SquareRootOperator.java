package rpn.operator;

import static java.lang.Math.sqrt;
import static rpn.operator.OperatorConstants.POWER;
import static rpn.operator.OperatorConstants.SQRT;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.SquareRoot;

/**
 * Define sqrt operator with calculation.
 */
public class SquareRootOperator extends GeneralOperator
{
    public SquareRootOperator()
    {
        super(SQRT, POWER, 1);
    }

    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new SquareRoot();
    }
}
