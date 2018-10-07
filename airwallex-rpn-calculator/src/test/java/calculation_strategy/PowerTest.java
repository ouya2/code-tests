package calculation_strategy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Power;
import rpn.operator.PowerOperator;

public class PowerTest
{
    Power operator;

    @Before
    public void setup()
    {
        operator = new Power();
    }

    @Test
    public void testCalculate() throws Exception
    {
        double firstParameter = 4123456879d;
        double secondParameter = 2d;
        assertEquals(1.7002896632972421E19, operator.calculate(firstParameter, secondParameter));
        firstParameter = 12.654678923445614d;
        secondParameter = 125d;
        assertEquals(160.14089865549863, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }
}
