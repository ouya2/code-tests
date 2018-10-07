package rpn.operator;

import static java.lang.Math.pow;
import static rpn.operator.OperatorConstants.POWER;
import static rpn.operator.OperatorConstants.SQRT;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Power;

/**
 * Define power/"pow" operator with calculation.
 */
public class PowerOperator extends GeneralOperator
{
    public PowerOperator()
    {
        super(POWER , SQRT, 1);
    }

    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Power();
    }
}
