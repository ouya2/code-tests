package calculation_strategy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Divide;

public class DivideTest
{
    Divide operator;

    @Before
    public void setup()
    {
        operator = new Divide();
    }

    @Test
    public void testCalculate() throws Exception
    {
        double firstParameter = 2d;
        double secondParameter = 412345687987435d;
        assertEquals(2.061728439937175E14, operator.calculate(firstParameter, secondParameter));
        firstParameter = 3.541245d;
        secondParameter = 12.6546789234d;
        assertEquals(3.573511271713762, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }

    @Test(expected = ArithmeticException.class)
    public void testInvalidCalculate()
    {
        operator.calculate(0d, 1235d);
    }
}
