package calculation_strategy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Multiply;
import rpn.operator.MultiplyOperator;

public class MultiplyTest
{
    Multiply operator;
    @Before
    public void setup()
    {
        operator = new Multiply();
    }

    @Test
    public void testCalculate()
    {
        double firstParameter = 2d;
        double secondParameter = 412345687987435d;
        assertEquals(8.2469137597487E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 3.5412457d;
        secondParameter = 12.6546789234d;
        assertEquals(44.81332732237088, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }
}
