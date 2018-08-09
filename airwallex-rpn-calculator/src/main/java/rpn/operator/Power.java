package rpn.operator;

import static java.lang.Math.pow;
import static rpn.operator.OperatorConstants.POWER;
import static rpn.operator.OperatorConstants.SQRT;

/**
 * Define power/"pow" operator with calculation.
 */
public class Power extends GeneralOperator
{
    public Power()
    {
        super(POWER , SQRT, 1);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        return pow(firstParameter, 2.0);
    }
}
