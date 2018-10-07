package calculation_strategy;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.SquareRoot;
import rpn.operator.SquareRootOperator;

public class SquareRootTest
{
    SquareRoot operator;

    @Before
    public void setup()
    {
        operator = new SquareRoot();
    }

    @Test
    public void testCalculate() throws Exception
    {
        double firstParameter = 412345687987435d;
        double secondParameter = 2d;
        assertEquals(2.0306296757100616E7, operator.calculate(firstParameter, secondParameter));
        firstParameter = 12.65216546789234456d;
        secondParameter = 125d;
        assertEquals(3.5569882580481407, operator.calculate(firstParameter, secondParameter), 0.000000000000001);
    }

    @Test(expected = ArithmeticException.class)
    public void testInvalidCalculate() throws Exception
    {
        operator.calculate(-2d, 1235d);
    }
}
